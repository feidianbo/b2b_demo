package demo.core.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by joe on 14/11/21.
 */
public class CompanyVerify implements Serializable{
    private int id;
    private String status;
    private String verifyman;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime applytime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime verifytime;
    private int companyid;
    private int userid;
    private String remarks;

    public CompanyVerify(){}

    public CompanyVerify(String status){
        this.status = status;
    }

    public CompanyVerify(String status, LocalDateTime applytime, int companyid, int userid) {
        this.status = status;
        this.applytime = applytime;
        this.companyid = companyid;
        this.userid = userid;
    }

    public CompanyVerify(LocalDateTime applytime, String status, int companyid,  int userid, String verifyman, LocalDateTime verifytime) {
        this.applytime = applytime;
        this.companyid = companyid;
        this.status = status;
        this.userid = userid;
        this.verifyman = verifyman;
        this.verifytime = verifytime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVerifyman() {
        return verifyman;
    }

    public void setVerifyman(String vertifyman) {
        this.verifyman = vertifyman;
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

    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
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
