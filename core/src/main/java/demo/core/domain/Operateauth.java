package demo.core.domain;

import java.io.Serializable;

/**
 * Created by fanjun on 15-3-5.
 */
public class Operateauth implements Serializable {

    //操作权限表
    private int id;
    private String operatename;
    private String operatecode;
    private int menuid;
    private String menuname;

    public Operateauth(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperatename() {
        return operatename;
    }

    public void setOperatename(String operatename) {
        this.operatename = operatename;
    }

    public String getOperatecode() {
        return operatecode;
    }

    public void setOperatecode(String operatecode) {
        this.operatecode = operatecode;
    }

    public int getMenuid() {
        return menuid;
    }

    public void setMenuid(int menuid) {
        this.menuid = menuid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }
}
