package demo.core.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by jack on 14/12/27.
 */
public class OrdersInfo implements Serializable {
    private int id;
    private EnumOrder status;                   //订单状态
    private String operateman;               //操作人
    private int operatemanid;                //操作人id
    private LocalDateTime createtime;        //创建时间
    private LocalDateTime lastupdatetime;    //最后一次更新时间
    private int oid;                      //orders表对应的id
    private String orderid;                  //orderid
    private String remarks;                  //备注

    public OrdersInfo() {
    }

    public OrdersInfo(EnumOrder status, String operateman, int operatemanid, int oid, String orderid, String remarks) {
        this.oid = oid;
        this.operateman = operateman;
        this.operatemanid = operatemanid;
        this.orderid = orderid;
        this.remarks = remarks;
        this.status = status;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getLastupdatetime() {
        return lastupdatetime;
    }

    public int getOid() {
        return oid;
    }

    public String getOperateman() {
        return operateman;
    }

    public int getOperatemanid() {
        return operatemanid;
    }

    public String getOrderid() {
        return orderid;
    }

    public String getRemarks() {
        return remarks;
    }

    public EnumOrder getStatus() {
        return status;
    }

    public void setStatus(EnumOrder status) {
        this.status = status;
    }
}
