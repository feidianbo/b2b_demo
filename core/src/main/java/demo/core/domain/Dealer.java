package demo.core.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jack on 15/1/9.
 */
public class Dealer implements Serializable {
    private int id;
    private String deliveryregion;                   //区域
    private String deliveryprovince;                 //省市
    private String deliveryplace;                    //港口
    private String dealerid;                         //交易员编号
    private String dealername;                       //交易员姓名
    private String dealerphone;                      //交易员电话
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createtime;                //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastupdatetime;            //最后一次更新时间
    private int lastupdatemanid;                     //最后一次更新， 操作人id
    private String lastupdatemanname;                //最后一次更新， 操作人name
    private String status;                           //是否在职，分为在职，已离职，已删除
    private Integer regionId;
    private Integer provinceId;
    private Integer portId;
    private List<Areaport> ports;


    public Dealer() {
    }

    public Dealer(String dealerid, String dealername){
        this.dealerid = dealerid;
        this.dealername = dealername;
    }

    public Dealer(String dealerid, String dealername, String dealerphone, String deliveryregion, String deliveryprovince, String deliveryplace, int lastupdatemanid, String lastupdatemanname) {
        this.dealerid =dealerid;
        this.dealername = dealername;
        this.dealerphone = dealerphone;
        this.deliveryregion = deliveryregion;
        this.deliveryprovince = deliveryprovince;
        this.deliveryplace = deliveryplace;
        this.lastupdatemanid = lastupdatemanid;
        this.lastupdatemanname = lastupdatemanname;
    }

    public Dealer(String dealername, String dealerphone, String deliveryregion, String deliveryprovince, String deliveryplace, int lastupdatemanid, String lastupdatemanname) {
        this.dealername = dealername;
        this.dealerphone = dealerphone;
        this.deliveryregion = deliveryregion;
        this.deliveryprovince = deliveryprovince;
        this.deliveryplace = deliveryplace;
        this.lastupdatemanid = lastupdatemanid;
        this.lastupdatemanname = lastupdatemanname;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public String getDealerid() {
        return dealerid;
    }

    public void setDealerid(String dealerid) {
        this.dealerid = dealerid;
    }

    public String getDealername() {
        return dealername;
    }

    public void setDealername(String dealername) {
        this.dealername = dealername;
    }

    public String getDealerphone() {
        return dealerphone;
    }

    public void setDealerphone(String dealerphone) {
        this.dealerphone = dealerphone;
    }

    public String getDeliveryplace() {
        return deliveryplace;
    }

    public void setDeliveryplace(String deliveryplace) {
        this.deliveryplace = deliveryplace;
    }

    public String getDeliveryprovince() {
        return deliveryprovince;
    }

    public void setDeliveryprovince(String deliveryprovince) {
        this.deliveryprovince = deliveryprovince;
    }

    public String getDeliveryregion() {
        return deliveryregion;
    }

    public void setDeliveryregion(String deliveryregion) {
        this.deliveryregion = deliveryregion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastupdatemanname() {
        return lastupdatemanname;
    }

    public void setLastupdatemanname(String lastupdatemanname) {
        this.lastupdatemanname = lastupdatemanname;
    }

    public int getLastupdatemanid() {
        return lastupdatemanid;
    }

    public void setLastupdatemanid(int lastupdatemanid) {
        this.lastupdatemanid = lastupdatemanid;
    }

    public LocalDateTime getLastupdatetime() {
        return lastupdatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public List<Areaport> getPorts() {
        return ports;
    }

    public void setPorts(List<Areaport> ports) {
        this.ports = ports;
    }

    public Integer getPortId() {
        return portId;
    }

    public void setPortId(Integer portId) {
        this.portId = portId;
    }
}
