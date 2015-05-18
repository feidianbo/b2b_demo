package demo.core.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jack on 14/11/25.
 */
public class SellInfo extends CoalBaseData implements Serializable {
    private int id;
    private String pid;
    private EnumSellInfo status;
    private String pname;
    private Integer ykj;                        //一口价
    private String seller;                  //自营还是其他卖家
    private String deliveryregion;          //提货地区，华东地区，西南地区等
    private String deliveryprovince;        //提货省份
    private String deliveryplace;           //提货地
    private String deliverymode;            //提货方式
    private LocalDate deliverytime1;        //提货时间1
    private LocalDate deliverytime2;        //提货时间2
    private int supplyquantity;             //供应数量
    private int soldquantity;               //已售数量
    private int availquantity;              //可售数量
    private String inspectorg;              //检验机构
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createtime;       //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastupdatetime;   //最后一次更新时间
    private int sellerid;                   //卖家id
    private String remarks;                 //审核信息备注
    private String otherharbour;            //其它港口
    private String otherinspectorg;         //其它检验机构
    private int jtjlast;                    //阶梯价，最低价
    private LocalDate createdate;           //createdate
    private String dealerid;                //交易员编号
    private String dealername;              //交易员姓名
    private String dealerphone;             //交易员电话
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime verifytime;       //审核时间
    private String producttype;             //产品类型，例如：推荐等
    private List<PriceLadder> pricelist;    //阶梯价列表
    private String type;                    //判断是否重新发布

    private Integer regionId;
    private Integer provinceId;
    private Integer portId;
    private int traderid;

    private int viewtimes;                  //浏览次数
    private String originplace;             //产地
    private int paymode;                    //付款方式
    private BigDecimal payperiod;           //到账周期
    private String releaseremarks;          //发布备注

    private int parentid;                   //原产品id
    private int editnum;                    //修改次数
    private int version;                    //版本号

    public boolean isLinktype() {
        return linktype;
    }

    public void setLinktype(boolean linktype) {
        this.linktype = linktype;
    }

    public String getLinkmanname() {
        return linkmanname;
    }

    public void setLinkmanname(String linkmanname) {
        this.linkmanname = linkmanname;
    }

    public String getLinkmanphone() {
        return linkmanphone;
    }

    public void setLinkmanphone(String linkmanphone) {
        this.linkmanphone = linkmanphone;
    }

    private boolean linktype;               //联系方式
    private String linkmanname;             //客户联系人
    private String linkmanphone;            //客户联系人手机号

    public SellInfo(){}

    public SellInfo(String deliveryregion,String deliveryprovince,String deliveryplace,EnumSellInfo status, String pname, Integer NCV, BigDecimal ADS, BigDecimal RS, BigDecimal TM, BigDecimal IM, BigDecimal ADV, BigDecimal RV, BigDecimal ASH, Integer AFT, Integer HGI, Integer ykj, String seller, String otherharbour, String deliverymode, LocalDate deliverytime1, LocalDate deliverytime2, int supplyquantity, int availquantity, String inspectorg, String otherinspectorg, LocalDateTime createtime, int sellerid, String remarks, int traderid, String dealername, String dealerphone, String producttype,Integer regionId,Integer provinceId,Integer portId,String originplace,String releaseremarks) {
        super(NCV, ADS, RS, TM, IM, ADV, RV, AFT, ASH, HGI);
        this.status = status;
        this.pname = pname;
        this.ykj = ykj;
        this.seller = seller;
        this.otherharbour = otherharbour;
        this.deliverymode = deliverymode;
        this.deliverytime1 = deliverytime1;
        this.deliverytime2 = deliverytime2;
        this.supplyquantity = supplyquantity;
        this.availquantity = availquantity;
        this.inspectorg = inspectorg;
        this.otherinspectorg = otherinspectorg;
        this.createtime = createtime;
        this.sellerid = sellerid;
        this.remarks = remarks;
        this.traderid = traderid;
        this.dealername = dealername;
        this.dealerphone = dealerphone;
        this.producttype = producttype;
        this.regionId=regionId;
        this.provinceId=provinceId;
        this.portId=portId;
        this.originplace = originplace;
        this.releaseremarks = releaseremarks;
        this.deliveryregion = deliveryregion;
        this.deliveryprovince = deliveryprovince;
        this.deliveryplace = deliveryplace;

    }

