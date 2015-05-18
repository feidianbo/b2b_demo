package demo.core.service;

import demo.core.domain.User;
import demo.core.persistence.UserMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by joe on 11/7/14.
 */
@Service
public class UserAuth {
    @Autowired
    protected UserMapper userMapper;

    public User getUserBySecurePhone(String securephone){
        if(securephone!=null)
            return userMapper.getUserByPhone(securephone);
        return null;
    }

    public boolean auth(User user, String password){
        return user!=null && password!=null && DigestUtils.md5Hex(password).equals(user.getPassword());
    }

    public boolean auth(String securephone, String password){
        return auth(this.getUserBySecurePhone(securephone), password);
    }
}
