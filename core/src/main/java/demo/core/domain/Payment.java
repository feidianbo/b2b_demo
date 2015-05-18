package demo.core.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by jack on 14/12/30.
 */
public class Payment {
    private int id;                         //id
    private int orderid;                    //order表对应的id
    private String oid;                     //orderid
    private int squence;                    //次序，第几个支付凭证
    private boolean issuccess;              //财务审核是否成功
    private BigDecimal money;               //此支付凭证上的金额
    private String verifyman;               //审核人
    private int verifymanid;                //审核人id
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime verifymantime;    //审核时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createtime;       //上传时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastupdatetime;   //最后一次更新时间
    private int userid;                     //上传人id
    private String username;                //上传人name
    private boolean isverified;             //此凭证是否已经被审核
    private String pictureurl;              //支付凭证存储路径


    public Payment() {
    }

    public Payment(int orderid, int userid, String username, String pictureurl) {
        this.orderid = orderid;
        this.userid = userid;
        this.username = username;
        this.pictureurl = pictureurl;
    }

    public Payment(String oid, int orderid, int userid, String username, String pictureurl) {
        this.oid = oid;
        this.orderid = orderid;
        this.userid = userid;
        this.username = username;
        this.pictureurl = pictureurl;
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

    public boolean isIssuccess() {
        return issuccess;
    }

    public void setIssuccess(boolean issuccess) {
        this.issuccess = issuccess;
    }

    public boolean isIsverified() {
        return isverified;
    }

    public void setIsverified(boolean isverified) {
        this.isverified = isverified;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
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

    public int getSquence() {
        return squence;
    }

    public void setSquence(int squence) {
        this.squence = squence;
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

    public String getVerifyman() {
        return verifyman;
    }

    public void setVerifyman(String verifyman) {
        this.verifyman = verifyman;
    }

    public int getVerifymanid() {
        return verifymanid;
    }

    public void setVerifymanid(int verifymanid) {
        this.verifymanid = verifymanid;
    }

    public LocalDateTime getVerifymantime() {
        return verifymantime;
    }

    public void setVerifymantime(LocalDateTime verifymantime) {
        this.verifymantime = verifymantime;
    }

    public LocalDateTime getLastupdatetime() {
        return lastupdatetime;
    }

    public String getPictureurl() {
        return pictureurl;
    }

    public void setPictureurl(String pictureurl) {
        this.pictureurl = pictureurl;
    }

}
