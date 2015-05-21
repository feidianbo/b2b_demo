package demo.site.service;

import com.mysql.jdbc.StringUtils;
import demo.core.domain.Phonevalidator;
import demo.core.domain.User;
import demo.core.domain.ValidateType;
import demo.core.persistence.BuyMapper;
import demo.core.persistence.UserMapper;
import demo.core.persistence.ValidMapper;
import demo.core.service.CODE;
import demo.core.service.SMS;
import demo.site.basic.exception.NotFoundException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class Auth {
    @Autowired
    protected Session session;
    @Autowired
    protected SMS sms;
    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected ValidMapper validMapper;
    @Autowired
    protected BuyMapper buyMapper;
    @Autowired
    protected CODE code;

    public boolean login(String securephone, String password) {
        if (securephone != null) {
            User user = userMapper.getUserByPhone(securephone);
            if (user != null && user.getPassword().equals(password)) {
                session.login(user);
                return true;
            }
        }
        return false;
    }

    //检查手机号是否已经注册
    public boolean checkUser(String securephone) {
        if (!StringUtils.isNullOrEmpty(securephone)) {
            User user = userMapper.getUserByPhone(securephone);
            return user == null ? true : false;
        }
        return false;           //手机号已经被注册
    }

    //注册，向数据库添加用户
    public boolean addUser(String securephone, String password) throws Exception {
        if (!StringUtils.isNullOrEmpty(securephone) && !StringUtils.isNullOrEmpty(password)) {
            userMapper.addUser(new User(securephone, DigestUtils.md5Hex(password), LocalDateTime.now(), true));
            session.login(userMapper.getUserByPhone(securephone));
            String content = "感谢您成为会员！温馨提示：请您完善企业信息，以便享受更加快捷、安全的交易功能与服务。";
            String hellowords = "";
            String signature = "【XX网】";
            sms.send(securephone, content, hellowords, signature);
            return true;    //注册成功
        }
        return false;       //注册失败
    }

    //发送手机验证码
    public boolean sendSMSCode(String securephone) throws Exception {
        if (securephone != null) {
            Phonevalidator phonevalidator = session.getPhonevalidator();
            String hellowords = "您的验证码是：";
            String signature = "【XX网】";
            if (phonevalidator != null && phonevalidator.getExpiretime().isAfter(LocalDateTime.now())
                    && phonevalidator.getPhone().equals(securephone)
                    && phonevalidator.getValidated() == 0) {
                if(phonevalidator.getExpiretime().minusMinutes(9).isBefore(LocalDateTime.now())) {
                    String verifycode = session.getPhonevalidator().getCode();
                    sms.send(securephone, verifycode, hellowords, signature);
                }
            } else {
                String verifycode = code.CreateCode();
                //DEO@20150518
                //println(verifycode);
                phonevalidator = new Phonevalidator(LocalDateTime.now().plusMinutes(10), securephone, verifycode, 0, null, 0, ValidateType.register);
                validMapper.addValid(phonevalidator);
                session.addPhonevalidator(phonevalidator);
                sms.send(securephone, verifycode, hellowords, signature);
            }
            return true;
        }
        return false;
    }

    //检验验证码
    public boolean checkCode(String securephone, String code) {
        if (!StringUtils.isNullOrEmpty(securephone) && !StringUtils.isNullOrEmpty(code)) {
            if (session.getPhonevalidator() != null && session.getPhonevalidator().getValidated() == 0 && session.getPhonevalidator().getCode().equals(code) && session.getPhonevalidator().getPhone().equals(securephone)) {
                session.getPhonevalidator().setValidated(1);
                return true;
            }
        }
        return false;
    }

    //检查是否有查看权限
    public void doCheckUserRight(int userid){
        if(session.getUser().getId() != userid){
            throw new NotFoundException();
        }
    }

    public void doCheckUserRight(int userid, int sellerid){
        if((session.getUser().getId() != userid && session.getUser().getId() != sellerid) ||
                (session.getUser().getId() == userid && session.getUser().getId() == sellerid )){
            throw new NotFoundException();
        }
    }
}
