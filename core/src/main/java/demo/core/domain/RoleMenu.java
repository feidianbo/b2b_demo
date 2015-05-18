package demo.core.domain;

import java.io.Serializable;

/**
 * Created by jack on 15/3/5.
 */
public class RoleMenu implements Serializable {
    private int id;
    private int roleid;
    private int menuid;

    public RoleMenu(){}

    public RoleMenu(int menuid, int roleid) {
        this.menuid = menuid;
        this.roleid = roleid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMenuid() {
        return menuid;
    }

    public void setMenuid(int menuid) {
        this.menuid = menuid;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }
}