    public SellInfo(String type,EnumSellInfo status, String pname, Integer NCV, BigDecimal ADS, BigDecimal RS, BigDecimal TM, BigDecimal IM, BigDecimal ADV, BigDecimal RV, BigDecimal ASH, Integer AFT, Integer HGI, Integer ykj, String seller, String deliveryregion, String deliveryprovince, String deliveryplace, String otherharbour, String deliverymode, LocalDate deliverytime1, LocalDate deliverytime2, int supplyquantity, int availquantity, String inspectorg, String otherinspectorg, LocalDateTime createtime, int sellerid, String dealerid, String dealername, String dealerphone, String producttype,Integer regionId,Integer provinceId,Integer portId,String originplace,String releaseremarks) {
        super(NCV, ADS, RS, TM, IM, ADV, RV, AFT, ASH, HGI);
        this.status = status;
        this.pname = pname;
        this.ykj = ykj;
        this.seller = seller;
        this.deliveryregion = deliveryregion;
        this.deliveryprovince = deliveryprovince;
        this.deliveryplace = deliveryplace;
        this.otherharbour = otherharbour;
        this.deliverymode = deliverymode;
        this.deliverytime1 = deliverytime1;
        this.deliverytime2 = deliverytime2;
        this.supplyquantity = supplyquantity;
        this.availquantity = availquantity;
        this.inspectorg = inspectorg;
        this.otherinspectorg = otherinspectorg;
        this.createtime = createtime;
        this.sellerid = sellerid;
        this.dealerid = dealerid;
        this.dealername = dealername;
        this.dealerphone = dealerphone;
        this.producttype = producttype;
        this.type=type;
        this.regionId=regionId;
        this.provinceId=provinceId;
        this.portId=portId;
        this.originplace = originplace;
        this.releaseremarks = releaseremarks;
    }

    public SellInfo(EnumSellInfo status, String pname, Integer NCV, BigDecimal ADS, BigDecimal RS, BigDecimal TM, BigDecimal IM, BigDecimal ADV, BigDecimal RV, BigDecimal ASH, Integer AFT, Integer HGI, Integer ykj, String seller, String deliveryregion, String deliveryprovince, String deliveryplace, String otherharbour, String deliverymode, LocalDate deliverytime1, LocalDate deliverytime2, int supplyquantity, int availquantity, String inspectorg, String otherinspectorg, LocalDateTime createtime, int sellerid, Integer regionId,Integer provinceId,Integer portId,String originplace,int paymode,BigDecimal payperiod, String releaseremarks, boolean linktype, String linkmanname, String linkmanphone, int editnum, int parentid) {
        super(NCV, ADS, RS, TM, IM, ADV, RV, AFT, ASH, HGI);
        this.status = status;
        this.pname = pname;
        this.ykj = ykj;
        this.seller = seller;
        this.deliveryregion = deliveryregion;
        this.deliveryprovince = deliveryprovince;
        this.deliveryplace = deliveryplace;
        this.otherharbour = otherharbour;
        this.deliverymode = deliverymode;
        this.deliverytime1 = deliverytime1;
        this.deliverytime2 = deliverytime2;
        this.supplyquantity = supplyquantity;
        this.availquantity = availquantity;
        this.inspectorg = inspectorg;
        this.otherinspectorg = otherinspectorg;
        this.createtime = createtime;
        this.sellerid = sellerid;
        this.regionId=regionId;
        this.provinceId=provinceId;
        this.portId=portId;
        this.originplace = originplace;
        this.paymode = paymode;
        this.payperiod = payperiod;
        this.releaseremarks = releaseremarks;
        this.linktype = linktype;
        this.linkmanname = linkmanname;
        this.linkmanphone = linkmanphone;
        this.editnum = editnum;
        this.parentid = parentid;
    }

