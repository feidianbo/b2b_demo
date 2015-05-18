package demo.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 团购供应商信息
 * Created by zhangbolun on 15/1/22.
 */
public class ProviderInfo implements Serializable {
    private int id;                          //pk_id
    private String providername;             //供应商名称
    private BigDecimal  registeredcapital;   //注册资本
    private String enterpriseproperty;       //企业性质
    private String enterpriseaddress;        //企业地点
    private LocalDateTime lastupdatetime;        //最后一次更新时间
    private LocalDateTime createtime;            //信息创建时间
    private String contacter;                //联系人
    private String contactphone;             //联系电话
    private boolean isdelete;                //是否删除
    private String status;                   //状态


    public ProviderInfo(){}

    public ProviderInfo(int id, String providername, BigDecimal registeredcapital, String enterpriseproperty, String enterpriseaddress, LocalDateTime lastupdatetime, LocalDateTime createtime, String contacter, String contactphone, boolean isdelete, String status) {
        this.id = id;
        this.providername = providername;
        this.registeredcapital = registeredcapital;
        this.enterpriseproperty = enterpriseproperty;
        this.enterpriseaddress = enterpriseaddress;
        this.lastupdatetime = lastupdatetime;
        this.createtime = createtime;
        this.contacter = contacter;
        this.contactphone = contactphone;
        this.isdelete = isdelete;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvidername() {
        return providername;
    }

    public void setProvidername(String providername) {
        this.providername = providername;
    }

    public BigDecimal getRegisteredcapital() {
        return registeredcapital;
    }

    public void setRegisteredcapital(BigDecimal registeredcapital) {
        this.registeredcapital = registeredcapital;
    }

    public String getEnterpriseproperty() {
        return enterpriseproperty;
    }

    public void setEnterpriseproperty(String enterpriseproperty) {
        this.enterpriseproperty = enterpriseproperty;
    }

    public String getEnterpriseaddress() {
        return enterpriseaddress;
    }

    public void setEnterpriseaddress(String enterpriseaddress) {
        this.enterpriseaddress = enterpriseaddress;
    }

    public LocalDateTime getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(LocalDateTime lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone;
    }

    public boolean getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
