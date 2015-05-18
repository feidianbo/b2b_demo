package demo.site.controller;

import demo.core.domain.Phonevalidator;
import demo.core.domain.User;
import demo.core.domain.Userlogin;
import demo.core.domain.ValidateType;
import demo.core.persistence.UserMapper;
import demo.core.persistence.ValidMapper;
import demo.core.service.CODE;
import demo.core.service.SMS;
import demo.site.basic.JsonController;
import demo.site.service.Auth;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by joe on 10/26/14.
 */
@Controller
public class AuthController extends JsonController {
	@Autowired
	protected Auth auth;
	@Autowired
	protected UserMapper userMapper;
    @Autowired
    protected SMS sms;
    @Autowired
    protected ValidMapper validMapper;
    @Autowired
    protected CODE code;

	@RequestMapping("/register")public String register() {
		return "register";
	}

    public static class PhoneForm{
        @Size(min=11, max=11)
        @NotNull
        protected String securephone;
        public String getSecurephone() {
            return securephone;
        }
        public void setSecurephone(String securephone) {
            this.securephone = securephone;
        }
    }

    public static class LoginForm extends PhoneForm{
        @Size(min = 6, max = 16)
        @NotNull
        protected String password;
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
    }

	public static class RegisterForm extends LoginForm{
		private String smsCode;
		public String getSmsCode() {
			return smsCode;
		}
		public void setSmsCode(String smsCode) {
			this.smsCode = smsCode;
		}
	}

    public static class ValidPhoneForm extends PhoneForm{
        @Size(min = 4, max = 4,message = "验证码4位")
        @NotNull(message = "验证码不能为空")
        private String picCode;
        public String getPicCode() {
            return picCode;
        }
        public void setPicCode(String picCode) {
            this.picCode = picCode;
        }
    }

    public static class ValidCodeForm extends PhoneForm{
        @Size(min = 6, max = 6,message = "验证码长度为6位")
        @NotNull(message = "验证码不能为空")
        private String code;
        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
    }

    public static class ResetAccountPasswordForm {
        private String password;
        @Size(min = 6, max = 16,message = "密码长度为6-16位")
        @NotNull(message = "新密码不能为空")
        private String newpassword;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getNewpassword() {
            return newpassword;
        }

        public void setNewpassword(String newpassword) {
            this.newpassword = newpassword;
        }
    }

    public static class ResetNicknameForm extends PhoneForm{
        @Size(min = 1, max = 20, message = "昵称长度不能超过20位")
        @NotNull(message = "昵称不能为空")
        private String nickname;
        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }

