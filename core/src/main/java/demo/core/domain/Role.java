package demo.core.domain;

import java.io.Serializable;

/**
 * Created by jack on 15/3/5.
 */
public class Role implements Serializable {
    private int id;
    private String rolename;
    private String rolecode;

    public Role(){}

    public Role(String rolename, String rolecode) {
        this.rolecode = rolecode;
        this.rolename = rolename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolecode() {
        return rolecode;
    }

    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
