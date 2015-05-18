package demo.core.domain;

import java.io.Serializable;

/**
 * Created by fanjun on 15-3-5.
 */
public class RoleOperate implements Serializable {

    //角色操作表
    private int id;
    private int roleid;
    private String operatecode;

    public RoleOperate(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getOperatecode() {
        return operatecode;
    }

    public void setOperatecode(String operatecode) {
        this.operatecode = operatecode;
    }
}
