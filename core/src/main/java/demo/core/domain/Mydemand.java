package demo.core.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by fanjun on 14-12-24.
 */
public class Mydemand implements Serializable {

    private int userid;
    private String demandcode;
    private LocalDateTime releasetime;
    private LocalDate quoteenddate;         //报价截止日期
    private int demandamount;               //需求数量
    private int quotenum;                   //报价人数
    private int purchasednum;               //已匹配数量
    private String status;                  //状态  (审核中,报价中,匹配中.....)

    //为便于spring定时任务修改状态,增加提货截止日期
    private LocalDate deliverydate;         //提货时间
    private LocalDate deliverydatestart;    //自提提货时间开始
    private LocalDate deliverydateend;      //自提提货时间截至

    public Mydemand(){

    }

    public Mydemand(String demandcode, LocalDateTime releasetime, LocalDate quoteenddate, int demandamount,String status,LocalDate deliverydate,LocalDate deliverydatestart,LocalDate deliverydateend) {
        this.demandcode = demandcode;
        this.releasetime = releasetime;
        this.quoteenddate = quoteenddate;
        this.demandamount = demandamount;
        this.status = status;
        this.deliverydate = deliverydate;
        this.deliverydatestart = deliverydatestart;
        this.deliverydateend = deliverydateend;
    }

    public Mydemand(int userid, String demandcode, LocalDateTime releasetime, LocalDate quoteenddate, int demandamount, String status,LocalDate deliverydate,LocalDate deliverydatestart,LocalDate deliverydateend) {
        this.userid = userid;
        this.demandcode = demandcode;
        this.releasetime = releasetime;
        this.quoteenddate = quoteenddate;
        this.demandamount = demandamount;
        this.status = status;
        this.deliverydate = deliverydate;
        this.deliverydatestart = deliverydatestart;
        this.deliverydateend = deliverydateend;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getDemandcode() {
        return demandcode;
    }

    public void setDemandcode(String demandcode) {
        this.demandcode = demandcode;
    }

    public LocalDateTime getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(LocalDateTime releasetime) {
        this.releasetime = releasetime;
    }

    public LocalDate getQuoteenddate() {
        return quoteenddate;
    }

    public void setQuoteenddate(LocalDate quoteenddate) {
        this.quoteenddate = quoteenddate;
    }

    public int getDemandamount() {
        return demandamount;
    }

    public void setDemandamount(int demandamount) {
        this.demandamount = demandamount;
    }

    public int getQuotenum() {
        return quotenum;
    }

    public void setQuotenum(int quotenum) {
        this.quotenum = quotenum;
    }

    public int getPurchasednum() {
        return purchasednum;
    }

    public void setPurchasednum(int purchasednum) {
        this.purchasednum = purchasednum;
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
}
