package demo.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 团购资质
 * Created by zhangbolun on 15/1/22.
 */
public class GroupBuyQualification implements Serializable {
    private int id;                          //pk_id 主键
    private int userid;                      //fk_id 用户id
    private String groupbuyordercode;        //团购订单编号
    private String qualificationcode;        //团购资质编号
    private String status;                   //团购资质状态
    private BigDecimal margins;              //保证金
    private String marginscode;              //保证金合同编号
    private String photopath;                //保证金图片路径
    private LocalDateTime lastupdatetime;        //最后一次更新时间
    private LocalDateTime createtime;            //信息创建时间
    private boolean contractverify;           //确认合同
    private String comment;                   //备注
    private String companyname;               //公司名
    private String username;
    private String userphone;
    private boolean isdelete;

    public GroupBuyQualification(){}

    public GroupBuyQualification(int userid, String status) {
        this.userid = userid;
        this.status = status;
    }

    public GroupBuyQualification(String photopath, String status, boolean contractverify){
        this.photopath = photopath;
        this.status = status;
        this.contractverify = contractverify;
    }

    public GroupBuyQualification(String companyname,String userphone,String username,String comment,int id, String groupbuyordercode, String status, BigDecimal margins, String photopath, LocalDateTime lastupdatetime, LocalDateTime createtime, boolean contractverify, boolean isdelete) {
        this.id = id;
        this.groupbuyordercode = groupbuyordercode;
        this.status = status;
        this.margins = margins;
        this.photopath = photopath;
        this.lastupdatetime = lastupdatetime;
        this.createtime = createtime;
        this.contractverify = contractverify;
        this.isdelete = isdelete;
        this.comment=comment;
        this.username=username;
        this.userphone=userphone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getGroupbuyordercode() {
        return groupbuyordercode;
    }

    public void setGroupbuyordercode(String groupbuyordercode) {
        this.groupbuyordercode = groupbuyordercode;
    }

    public String getQualificationcode() {
        return qualificationcode;
    }

    public void setQualificationcode(String qualificationcode) {
        this.qualificationcode = qualificationcode;
    }

    public BigDecimal getMargins() {
        return margins;
    }

    public void setMargins(BigDecimal margins) {
        this.margins = margins;
    }

    public String getPhotopath() {
        return photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath;
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

    public boolean isContractverify() {
        return contractverify;
    }

    public void setContractverify(boolean contractverify) {
        this.contractverify = contractverify;
    }

    public boolean isIsdelete() {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMarginscode() {
        return marginscode;
    }

    public void setMarginscode(String marginscode) {
        this.marginscode = marginscode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
}
