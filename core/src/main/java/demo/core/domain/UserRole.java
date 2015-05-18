package demo.core.domain;

import java.io.Serializable;

/**
 * Created by jack on 15/3/5.
 */
public class UserRole implements Serializable {
    private int id;
    private int userid;
    private int roleid;

    public UserRole(){}

    public UserRole(int roleid, int userid) {
        this.roleid = roleid;
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserRole(int id) {
        this.id = id;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
