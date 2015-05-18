package demo.core.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by fanjun on 14-11-7.
 */
public class Userlogin implements Serializable {
    private int id;
    private int userid;
    private LocalDateTime logintime;
    private String loginip;
    private String loginby;
    private String useragent;
    private String acceptlanguage;

    public Userlogin(){

    }

    public Userlogin(int userid,LocalDateTime logintime,String loginip,String loginby,String useragent,String acceptlanguage){
        this.userid = userid;
        this.logintime = logintime;
        this.loginip = loginip;
        this.loginby = loginby;
        this.useragent = useragent;
        this.acceptlanguage = acceptlanguage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public LocalDateTime getLogintime() {
        return logintime;
    }

    public void setLogintime(LocalDateTime logintime) {
        this.logintime = logintime;
    }

    public String getLoginip() {
        return loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }

    public String getLoginby() {
        return loginby;
    }

    public void setLoginby(String loginby) {
        this.loginby = loginby;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public String getAcceptlanguage() {
        return acceptlanguage;
    }

    public void setAcceptlanguage(String acceptlanguage) {
        this.acceptlanguage = acceptlanguage;
    }
}
