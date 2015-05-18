package demo.core.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by fanjun on 14-11-26.
 */
public class Quote implements Serializable {
    //报价表
    private int id;
    private int userid;
    private int demandid;                   //需求id
    private String demandcode;              //需求编号  (需求id和需求编号原本一个就好,为了方便页面处理字符和数字类型,都保留)
  /*  private int unitprice;                  //单价 (需求表的单价)*/
    private int supplyton;                  //申供瓶数
   /* private int lowcalorificvalue;          //低位热值 (需求更改后未使用,保留)*/
    private int quote;                      //报价
    private LocalDateTime lastupdatetime;   //最后更新时间 (因为报价表暂时不需要更新,所以此字段只作为第一次报价时记录时间)
    private boolean isdelete;                //是否删除
    private String status;                  //状态

    //增加提货时间和报价截止时间,方便定时任务修改报价信息的状态
    private String deliverymode;             //提货方式
    private LocalDate deliverydate;         //提货时间
    private LocalDate deliverydatestart;    //自提开始时间
    private LocalDate deliverydateend;      //自提截止时间
    private LocalDate quoteenddate;         //报价截止时间
    private String companyname;             //公司名称
    private String traderphone;             //交易员手机
    private String tradername;                //交易员姓名
    private String username;

    public Quote(){

    }

    public Quote(int userid, int demandid, String demandcode, int supplyton,int quote, LocalDateTime lastupdatetime, String status, String deliverymode, LocalDate deliverydate, LocalDate deliverydatestart, LocalDate deliverydateend, LocalDate quoteenddate,String companyname,String traderphone) {
        this.userid = userid;
        this.demandid = demandid;
        this.demandcode = demandcode;
        this.supplyton = supplyton;
        this.quote = quote;
        this.lastupdatetime = lastupdatetime;
        this.status = status;
        this.deliverymode = deliverymode;
        this.deliverydate = deliverydate;
        this.deliverydatestart = deliverydatestart;
        this.deliverydateend = deliverydateend;
        this.quoteenddate = quoteenddate;
        this.companyname = companyname;
        this.traderphone = traderphone;
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

    public int getDemandid() {
        return demandid;
    }

    public void setDemandid(int demandid) {
        this.demandid = demandid;
    }

    public String getDemandcode() {
        return demandcode;
    }

    public void setDemandcode(String demandcode) {
        this.demandcode = demandcode;
    }

    public int getSupplyton() {
        return supplyton;
    }

    public void setSupplyton(int supplyton) {
        this.supplyton = supplyton;
    }

    public int getQuote() {
        return quote;
    }

    public void setQuote(int quote) {
        this.quote = quote;
    }

    public LocalDateTime getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(LocalDateTime lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
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

    public LocalDate getDeliverydate() {
        return deliverydate;
    }

    public void setDeliverydate(LocalDate deliverydate) {
        this.deliverydate = deliverydate;
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

    public LocalDate getQuoteenddate() {
        return quoteenddate;
    }

    public void setQuoteenddate(LocalDate quoteenddate) {
        this.quoteenddate = quoteenddate;
    }

    public String getDeliverymode() {
        return deliverymode;
    }

    public void setDeliverymode(String deliverymode) {
        this.deliverymode = deliverymode;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getTraderphone() {
        return traderphone;
    }

    public void setTraderphone(String traderphone) {
        this.traderphone = traderphone;
    }

    public String getTradername() {
        return tradername;
    }

    public void setTradername(String tradername) {
        this.tradername = tradername;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
