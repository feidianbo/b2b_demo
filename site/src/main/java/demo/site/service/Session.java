package demo.site.service;


import demo.core.domain.Phonevalidator;
import demo.core.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Session {
    protected User user;
    protected Phonevalidator phonevalidator;
    protected String picCode;
    protected Phonevalidator resetPasswdValidCode;

    public User getUser() {
        return user;
    }

    public boolean login(User user) {
        this.user = user;
        return true;
    }

    public boolean addPhonevalidator(Phonevalidator phonevalidator) {
        this.phonevalidator = phonevalidator;
        return true;
    }

    public boolean isLogined() {
        return this.user != null;
    }

    public void logout() {
        this.user = null;
    }

    public Phonevalidator getPhonevalidator() {
        return phonevalidator;
    }

    public String getPicCode() {
        return picCode;
    }

    public void setPicCode(String picCode) {
        this.picCode = picCode;
    }

    public Phonevalidator getResetPasswdValidCode() {
        return resetPasswdValidCode;
    }

    public void setResetPasswdValidCode(Phonevalidator resetPasswdValidCode) {
        this.resetPasswdValidCode = resetPasswdValidCode;
    }

}
