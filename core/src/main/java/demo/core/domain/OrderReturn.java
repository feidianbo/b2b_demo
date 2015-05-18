package demo.core.domain;

import java.time.LocalDateTime;

/**
 * Created by jack on 15/1/4.
 */
public class OrderReturn {
    private int id;
    private int orderid;                          //order 表对应的id
    private String oid;                           //orderid
    private String status;                        //状态， 正常是 退货中
    private EnumOrder laststatus;                    //上一个状态
    private LocalDateTime createtime;             //创建时间
    private LocalDateTime lastupdatetime;             //最后一次改变时间
    private int userid;                           //用户id
    private String username;                      //用户name
    private boolean iscanceled;                   //用户是否已经取消退货

    public OrderReturn() {
    }

    public OrderReturn(EnumOrder laststatus, int orderid, String oid, int userid, String username, boolean iscanceled) {
        this.iscanceled = iscanceled;
        this.laststatus = laststatus;
        this.oid = oid;
        this.orderid = orderid;
        this.userid = userid;
        this.username = username;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIscanceled() {
        return iscanceled;
    }

    public void setIscanceled(boolean iscanceled) {
        this.iscanceled = iscanceled;
    }

    public EnumOrder getLaststatus() {
        return laststatus;
    }

    public void setLaststatus(EnumOrder laststatus) {
        this.laststatus = laststatus;
    }

    public LocalDateTime getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(LocalDateTime lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