    public SellInfo(int id, String pname, Integer NCV, BigDecimal ADS, BigDecimal RS, BigDecimal TM, BigDecimal IM, BigDecimal ADV, BigDecimal RV, BigDecimal ASH, Integer AFT, Integer HGI, int ykj, String seller, String otherharbour, String deliverymode, LocalDate deliverytime1, LocalDate deliverytime2, int supplyquantity, int availquantity, String inspectorg, String otherinspectorg, LocalDateTime createtime, String remarks, int traderid, String dealername, String dealerphone, String producttype,Integer regionId,Integer provinceId,Integer portId,String originplace,String releaseremarks, int version) {
        super(NCV, ADS, RS, TM, IM, ADV, RV, AFT, ASH, HGI);
        this.id = id;
        this.pname = pname;
        this.ykj = ykj;
        this.seller = seller;
        this.otherharbour = otherharbour;
        this.deliverymode = deliverymode;
        this.deliverytime1 = deliverytime1;
        this.deliverytime2 = deliverytime2;
        this.supplyquantity = supplyquantity;
        this.availquantity = availquantity;
        this.inspectorg = inspectorg;
        this.otherinspectorg = otherinspectorg;
        this.createtime = createtime;
        this.remarks = remarks;
        this.traderid = traderid;
        this.dealername = dealername;
        this.dealerphone = dealerphone;
        this.producttype = producttype;
        this.regionId=regionId;
        this.provinceId=provinceId;
        this.portId=portId;
        this.originplace = originplace;
        this.releaseremarks = releaseremarks;
        this.version = version;
    }

    public SellInfo(int id, String pname, EnumSellInfo status, Integer NCV, BigDecimal ADS, BigDecimal RS, BigDecimal TM, BigDecimal IM, BigDecimal ADV, BigDecimal RV, BigDecimal ASH, Integer AFT, Integer HGI, int ykj, String seller, String deliveryregion, String deliveryprovince, String deliveryplace, String otherharbour, String deliverymode, LocalDate deliverytime1, LocalDate deliverytime2, int supplyquantity, int availquantity, String inspectorg, String otherinspectorg, LocalDateTime createtime, Integer regionId,Integer provinceId,Integer portId,String originplace,int paymode,BigDecimal payperiod,String releaseremarks, boolean linktype, String linkmanname, String linkmanphone, int version) {
        super(NCV, ADS, RS, TM, IM, ADV, RV, AFT, ASH, HGI);
        this.id = id;
        this.pname = pname;
        this.status = status;
        this.ykj = ykj;
        this.seller = seller;
        this.deliveryregion = deliveryregion;
        this.deliveryprovince = deliveryprovince;
        this.deliveryplace = deliveryplace;
        this.otherharbour = otherharbour;
        this.deliverymode = deliverymode;
        this.deliverytime1 = deliverytime1;
        this.deliverytime2 = deliverytime2;
        this.supplyquantity = supplyquantity;
        this.availquantity = availquantity;
        this.inspectorg = inspectorg;
        this.otherinspectorg = otherinspectorg;
        this.createtime = createtime;
        this.regionId=regionId;
        this.provinceId=provinceId;
        this.portId=portId;
        this.originplace = originplace;
        this.paymode = paymode;
        this.payperiod = payperiod;
        this.releaseremarks = releaseremarks;
        this.linktype = linktype;
        this.linkmanname = linkmanname;
        this.linkmanphone = linkmanphone;
        this.version = version;
    }

    public List<PriceLadder> getPricelist() {
        return pricelist;
    }

