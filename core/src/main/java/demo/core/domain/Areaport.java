package demo.core.domain;

import java.io.Serializable;

/**
 * Created by fanjun on 14-12-16.
 */
public class Areaport implements Serializable {
    private int id;
    private String name;
    private int parentid;
    private String code;
    private boolean isdelete;
    //港口下的交易员数量
    private int dealerNum;

    public Areaport(){

    }

    public Areaport(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Areaport(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Areaport(int id, String name, int parentid, String code, boolean isdelete) {
        this.id = id;
        this.name = name;
        this.parentid = parentid;
        this.code = code;
        this.isdelete = isdelete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isIsdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }

    public int getDealerNum() {
        return dealerNum;
    }

    public void setDealerNum(int dealerNum) {
        this.dealerNum = dealerNum;
    }
}
