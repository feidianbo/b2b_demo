package demo.core.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by jack on 14/12/12.
 */
public class OrderVerify implements Serializable {
    private int id;
    private EnumOrder status;
    private String verifyman;
    private LocalDateTime applytime;
    private LocalDateTime verifytime;
    private int orderid;
    private int userid;
    private String remarks;

    public OrderVerify() {}

    public OrderVerify(EnumOrder status, LocalDateTime applytime, int orderid, int userid) {
        this.status = status;
        this.applytime = applytime;
        this.orderid = orderid;
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnumOrder getStatus() {
        return status;
    }

    public void setStatus(EnumOrder status) {
        this.status = status;
    }

    public String getVerifyman() {
        return verifyman;
    }

    public void setVerifyman(String verifyman) {
        this.verifyman = verifyman;
    }

    public LocalDateTime getApplytime() {
        return applytime;
    }

    public void setApplytime(LocalDateTime applytime) {
        this.applytime = applytime;
    }

    public LocalDateTime getVerifytime() {
        return verifytime;
    }

    public void setVerifytime(LocalDateTime verifytime) {
        this.verifytime = verifytime;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
