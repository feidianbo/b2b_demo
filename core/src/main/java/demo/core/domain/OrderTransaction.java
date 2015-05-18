package demo.core.domain;

import java.io.Serializable;

/**
 * Created by jack on 15/2/6.
 */
public class OrderTransaction implements Serializable{
    private String pname;              //产品
    private int amount;                //数量
    private String harbour;            //港口
    private String time;               //时间
    private int sellinfoid;            //sellinfo 表对应的id
    private String seller;             //卖家

    public OrderTransaction() {
    }

    public OrderTransaction(int amount, String harbour, String pname, String time, int sellinfoid, String seller) {
        this.amount = amount;
        this.harbour = harbour;
        this.pname = pname;
        this.time = time;
        this.sellinfoid = sellinfoid;
        this.seller = seller;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getHarbour() {
        return harbour;
    }

    public void setHarbour(String harbour) {
        this.harbour = harbour;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSellinfoid() {
        return sellinfoid;
    }

    public void setSellinfoid(int sellinfoid) {
        this.sellinfoid = sellinfoid;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

}
