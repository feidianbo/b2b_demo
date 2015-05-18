package demo.core.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 团购订单
 * Created by zhangbolun on 15/1/22.
 */
public class GroupBuyOrder implements Serializable {
    private int id;                          //pk_id
    private int groupbuysupplyid;            //fk_id 团购供货信息id
    private int userid;                      //fk_id 用户id
    private String qualificationcode;        //团购资质编号
    private String groupbuyordercode;        //团购订单编号
    private String status;                   //团购订单状态
    private int volume;                      //成交量，团购数量
    private LocalDateTime lastupdatetime;        //最后一次更新时间
    private String performancecode;          //履约书合同编号
    private LocalDateTime createtime;        //信息创建时间
    private String companyname;              //公司名称
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private LocalDate deliverydatestart;     //提货时间开始
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private LocalDate deliverydateend;       //提货时间截至
    private String qualifystatus;            //资质状
    private boolean isdelete;
    private boolean contractverify;          //确认合同
    private String groupbuysupplycode;       //团购供货信息编号
    private BigDecimal groupbuyprice;            //团购价格
    private LocalDateTime groupbuyenddate;       //团购截止日期

    public GroupBuyOrder(){}
    public GroupBuyOrder(LocalDate deliverydateend,LocalDate deliverydatestart,String qualifystatus,String companyname,String performancecode,int id, int groupbuysupplyid, int userid, String qualificationcode, String groupbuyordercode, String status, int volume,LocalDateTime lastupdatetime,LocalDateTime createtime,boolean contractverify,boolean isdelete, String groupbuysupplycode) {
        this.id = id;
        this.groupbuysupplyid = groupbuysupplyid;
        this.userid = userid;
        this.qualificationcode = qualificationcode;
        this.groupbuyordercode = groupbuyordercode;
        this.status = status;
        this.volume = volume;
        this.lastupdatetime = lastupdatetime;
        this.createtime = createtime;
        this.contractverify = contractverify;
        this.isdelete = isdelete;
        this.performancecode = performancecode;
        this.groupbuysupplycode = groupbuysupplycode;
        this.qualifystatus=qualifystatus;
        this.deliverydateend=deliverydateend;
        this.deliverydatestart=deliverydatestart;

        this.companyname=companyname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupbuysupplyid() {
        return groupbuysupplyid;
    }

    public void setGroupbuysupplyid(int groupbuysupplyid) {
        this.groupbuysupplyid = groupbuysupplyid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getQualificationcode() {
        return qualificationcode;
    }

    public void setQualificationcode(String qualificationcode) {
        this.qualificationcode = qualificationcode;
    }

    public String getGroupbuyordercode() {
        return groupbuyordercode;
    }

    public void setGroupbuyordercode(String groupbuyordercode) {
        this.groupbuyordercode = groupbuyordercode;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
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

    public boolean getdelete() {
        return isdelete;
    }

    public void setdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }

    public boolean getContractverify() {
        return contractverify;
    }

    public void setContractverify(boolean contractverify) {
        this.contractverify = contractverify;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPerformancecode() {
        return performancecode;
    }

    public void setPerformancecode(String performancecode) {
        this.performancecode = performancecode;
    }

    public String getGroupbuysupplycode() {
        return groupbuysupplycode;
    }

    public void setGroupbuysupplycode(String groupbuysupplycode) {
        this.groupbuysupplycode = groupbuysupplycode;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getQualifystatus() {
        return qualifystatus;
    }

    public void setQualifystatus(String qualifystatus) {
        this.qualifystatus = qualifystatus;
    }

    public LocalDate getDeliverydatestart() {
        return deliverydatestart;
    }

    public void setDeliverydatestart(LocalDate deliverydatestart) {
        this.deliverydatestart = deliverydatestart;
    }

    public LocalDate getDeliverydateend() {
        return deliverydateend;
    }

    public void setDeliverydateend(LocalDate deliverydateend) {
        this.deliverydateend = deliverydateend;
    }

    public BigDecimal getGroupbuyprice() {
        return groupbuyprice;
    }

    public void setGroupbuyprice(BigDecimal groupbuyprice) {
        this.groupbuyprice = groupbuyprice;
    }

    public LocalDateTime getGroupbuyenddate() {
        return groupbuyenddate;
    }

    public void setGroupbuyenddate(LocalDateTime groupbuyenddate) {
        this.groupbuyenddate = groupbuyenddate;
    }
}
