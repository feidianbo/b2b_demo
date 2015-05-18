package demo.core.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by xiangyang on 14-12-17.
 */
public class ManualSell  implements Serializable {
    private int id;
    private String manualsellId;
    private int userId;
    //类型 1.人工找货  0.人工销售
    private boolean type;
    //低位热值
    private int lowcalorificvalue;
    //空干基硫分
    private BigDecimal airdrybasissulfur;
    //收到基硫分
    private BigDecimal receivebasissulfur;
    //酒类指标2
    private BigDecimal airdrybasisvolatile;
    //省份片区
    private String deliveryDistrict;
    //省份
    private String deliveryProvince;
    //提货详细地址
    private String deliveryAddr;
    private String deliveryOtherPlace;
    //提货方式
    private String deliveryMode;
    //需求数量
    private int demandAmount;
    //供货开始时间
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate deliveryStartDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate deliveryEndDate;
    //联系人
    private String contactName;
    //联系电话
    private String phone;
    //公司名称
    private String companyName;
    //煤种
    private String coalType;
    private LocalDate createDatetime;
    //验证码，数据库没有这个字段
    private String verifyCode;
    private String dateCondition;

    private Integer regionId;
    private Integer provinceId;
    private Integer portId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLowcalorificvalue() {
        return lowcalorificvalue;
    }

    public void setLowcalorificvalue(int lowcalorificvalue) {
        this.lowcalorificvalue = lowcalorificvalue;
    }

    public BigDecimal getAirdrybasissulfur() {
        return airdrybasissulfur;
    }

    public void setAirdrybasissulfur(BigDecimal airdrybasissulfur) {
        this.airdrybasissulfur = airdrybasissulfur;
    }

    public String getDeliveryDistrict() {
        return deliveryDistrict;
    }

    public void setDeliveryDistrict(String deliveryDistrict) {
        this.deliveryDistrict = deliveryDistrict;
    }

    public String getDeliveryProvince() {
        return deliveryProvince;
    }

    public void setDeliveryProvince(String deliveryProvince) {
        this.deliveryProvince = deliveryProvince;
    }

    public String getDeliveryAddr() {
        return deliveryAddr;
    }

    public void setDeliveryAddr(String deliveryAddr) {
        this.deliveryAddr = deliveryAddr;
    }

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public int getDemandAmount() {
        return demandAmount;
    }

    public void setDemandAmount(int demandAmount) {
        this.demandAmount = demandAmount;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public void setType(boolean type) {
        this.type = type;
    }

    public String getDeliveryOtherPlace() {
        return deliveryOtherPlace;
    }

    public void setDeliveryOtherPlace(String deliveryOtherPlace) {
        this.deliveryOtherPlace = deliveryOtherPlace;
    }

    public boolean getType() {
        return type;
    }

    public LocalDate getDeliveryStartDate() {
        return deliveryStartDate;
    }

    public void setDeliveryStartDate(LocalDate deliveryStartDate) {
        this.deliveryStartDate = deliveryStartDate;
    }

    public LocalDate getDeliveryEndDate() {
        return deliveryEndDate;
    }

    public void setDeliveryEndDate(LocalDate deliveryEndDate) {
        this.deliveryEndDate = deliveryEndDate;
    }

    public BigDecimal getAirdrybasisvolatile() {
        return airdrybasisvolatile;
    }

    public void setAirdrybasisvolatile(BigDecimal airdrybasisvolatile) {
        this.airdrybasisvolatile = airdrybasisvolatile;
    }

    public BigDecimal getReceivebasissulfur() {
        return receivebasissulfur;
    }

    public void setReceivebasissulfur(BigDecimal receivebasissulfur) {
        this.receivebasissulfur = receivebasissulfur;
    }

    public String getCoalType() {
        return coalType;
    }

    public void setCoalType(String coalType) {
        this.coalType = coalType;
    }

    public LocalDate getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(LocalDate createDatetime) {
        this.createDatetime = createDatetime;
    }
    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getManualsellId() {
        return manualsellId;
    }

    public void setManualsellId(String manualsellId) {
        this.manualsellId = manualsellId;
    }

    public String getDateCondition() {
        return dateCondition;
    }

    public void setDateCondition(String dateCondition) {
        this.dateCondition = dateCondition;
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

    public Integer getPortId() {
        return portId;
    }

    public void setPortId(Integer portId) {
        this.portId = portId;
    }
}