	@RequestMapping(value = "/userCheck", method = RequestMethod.POST)
	@ResponseBody
	public Object doUserCheck(@Valid PhoneForm phoneForm, BindingResult bindingResult) {
		if(!bindingResult.hasErrors()){
			if(!checkPhonenum(phoneForm.securephone)){
				bindingResult.rejectValue("securephone", "phoneillegal", "请输入正确的手机号");
			} else if(!auth.checkUser(phoneForm.securephone)){
				bindingResult.rejectValue("securephone", "phoneexist", "此手机号已经被注册");
			}
		}
		return json(bindingResult);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Object doRegister(@Valid RegisterForm registerForm, BindingResult bindingResult) throws Exception {
		if(!bindingResult.hasErrors()){
			if(!checkPhonenum(registerForm.securephone)){
				bindingResult.rejectValue("securephone", "phoneillegal", "请输入正确的手机号");
			} else if(userMapper.getUserByPhone(registerForm.securephone) != null){
				bindingResult.rejectValue("securephone", "phoneexist", "手机号已经被注册");
			} else if(session.getPhonevalidator() != null && session.getPhonevalidator().getExpiretime().isBefore(LocalDateTime.now())){
				bindingResult.rejectValue("smsCode", "codeover", "验证码已过期");
			} else if(!auth.checkCode(registerForm.securephone, registerForm.smsCode)){
				bindingResult.rejectValue("smsCode", "codeerror", "验证码输入错误");
			} else if(!auth.addUser(registerForm.securephone, registerForm.password)){
				bindingResult.rejectValue("securephone", "regfailure", "注册失败");
			}
		}
		return json(bindingResult);
	}

	// 给手机发送验证码
	@RequestMapping(value = "/sendSmsCode", method = RequestMethod.POST)
	@ResponseBody
	public Object doSendSmsCode(@Valid PhoneForm phoneForm, BindingResult bindingResult) throws Exception {
		if(!bindingResult.hasErrors()) {
			if (!checkPhonenum(phoneForm.securephone)) {
				bindingResult.rejectValue("securephone", "phoneillegal", "请输入正确的手机号");
			} else if (!auth.checkUser(phoneForm.securephone)) {
				bindingResult.rejectValue("securephone", "phoneexist", "此手机号已经被注册");
			} else if (!auth.sendSMSCode(phoneForm.securephone)) {
				bindingResult.rejectValue("securephone", "senderror", "手机短信验证码发送失败");
			}
		}
		return json(bindingResult);
	}
	public boolean checkPhonenum(String securephone) {

		return (null!=securephone)&&Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$").matcher(securephone).matches(); // 验证手机号
	}

    @RequestMapping("/logout")
    public String logout(){
        session.logout();
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login(String securephone, String t, Map<String, Object> model){
        if(!StringUtils.isBlank(securephone))
            model.put("securephone",securephone);
        if(!StringUtils.isBlank(t))
            model.put("t",t);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object doLogin(@Valid LoginForm loginForm, BindingResult bindingResult,HttpServletRequest request){
        if(!checkPhonenum(loginForm.securephone)){
            bindingResult.rejectValue("securephone", "phoneerror", "请输入正确的手机号码!");
        } else{
            String securephone = loginForm.securephone;
            String password = DigestUtils.md5Hex(loginForm.password);
            User user = userMapper.getUserByPhone(securephone);
            if(user != null){
                if(!user.isIsactive()){
                    bindingResult.rejectValue("securephone", "loginerror", "此账号被禁用");
                } else{
                    if(auth.login(securephone,password)) {
                        Userlogin userlogin = new Userlogin(user.getId(), LocalDateTime.now(),
                                getIp(request), null, getComputerType(request), getAcceptLanguage(request));
                        userMapper.addUserlogin(userlogin); //新增用户登陆记录
                    } else{
                        bindingResult.rejectValue("securephone", "loginerror", "用户名或密码错误");
                    }
                }
            } else{
                bindingResult.rejectValue("securephone", "loginerror", "用户名或密码错误");
            }
        }
        return json(bindingResult);
    }

    //重置密码
    @RequestMapping("/reset-password")
    public String resetPassword() {
        return "reset-password";
    }

    //验证手机号和图片验证码
    @RequestMapping(value = "/validphone", method = RequestMethod.POST)
    @ResponseBody
    public Object validPhone(@Valid ValidPhoneForm validPhoneForm, BindingResult bindingResult) throws Exception {
        User user = userMapper.getUserByPhone(validPhoneForm.securephone);
        if(user != null){
            if(validPhoneForm.picCode.equals(session.getPicCode())){
                String verifycode = code.CreateCode();
                String hellowords = "您的验证码是：";
                String signature = "【XX网】";
                sms.send(validPhoneForm.securephone, verifycode, hellowords, signature); //发送短信验证码
                //保存在session的对象中
                Phonevalidator pv = new Phonevalidator();
                pv.setCode(verifycode);
                pv.setExpiretime(LocalDateTime.now().plusMinutes(10));
                session.setResetPasswdValidCode(pv);
                Phonevalidator phonevalidator = new Phonevalidator(LocalDateTime.now().plusMinutes(10),validPhoneForm.securephone,verifycode,
                        user.getId(),"test",0, ValidateType.resetpassword);
                validMapper.addValid(phonevalidator);
            }else{
                bindingResult.rejectValue("picCode", "picCodeerror", "验证码错误");
            }
        }else{
            bindingResult.rejectValue("securephone", "usernotexist", "用户不存在");
        }
        return json(bindingResult);
    }

    //跳转填写验证码页面
    @RequestMapping("/verifyAccount")
    public String verifyAccount(Map<String, Object> model,@RequestParam("securephone") String securephone){
        model.put("securephone", securephone);
        return "verifyAccount";
    }

    //填写验证码
    @RequestMapping(value = "/validcode", method = RequestMethod.POST)
    @ResponseBody
    public Object validCode(@Valid ValidCodeForm validCodeForm,BindingResult bindingResult){
        Phonevalidator phonevalidator = validMapper.getValidcodeObj(validCodeForm.securephone,validCodeForm.code);
        if(phonevalidator != null) {
            if (LocalDateTime.now().isAfter(phonevalidator.getExpiretime())){
                bindingResult.rejectValue("code", "codeerror", "验证码错误");
            }else{
                validMapper.modifyValidatedAndTime(validCodeForm.securephone,validCodeForm.code);
                session.getResetPasswdValidCode().setValidated(1);
            }
        }else{
            bindingResult.rejectValue("code", "codeerror", "验证码错误");
        }
        return json(bindingResult);
    }

    //重置密码-重发验证码  10分钟内验证码相同
    @RequestMapping(value = "/resendValidcode")
    @ResponseBody
    public void resendValidcode(@RequestParam("securephone") String securephone) throws Exception {
        String verifycode = null;
        String hellowords = "您的验证码是：";
        String signature = "【XX网】";
        if(session != null){
           if(LocalDateTime.now().isBefore(session.getResetPasswdValidCode().getExpiretime())
                   &&session.getResetPasswdValidCode().getValidated() == 0){
               verifycode = session.getResetPasswdValidCode().getCode();
            }else{
               verifycode = code.CreateCode();
           }
        }else{
              verifycode = code.CreateCode();
        }
        sms.send(securephone, verifycode, hellowords, signature);
        User user = userMapper.getUserByPhone(securephone);
        Phonevalidator phonevalidator = new Phonevalidator(LocalDateTime.now().plusMinutes(10),securephone,verifycode,
                user.getId(),"test",0,ValidateType.resetpassword);
        validMapper.addValid(phonevalidator);
    }

    //跳转修改密码页面
    @RequestMapping("/modifypassword")
    public String modifyPassword(Map<String, Object> model,@RequestParam("securephone") String securephone){
        model.put("securephone", securephone);
        return "modifyPassword";
    }

    //修改密码
    @RequestMapping(value = "/modifypasswd", method = RequestMethod.POST)
    @ResponseBody
    public Object modifyPasswd(@Valid LoginForm loginForm,BindingResult bindingResult){
        if(!bindingResult.hasErrors()) {
            userMapper.modifyPasswd(DigestUtils.md5Hex(loginForm.password), loginForm.securephone);
        }else{
            bindingResult.rejectValue("code", "codeerror");
        }
        return json(bindingResult);
    }

    //修改昵称
    @RequestMapping(value = "/modifynickname", method = RequestMethod.POST)
    @ResponseBody
    public Object modifyNickname(@Valid ResetNicknameForm resetNicknameForm,BindingResult bindingResult){
        if(!bindingResult.hasErrors()) {
            userMapper.modifyNickname(resetNicknameForm.nickname, resetNicknameForm.securephone);
            session.getUser().setNickname(resetNicknameForm.nickname);
        }else{
            bindingResult.rejectValue("nickname", "nicknameerror");
        }
        return json(bindingResult);
    }

    //修改账户密码
    @RequestMapping(value = "/modifyAccountPasswd", method = RequestMethod.POST)
    @ResponseBody
    public Object modifyAccountPasswd(@Valid ResetAccountPasswordForm resetAccountPasswordForm,BindingResult bindingResult){
       //从数据库取密码,不从session中取,避免浏览器重复登录修改密码通过.
        User u = userMapper.getUserById(session.getUser().getId());
        if(!DigestUtils.md5Hex(resetAccountPasswordForm.password).equals(u.getPassword())){
            bindingResult.rejectValue("password", "passworderror", "原密码错误");
        }else {
            userMapper.modifyAccountPasswd(DigestUtils.md5Hex(resetAccountPasswordForm.newpassword), session.getUser().getId());
            session.getUser().setPassword(DigestUtils.md5Hex(resetAccountPasswordForm.newpassword));
        }
        return json(bindingResult);
    }

    //重置账户手机 --手机获取验证码
    @RequestMapping(value = "/getValidCode")
    @ResponseBody
    public Object getValidCode(@Valid PhoneForm phoneForm,BindingResult bindingResult) throws Exception {
        if(!checkPhonenum(phoneForm.securephone)){
            bindingResult.rejectValue("securephone", "phoneerror", "手机号错误");
        }else{
            String verifycode = code.CreateCode();
            String hellowords = "您的验证码是：";
            String signature = "【XX网】";
            sms.send(phoneForm.securephone, verifycode, hellowords, signature);
            Phonevalidator phonevalidator = new Phonevalidator(LocalDateTime.now().plusMinutes(10),phoneForm.securephone,
                 verifycode,session.getUser().getId(),"test",0,ValidateType.resetpassword);
            validMapper.addValid(phonevalidator);
        }
        return json(bindingResult);
    }

    //验证验证码
    @RequestMapping(value = "/validOrginalPhone")
    @ResponseBody
    public Object validOrginalPhone(@Valid ValidCodeForm validCodeForm,BindingResult bindingResult){
        Phonevalidator p =  validMapper.getValidcodeObj(validCodeForm.securephone,validCodeForm.code);
        if(p != null){
            if(LocalDateTime.now().isAfter(p.getExpiretime())){
                bindingResult.rejectValue("code", "codeerror", "验证码错误");
            }else{
                validMapper.modifyValidatedAndTime(validCodeForm.securephone,validCodeForm.code);
            }
        }else{
            bindingResult.rejectValue("code", "codeerror", "验证码错误");
        }
        return json(bindingResult);
    }

    //判断新手机号码是否存在
    @RequestMapping(value = "/checkNewSecurephone", method = RequestMethod.POST)
    @ResponseBody
    public String CheckNewSecurephone(@RequestParam("newSecurephone") String newSecurephone){
        String result ="";
        if(userMapper.getUserByNewSecurephone(newSecurephone) > 0){
            result = "fail";
        } else{
            result ="success";
        }
        return JSON.toString(result);
    }

    //修改账户手机号
    @RequestMapping(value = "/modifyphone", method = RequestMethod.POST)
    @ResponseBody
    public Object modifyPhone(@Valid ValidCodeForm validCodeForm,BindingResult bindingResult){
        Phonevalidator p =  validMapper.getValidcodeObj(validCodeForm.securephone,validCodeForm.code);
        if(p != null){
            userMapper.modifyPhone(validCodeForm.securephone,session.getUser().getId());
            validMapper.modifyValidatedAndTime(validCodeForm.securephone,validCodeForm.code);
            session.getUser().setSecurephone(validCodeForm.securephone);
        }else{
            bindingResult.rejectValue("code", "codeerror", "验证码错误");
        }
        return json(bindingResult);
    }

    //获取客户端的ip
    public  String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(ip != null && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(ip != null && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }

    //获取客户端的操作系统名称
    public String getComputerType(HttpServletRequest request) {
        //暂时先不做处理
        String agent = request.getHeader("user-agent");
//        StringTokenizer st = new StringTokenizer(agent,";");
//        st.nextToken();
//        String useros = st.nextToken();
        return agent;
    }

    //获取客户端的可接受语言
    public String getAcceptLanguage(HttpServletRequest request){
        return request.getHeader("accept-language");
    }

    //测试发送短信
    @RequestMapping("/test/sms")
    @ResponseBody
    public Object sendSMS() throws Exception {
        String content = "感谢您成为会员！温馨提示：请您完善企业信息，以便享受更加快捷、安全的交易功能与服务。";
        String signature = "【XX网】";
        String hellowords = "";
        sms.send("18616319335", content, hellowords, signature);
        return content+signature;
    }
}
