package demo.core.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by jack on 14/12/10.
 */
public class SupplyVerify implements Serializable {
    private int id;
    private EnumSellInfo status;
    private String verifyman;
    private LocalDateTime applytime;
    private LocalDateTime verifytime;
    private int sellinfoid;
    private int userid;
    private String remarks;

    public SupplyVerify() {}

    public SupplyVerify(EnumSellInfo status, LocalDateTime applytime, int sellinfoid, int userid) {
        this.status = status;
        this.applytime = applytime;
        this.sellinfoid = sellinfoid;
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnumSellInfo getStatus() {
        return status;
    }

    public void setStatus(EnumSellInfo status) {
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

    public int getSellinfoid() {
        return sellinfoid;
    }

    public void setSellinfoid(int sellinfoid) {
        this.sellinfoid = sellinfoid;
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
