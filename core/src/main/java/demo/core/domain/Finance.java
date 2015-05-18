package demo.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by fanjun on 15-1-21.
 */
public class Finance implements Serializable {

    //金融,煤易贷和快融通联系
    private int id;
    private String type;            //类型
    private String companyname;     //公司名称
    private String address;         //公司地址
    private String businessarea;    //业务区域
    private int amountnum;          //融资金额(整数)
    private String contact;        //联系人
    private String phone;           //联系电话
    private LocalDateTime createtime;  //创建时间

    public Finance(){

    }

    public Finance(String type, String companyname, String address, String businessarea,int amountnum,String contact, String phone,LocalDateTime createtime) {
        this.type = type;
        this.companyname = companyname;
        this.address = address;
        this.businessarea = businessarea;
        this.amountnum = amountnum;
        this.contact = contact;
        this.phone = phone;
        this.createtime = createtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessarea() {
        return businessarea;
    }

    public void setBusinessarea(String businessarea) {
        this.businessarea = businessarea;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public int getAmountnum() {
        return amountnum;
    }

    public void setAmountnum(int amountnum) {
        this.amountnum = amountnum;
    }
}