    public void setPricelist(List<PriceLadder> pricelist) {
        this.pricelist = pricelist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public EnumSellInfo getStatus() {
        return status;
    }

    public void setStatus(EnumSellInfo status) {
        this.status = status;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getYkj() {
        return ykj;
    }

    public void setYkj(Integer ykj) {
        this.ykj = ykj;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getDeliveryplace() {
        return deliveryplace;
    }

    public String getDeliveryregion() {
        return deliveryregion;
    }

    public void setDeliveryregion(String deliveryregion) {
        this.deliveryregion = deliveryregion;
    }

    public String getDeliveryprovince() {
        return deliveryprovince;
    }

    public void setDeliveryprovince(String deliveryprovince) {
        this.deliveryprovince = deliveryprovince;
    }

    public void setDeliveryplace(String deliveryplace) {
        this.deliveryplace = deliveryplace;
    }

    public String getOtherharbour() {
        return otherharbour;
    }

    public void setOtherharbour(String otherharbour) {
        this.otherharbour = otherharbour;
    }

    public String getDeliverymode() {
        return deliverymode;
    }

    public void setDeliverymode(String deliverymode) {
        this.deliverymode = deliverymode;
    }

    public LocalDate getDeliverytime1() {
        return deliverytime1;
    }

    public void setDeliverytime1(LocalDate deliverytime1) {
        this.deliverytime1 = deliverytime1;
    }

    public LocalDate getDeliverytime2() {
        return deliverytime2;
    }

    public void setDeliverytime2(LocalDate deliverytime2) {
        this.deliverytime2 = deliverytime2;
    }

    public int getSupplyquantity() {
        return supplyquantity;
    }

    public void setSupplyquantity(int supplyquantity) {
        this.supplyquantity = supplyquantity;
    }

    public int getSoldquantity() {
        return soldquantity;
    }

    public void setSoldquantity(int soldquantity) {
        this.soldquantity = soldquantity;
    }

    public int getAvailquantity() {
        return availquantity;
    }

    public void setAvailquantity(int availquantity) {
        this.availquantity = availquantity;
    }

    public String getInspectorg() {
        return inspectorg;
    }

    public void setInspectorg(String inspectorg) {
        this.inspectorg = inspectorg;
    }

    public String getOtherinspectorg() {
        return otherinspectorg;
    }

    public void setOtherinspectorg(String otherinspectorg) {
        this.otherinspectorg = otherinspectorg;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public LocalDateTime getLastupdatetime() {
        return lastupdatetime;
    }

    public int getSellerid() {
        return sellerid;
    }

    public void setSellerid(int sellerid) {
        this.sellerid = sellerid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getJtjlast() {
        return jtjlast;
    }

    public void setJtjlast(int jtjlast) {
        this.jtjlast = jtjlast;
    }

    public LocalDate getCreatedate() {
        return createdate;
    }

    public void setCreatedate(LocalDate createdate) {
        this.createdate = createdate;
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

    public LocalDateTime getVerifytime() {
        return verifytime;
    }

    public void setVerifytime(LocalDateTime verifytime) {
        this.verifytime = verifytime;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    public int getViewtimes() {
        return viewtimes;
    }

    public void setViewtimes(int viewtimes) {
        this.viewtimes = viewtimes;

    }

    public String getReleaseremarks() {
        return releaseremarks;
    }

    public void setReleaseremarks(String releaseremarks) {
        this.releaseremarks = releaseremarks;
    }

    public int getPaymode() {
        return paymode;
    }

    public void setPaymode(int paymode) {
        this.paymode = paymode;
    }

    public BigDecimal getPayperiod() {
        return payperiod;
    }

    public void setPayperiod(BigDecimal payperiod) {
        this.payperiod = payperiod;
    }

    public String getOriginplace() {
        return originplace;
    }

    public void setOriginplace(String originplace) {
        this.originplace = originplace;
    }


    public int getTraderid() {
        return traderid;
    }

    public void setTraderid(int traderid) {
        this.traderid = traderid;
    }
    public void setLastupdatetime(LocalDateTime lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public int getEditnum() {
        return editnum;
    }

    public void setEditnum(int editnum) {
        this.editnum = editnum;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;

    }
}







