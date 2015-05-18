package demo.site.controller;

import com.mysql.jdbc.StringUtils;
import demo.core.domain.*;
import demo.core.persistence.*;
import demo.core.util.PageQueryParam;
import demo.site.basic.JsonController;
import demo.site.basic.annotation.LoginRequired;
import demo.site.basic.exception.NotFoundException;
import demo.site.service.Auth;
import demo.site.service.BuyService;
import demo.site.service.KittHandlerExceptionResolver;
import demo.site.service.Session;
import org.eclipse.jetty.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.sql.SQLDataException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jack on 14/11/25.
 */
@Controller
public class BuyController extends JsonController {
    @Autowired
    private Session session;
    @Autowired
    private BuyMapper buyMapper;
    @Autowired
    private BuyService buyService;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private AreaportMapper areaportMapper;
    @Autowired
    private DemandMapper demandMapper;
    @Autowired
    private MyInterestMapper myInterestMapper;
    @Autowired
    private KittHandlerExceptionResolver kittHandlerExceptionResolver;
    @Autowired
    private Auth auth;
    @Autowired
    private DictionaryMapper dictionaryMapper;

    @RequestMapping("/buy/info")
    public String getBuyDetailInfo(Integer id, String type, Map<String, Object> model) {
        buyService.getSellInfoDetail(id, model);
        if(!StringUtils.isNullOrEmpty(type)){
            model.put("type",type);
        }
        return "fixedInfo";
    }

    @RequestMapping("/mall/info")
    public String getMallDetailInfo(Integer id, Map<String, Object> model,String type) {
        buyService.getSellInfoDetail(id, model);
        if(!StringUtils.isNullOrEmpty(type)){
            model.put("type",type);
        }
        return "fixedInfo";
    }

    //加入我的关注
    @RequestMapping("/buy/addInterest")
    @ResponseBody
    @LoginRequired
    public Object addMyInterest(@RequestParam("id") int sid, String type) {
        SellInfo sellInfo = null;
        Demand demand = null;
        if (type.equals("supply")) {
            sellInfo = buyMapper.getSellInfoById(sid);
        } else if (type.equals("demand")) {
            demand = demandMapper.getDemandById(sid);
        }
        if (sellInfo == null && demand == null) throw new NotFoundException();
        if (myInterestMapper.getMyInterestBySid(sid, session.getUser().getId(), type) == null) {
            if (type.equals("supply")) {
                int price = sellInfo.getYkj() == 0 ? sellInfo.getJtjlast() : sellInfo.getYkj();
                myInterestMapper.addMyInterest(new MyInterest(sellInfo.getPid(), sid, sellInfo.getPname(), sellInfo.getSeller(), price, sellInfo.getSupplyquantity(), sellInfo.getNCV(), session.getUser().getId(), type));
            } else if (type.equals("demand")) {
                myInterestMapper.addMyInterest(new MyInterest(demand.getDemandcode(), sid, demand.getCoaltype(), demand.getDemandcustomer(), 0, demand.getDemandamount(), demand.getNCV(), session.getUser().getId(), type));
            }
        } else {
            if (myInterestMapper.getMyInterestBySid(sid, session.getUser().getId(), type).isIsdelete()) {
                myInterestMapper.setMyInterestStatusBySid(sid, session.getUser().getId(), type);
            }
        }
        return true;
    }

    /**
     *
     * 已经发布供应信息修改，增加一条新的待审核的供应信息，原供应信息下架
     * @param sid
     * @param ykj
     * @param jtj01
     * @param amount12
     * @param jtj02
     * @param amount21
     * @param amount22
     * @param jtj03
     * @param amount31
     * @param amount32
     * @param jtj04
     * @param amount41
     * @param amount42
     * @param jtj05
     * @param amount51
     * @param amount52
     * @param deliverytime1
     * @param deliverytime2
     * @param paymode
     * @param payperiod
     * @param releaseremarks
     * @return
     */
    @RequestMapping(value = "/editSellinfo", method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired
    public Object editSellInfo(
            @RequestParam(value = "id", required = true, defaultValue = "0") int sid,
            @RequestParam(value = "ykj", required = false, defaultValue = "0") int ykj,
            @RequestParam(value = "jtj01", required = false, defaultValue = "0") int jtj01,
            @RequestParam(value = "amount12", required = false, defaultValue = "0") int amount12,
            @RequestParam(value = "jtj02", required = false, defaultValue = "0") int jtj02,
            @RequestParam(value = "amount21", required = false, defaultValue = "0") int amount21,
            @RequestParam(value = "amount22", required = false, defaultValue = "0") int amount22,
            @RequestParam(value = "jtj03", required = false, defaultValue = "0") int jtj03,
            @RequestParam(value = "amount31", required = false, defaultValue = "0") int amount31,
            @RequestParam(value = "amount32", required = false, defaultValue = "0") int amount32,
            @RequestParam(value = "jtj04", required = false, defaultValue = "0") int jtj04,
            @RequestParam(value = "amount41", required = false, defaultValue = "0") int amount41,
            @RequestParam(value = "amount42", required = false, defaultValue = "0") int amount42,
            @RequestParam(value = "jtj05", required = false, defaultValue = "0") int jtj05,
            @RequestParam(value = "amount51", required = false, defaultValue = "0") int amount51,
            @RequestParam(value = "amount52", required = false, defaultValue = "0") int amount52,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "deliverytime1", required = true) LocalDate deliverytime1,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "deliverytime2", required = true) LocalDate deliverytime2,
            @RequestParam(value = "paymode", required = true) int paymode,
            @RequestParam(value = "supplyquantity", required = true) int supplyquantity,
            @RequestParam(value = "payperiod", required = false) BigDecimal payperiod,
            @RequestParam(value = "releaseremarks", required = false) String releaseremarks,
            @RequestParam(value = "linktype", required = true)boolean linktype,
            @RequestParam(value = "linkmanname", required = false, defaultValue = "")String linkmanname,
            @RequestParam(value = "linkmanphone", required = false, defaultValue = "")String linkmanphone,
            @RequestParam(value = "version", required = false, defaultValue = "0")int version){
        int id = sid;
        boolean success = false;
        Map<String, Object> map = new HashMap<String, Object>();
        List<PriceLadder> priceLadderList = new ArrayList<>();
        Integer[] priceList = new Integer[]{jtj01, jtj02, jtj03, jtj04, jtj05};
        Integer[] amount1List = new Integer[]{0, amount21, amount31, amount41, amount51};
        Integer[] amount2List = new Integer[]{amount12, amount22, amount32, amount42, amount52};
        if(paymode != 2){//账期
            payperiod = new BigDecimal(0);
        }
        String error = "";
        if (deliverytime1.isBefore(LocalDate.now().minusDays(1))) {
            error = "before";
            map.put("id", id);
            map.put("success", false);
            map.put("error", error);
            return map;
        }
        SellInfo sellInfoToAdd = buyMapper.getSellInfoById(sid);
        // 供货数量<已售数量，返回错误
        int availableQuantity = supplyquantity - sellInfoToAdd.getSoldquantity() ;
        if (availableQuantity < 0) {
            error = "wrongQuantity";
            map.put("id", id);
            map.put("success", false);
            map.put("error", error);
            return map;
        }
        if(!linktype){
            linkmanname = "";
            linkmanphone = "";
        }
        int i = 0;
        int jtjLast = jtj01;
        while(i < priceList.length && priceList[i] > 0){
            priceLadderList.add(new PriceLadder(i+1, priceList[i], amount1List[i], amount2List[i]));
            jtjLast = jtjLast > priceList[i] ? priceList[0] : jtjLast;
            i++;
        }
        sellInfoToAdd.setAvailquantity(availableQuantity);
        sellInfoToAdd.setYkj(ykj);
        sellInfoToAdd.setDeliverytime1(deliverytime1);
        sellInfoToAdd.setDeliverytime2(deliverytime2);
        sellInfoToAdd.setPaymode(paymode);
        sellInfoToAdd.setSupplyquantity(supplyquantity);
        sellInfoToAdd.setPayperiod(payperiod);
        sellInfoToAdd.setReleaseremarks(releaseremarks);
        sellInfoToAdd.setLinktype(linktype);
        sellInfoToAdd.setLinkmanname(linkmanname);
        sellInfoToAdd.setLinkmanphone(linkmanphone);
        sellInfoToAdd.setVersion(version);
        // 新修改时间，状态改为WaitVerify
        sellInfoToAdd.setLastupdatetime(LocalDateTime.now());
        // 更改审核通过的记录VerifyPass：插入新记录，editnum+1，新加阶梯价，新id
        if(ykj ==0 && (jtj01 == 0 || jtj02 == 0)){
            throw new NotFoundException();
        } else if (EnumSellInfo.VerifyPass.equals(sellInfoToAdd.getStatus())) {
            if(0 == sellInfoToAdd.getParentid()){
                sellInfoToAdd.setParentid(sid);
            }
            sellInfoToAdd.setStatus(EnumSellInfo.WaitVerify);
            sellInfoToAdd.setEditnum(sellInfoToAdd.getEditnum() + 1);
            // 新id
            id = buyService.addSellinfoForUpdate(sellInfoToAdd, sid, version, priceLadderList, jtjLast) ;
            success = true;
        } else {
            sellInfoToAdd.setStatus(EnumSellInfo.WaitVerify);
            // 更改其他状态的记录：更新原记录，原editnum，更新阶梯价，原id
            sellInfoToAdd.setStatus(EnumSellInfo.WaitVerify);
            buyService.updateSellInfo(sellInfoToAdd, priceLadderList, jtjLast);
            success = true;
            map.put("id", sellInfoToAdd.getId());
        }
        map.put("id", id);
        map.put("success", success);
        map.put("error", error);
        return map ;
    }

    //获取sellinfo修改历史信息表
    @RequestMapping(value = "/getSellInfoEditHist", method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired
    public String getSellInfoEditHist(@RequestParam(value = "id", required = false, defaultValue = "0") int sid,
                                      Map<String, Object> model){
        SellInfo sellInfo =  buyMapper.getSellInfoById(sid)  ;
        List<SellInfo> sellInfoList = buyMapper.getSellInfoEditHist(sid,sellInfo.getParentid());
        model.put("sellInfoEditHist", sellInfoList);
        return "";
    }


    @RequestMapping(value = "/addSellinfo", method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired
    public Object doAddSellinfo(@RequestParam(value = "id", required = false, defaultValue = "0") int sid,
                                @RequestParam(value = "pname", required = true) String pname,
                                @RequestParam(value = "NCV", required = true) int NCV,
                                @RequestParam(value = "RS", required = true) BigDecimal RS,
                                @RequestParam(value = "ADS", required = false, defaultValue = "0") BigDecimal ADS,
                                @RequestParam(value = "TM", required = true) BigDecimal TM,
                                @RequestParam(value = "IM", required = false, defaultValue = "0") BigDecimal IM,
                                @RequestParam(value = "ADV", required = true) BigDecimal ADV,
                                @RequestParam(value = "RV", required = false, defaultValue = "0") BigDecimal RV,
                                @RequestParam(value = "ASH", required = false, defaultValue = "0") BigDecimal ASH,
                                @RequestParam(value = "AFT", required = false, defaultValue = "0") int AFT,
                                @RequestParam(value = "HGI", required = false, defaultValue = "0") int HGI,
                                @RequestParam(value = "ykj", required = false, defaultValue = "0") int ykj,
                                @RequestParam(value = "jtj01", required = false, defaultValue = "0") int jtj01,
                                @RequestParam(value = "amount12", required = false, defaultValue = "0") int amount12,
                                @RequestParam(value = "jtj02", required = false, defaultValue = "0") int jtj02,
                                @RequestParam(value = "amount21", required = false, defaultValue = "0") int amount21,
                                @RequestParam(value = "amount22", required = false, defaultValue = "0") int amount22,
                                @RequestParam(value = "jtj03", required = false, defaultValue = "0") int jtj03,
                                @RequestParam(value = "amount31", required = false, defaultValue = "0") int amount31,
                                @RequestParam(value = "amount32", required = false, defaultValue = "0") int amount32,
                                @RequestParam(value = "jtj04", required = false, defaultValue = "0") int jtj04,
                                @RequestParam(value = "amount41", required = false, defaultValue = "0") int amount41,
                                @RequestParam(value = "amount42", required = false, defaultValue = "0") int amount42,
                                @RequestParam(value = "jtj05", required = false, defaultValue = "0") int jtj05,
                                @RequestParam(value = "amount51", required = false, defaultValue = "0") int amount51,
                                @RequestParam(value = "amount52", required = false, defaultValue = "0") int amount52,
                                @RequestParam(value = "deliveryprovince", required = true) int deliveryprovince,
                                @RequestParam(value = "deliveryplace", required = true) String deliveryplace,
                                @RequestParam(value = "deliverymode", required = true) String deliverymode,
                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "deliverytime1", required = true) LocalDate deliverytime1,
                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "deliverytime2", required = true) LocalDate deliverytime2,
                                @RequestParam(value = "supplyquantity", required = true) int supplyquantity,
                                @RequestParam(value = "inspectorg", required = true) String inspectorg,
                                @RequestParam(value = "otherinspectorg", required = false, defaultValue = "") String otherinspectorg,
                                @RequestParam(value = "otherinspectorg1", required = false, defaultValue = "") String otherinspectorg1,
                                @RequestParam(value = "otherharbour", required = false, defaultValue = "") String otherharbour,
                                @RequestParam(value = "otherharbour1", required = false, defaultValue = "") String otherharbour1,
                                @RequestParam(value = "type", required = false, defaultValue = "") String type,
                                @RequestParam(value = "originplace", required = true) String originplace,
                                @RequestParam(value = "paymode", required = true) int paymode,
                                @RequestParam(value = "payperiod", required = false) BigDecimal payperiod,
                                @RequestParam(value = "releaseremarks", required = false) String releaseremarks,
                                @RequestParam(value = "linktype", required = true)boolean linktype,
                                @RequestParam(value = "linkmanname", required = false, defaultValue = "")String linkmanname,
                                @RequestParam(value = "linkmanphone", required = false, defaultValue = "")String linkmanphone,
                                @RequestParam(value = "version", required = false, defaultValue = "0")int version)  throws ParseException {
        Map<String, Object> map = new HashMap<>();
        boolean success = false;
        String error = "";
        List<PriceLadder> priceLadderList = new ArrayList<>();
        Integer[] priceList = new Integer[]{jtj01, jtj02, jtj03, jtj04, jtj05};
        Integer[] amount1List = new Integer[]{0, amount21, amount31, amount41, amount51};
        Integer[] amount2List = new Integer[]{amount12, amount22, amount32, amount42, amount52};
        int id = 0;
        String seller = companyMapper.getCompanyByUserid(session.getUser().getId()).getName();
        if (!StringUtils.isNullOrEmpty(otherharbour1)) { otherharbour = otherharbour1; }
        if (!StringUtils.isNullOrEmpty(otherinspectorg1)) { otherinspectorg = otherinspectorg1; }
        String province = areaportMapper.getNameById(deliveryprovince);
        String region = areaportMapper.getNameBySonName(province);
        String harbour = areaportMapper.getNameById(Integer.valueOf(deliveryplace.replace(",", "")));
        if (harbour == null) {
            harbour = "其它";
        }
        if(!linktype){
            linkmanname = "";
            linkmanphone = "";
        }
        //获取区域id
        Integer regionId = areaportMapper.getIdByName(region);
        //获取港口id
        Integer portId=areaportMapper.getIdByName(harbour);
        //如果没有港口信息，比如港口为其它的情况，把portId设置为-1
        portId = portId==null?-1:portId;
        //付款方式
        if(paymode != 2){
            payperiod = new BigDecimal(0);
        }
        int i = 0;
        int jtjLast = jtj01;
        while(i < priceList.length && priceList[i] > 0){
            priceLadderList.add(new PriceLadder(i+1, priceList[i], amount1List[i], amount2List[i]));
            jtjLast = jtjLast > priceList[i] ? priceList[i] : jtjLast;
            i++;
        }
        if (deliverytime1.isBefore(LocalDate.now().minusDays(1))) {
            error = "before";
        } else if(ykj ==0 && (jtj01 == 0 || jtj02 == 0)){
            throw new NotFoundException();
        } else if(sid == 0 || (sid != 0 && !StringUtils.isNullOrEmpty(type))) {
            SellInfo sellInfo = new SellInfo(EnumSellInfo.WaitConfirmed, pname, NCV, ADS, RS, TM, IM, ADV, RV, ASH, AFT, HGI, ykj, seller, region, province, harbour, otherharbour, deliverymode, deliverytime1, deliverytime2, supplyquantity, supplyquantity, inspectorg, otherinspectorg, LocalDateTime.now(), session.getUser().getId(), regionId,deliveryprovince,portId,originplace,paymode,payperiod,releaseremarks,linktype, linkmanname, linkmanphone, 0, 0);
            id = buyService.addSellInfo(sellInfo, sid, version, priceLadderList, jtjLast);
            success = true;
        } else {
            if(buyMapper.getSellInfoById(sid) == null) throw new NotFoundException();
            SellInfo newSellInfo = new SellInfo(sid, pname, EnumSellInfo.WaitConfirmed, NCV, ADS, RS, TM, IM, ADV, RV, ASH, AFT, HGI, ykj, seller, region, province, harbour, otherharbour, deliverymode, deliverytime1, deliverytime2, supplyquantity, supplyquantity, inspectorg, otherinspectorg, LocalDateTime.now(), regionId,deliveryprovince,portId,originplace,paymode,payperiod,releaseremarks, linktype, linkmanname, linkmanphone, version);
            buyService.updateSellInfo(newSellInfo, priceLadderList, jtjLast);
            id = sid;
            success = true;
        }
        map.put("id", id);
        map.put("success", success);
        map.put("error", error);
        return map;
    }

    @RequestMapping("/sell/createSupply")
    @LoginRequired
    public String getSupplyInfo(Map<String, Object> model) {
        buyService.initCreateSupply(model);
        return "releaseSupply";
    }

    /**
     * 更改供应信息
     * 未曾通过审核的修改供应跳到 "/releaseSupply"
     * 曾通过审核的修改供应跳到 "/releaseSupplyUpdate"
     * @param id
     * @param type
     * @param model
     * @return
     */
    @RequestMapping("/account/updateSupply")
    @LoginRequired
    public String goUpdateSupply(int id, String type, Map<String, Object> model) {
        SellInfo sellInfo = buyMapper.getSellInfoById(id);
        if (sellInfo == null) throw new NotFoundException();
        buyService.initUpdateSupply(sellInfo.getDeliveryprovince(), model);
        buyService.showJTJ(id, model);
        model.put("sellInfo", sellInfo);
        model.put("type", type);
        String url = "/releaseSupply";
        // 是否曾经通过审核
        String verifiedOnce = "false";
        // 根据editnum 和 VerifyPass 判断是否曾经通过审核
        if ((EnumSellInfo.VerifyPass).equals(sellInfo.getStatus()) || sellInfo.getEditnum()>0) {
            verifiedOnce = "true";
            url = "/releaseSupplyUpdate";
        }
        model.put("verifiedOnce", verifiedOnce);
        return url;
    }

    @RequestMapping("/confirmSellinfo")
    @ResponseBody
    @LoginRequired
    public Object doConfirmSellinfo(@RequestParam(value = "id", required = true)int id,
                                    @RequestParam(value = "version", required = true)int version) {
        SellInfo sellInfo = buyMapper.getSellInfoById(id);
        if (sellInfo == null) throw new NotFoundException();
        auth.doCheckUserRight(sellInfo.getSellerid());
        if(sellInfo.getStatus().equals(EnumSellInfo.WaitConfirmed)) {
            buyService.confirmSellinfo(id, version);
            return true;
        } else{
            return false;
        }
    }

    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public String buyList(
            @RequestParam(value = "region", required = false, defaultValue = "0")int region,
            @RequestParam(value = "province", required = false, defaultValue = "0") int province,
            @RequestParam(value = "harbour", required = false, defaultValue = "0") int harbour,
            @RequestParam(value = "NCV01", required = false, defaultValue = "0") int NCV01,
            @RequestParam(value = "NCV02", required = false, defaultValue = "10000") int NCV02,
            @RequestParam(value = "RS01", required = false, defaultValue = "0") BigDecimal RS01,
            @RequestParam(value = "RS02", required = false, defaultValue = "10") BigDecimal RS02,
            @RequestParam(value = "coaltype", required = false, defaultValue = "0")int coaltype,
            @RequestParam(value = "sorttype", required = false, defaultValue = "0")int sorttype,
            @RequestParam(value = "sequence", required = false, defaultValue = "0")int sequence,
            @RequestParam(value = "tag", required = false, defaultValue = "0") int tag,
            @RequestParam(value = "tagPrice", required = false, defaultValue = "0") int tagPrice,
            @RequestParam(value = "pagesize", required = false, defaultValue = "10")int pagesize,
            Map<String, Object> model, PageQueryParam param) {
        sellInfoList(region, province, harbour, NCV01, NCV02, RS01, RS02, null, coaltype, sorttype, sequence, tag, tagPrice, pagesize, model, param);
        return "buy";
    }

    @RequestMapping(value = "/mall", method = RequestMethod.GET)
    public String mallList(
            @RequestParam(value = "region", required = false, defaultValue = "0")int region,
            @RequestParam(value = "province", required = false, defaultValue = "0") int province,
            @RequestParam(value = "harbour", required = false, defaultValue = "0") int harbour,
            @RequestParam(value = "NCV01", required = false, defaultValue = "0") int NCV01,
            @RequestParam(value = "NCV02", required = false, defaultValue = "10000") int NCV02,
            @RequestParam(value = "RS01", required = false, defaultValue = "0") BigDecimal RS01,
            @RequestParam(value = "RS02", required = false, defaultValue = "10") BigDecimal RS02,
            @RequestParam(value = "coaltype", required = false, defaultValue = "0")int coaltype,
            @RequestParam(value = "sorttype", required = false, defaultValue = "0")int sorttype,
            @RequestParam(value = "sequence", required = false, defaultValue = "0")int sequence,
            @RequestParam(value = "tag", required = false, defaultValue = "0") int tag,
            @RequestParam(value = "tagPrice", required = false, defaultValue = "0") int tagPrice,
            @RequestParam(value = "pagesize", required = false, defaultValue = "10")int pagesize,
            Map<String, Object> model, PageQueryParam param) {
            sellInfoList(region, province, harbour, NCV01, NCV02, RS01, RS02, "自营", coaltype, sorttype, sequence, tag,tagPrice, pagesize, model, param);
        model.put("mallType", "mall");
        return "mall";
    }

    @RequestMapping(value = "/mall/index", method = RequestMethod.GET)
    public String indexList(@RequestParam(value = "region", required = false, defaultValue = "null")String region,
                            @RequestParam(value = "province", required = false, defaultValue = "null") String province,
                            @RequestParam(value = "harbour", required = false, defaultValue = "null") String harbour,
                            @RequestParam(value = "NCV01", required = false, defaultValue = "0") Integer NCV01,
                            @RequestParam(value = "NCV02", required = false, defaultValue = "10000") Integer NCV02,
                            @RequestParam(value = "RS01", required = false, defaultValue = "0") BigDecimal RS01,
                            @RequestParam(value = "RS02", required = false, defaultValue = "10") BigDecimal RS02,
                            @RequestParam(value = "coaltype", required = false, defaultValue = "null")String coaltype,
                            @RequestParam(value = "sorttype", required = false, defaultValue = "0")int sorttype,
                            @RequestParam(value = "sequence", required = false, defaultValue = "0")int sequence,
                            @RequestParam(value = "tag", required = false, defaultValue = "0") int tag,
                            @RequestParam(value = "tagPrice", required = false, defaultValue = "0") int tagPrice,
                            @RequestParam(value = "pagesize", required = false, defaultValue = "10")int pagesize,
                            Map<String, Object> model, PageQueryParam param){
        int regionNum = region.equals("null") ? 0 : (areaportMapper.getIdByName(region) == null ? 0 : areaportMapper.getIdByName(region));
        int provinceNum = province.equals("null") ? 0 : (areaportMapper.getIdByName(province) == null ? 0 : areaportMapper.getIdByName(province));
        int harbourNum = harbour.equals("null") ? 0 : (areaportMapper.getIdByName(harbour) == null ? 0 : areaportMapper.getIdByName(harbour));
        int coalname = coaltype.equals("null") ? 0 : (dictionaryMapper.getIdByName(coaltype) == null ? 0 : dictionaryMapper.getIdByName(coaltype));
        if(harbourNum != 0){
            provinceNum = areaportMapper.getParentidById(harbourNum);
            regionNum = areaportMapper.getParentidById(provinceNum);
        }
        sellInfoList(regionNum, provinceNum, harbourNum, NCV01, NCV02, RS01, RS02, "自营", coalname, sorttype, sequence, tag, tagPrice, pagesize, model, param);
        return "mall";
    }

    private void sellInfoList(int region, int province, int harbour, Integer NCV01, Integer NCV02, BigDecimal RS01,
                              BigDecimal RS02, String seller, int coaltype, int sorttype, int sequence, Integer tag,Integer tagPrice, int pagesize, Map<String, Object> model, PageQueryParam param) {
        if(NCV01 != 0 && NCV02 != 10000){
            int temp = NCV01;
            NCV01 = temp > NCV02 ? NCV02 : temp;
            NCV02 = temp > NCV02 ? temp : NCV02;
        }
        if(!(RS01.compareTo(BigDecimal.valueOf(0)) == 0) && !(RS02.compareTo(BigDecimal.valueOf(10)) == 0)) {
            BigDecimal temp = RS01;
            RS01 = temp.compareTo(RS02) == 1 ? RS02 : temp;
            RS02 = temp.compareTo(RS02) == 1 ? temp : RS02;
        }
        String coalname = dictionaryMapper.getNameById(coaltype);
        param.setPageSize(pagesize);
        List<Areaport> provinceList = getMallProvinces(region);
        List<Areaport> harbourList = getMallPorts(province);
        List<Areaport> regionList = new ArrayList<>();
        regionList.add(new Areaport(0, "全部"));
        regionList.addAll(areaportMapper.getAllArea());
        List<Dictionary> coalList = new ArrayList<>();
        coalList.add(new Dictionary(0, "coaltype", "全部"));
        coalList.addAll(dictionaryMapper.getAllCoalTypes());
        int count = buyMapper.getSellInfoCount(NCV01, NCV02, RS01, RS02, region, province, harbour, seller, coalname, EnumSellInfo.VerifyPass);
        if(param.getPage() > (count / param.getPageSize() + 1)) param.setPage((count / param.getPageSize() + 1));
        List<SellInfo> productList = buyMapper.getSellInfoList(NCV01, NCV02, RS01, RS02, region, province, harbour, seller, coalname, EnumSellInfo.VerifyPass, sorttype, sequence, param.getPageSize(), param.getIndexNum());
        model.put("coalList", coalList);
        model.put("regionlist", regionList);
        model.put("provincelist", provinceList);
        model.put("harbourlist", harbourList);
        model.put("page", param.getPage());
        model.put("pagesize", param.getPageSize());
        model.put("count", count);
        model.put("sorttype", sorttype);
        model.put("sequence", sequence);
        model.put("parameterMap", productList);
        model.put("coaltype", coaltype);
        model.put("result", tag);
        model.put("resultPrice", tagPrice);
        model.put("NCV01", String.valueOf(NCV01));
        model.put("NCV02", String.valueOf(NCV02));
        model.put("RS01", String.valueOf(RS01));
        model.put("RS02", String.valueOf(RS02));
        model.put("region", String.valueOf(region));
        model.put("province", String.valueOf(province));
        model.put("harbour", String.valueOf(harbour));
    }

    @RequestMapping("/mall/getProvinces")
    @ResponseBody
    public List<Areaport> getMallProvinces(Integer id) {
        List<Areaport> provinceList = new ArrayList<Areaport>();
        provinceList.add(new Areaport(0, "全部"));
        if (id != null && id != 0) {
            provinceList.addAll(doGetProvinces(id));
        } else{
            provinceList.addAll(areaportMapper.getAllProvince());
        }
        return provinceList;
    }

    @RequestMapping("/mall/getPorts")
    @ResponseBody
    public List<Areaport> getMallPorts(@RequestParam(value = "id", required = true) Integer id) {
        List<Areaport> harbourList = new ArrayList<>();
        harbourList.add(new Areaport(0, "全部"));
        if (id != null && id != 0) {
            harbourList.addAll(getPorts(id));
            harbourList.add(new Areaport(999, "其它"));
        }
        return harbourList;
    }

    @RequestMapping("/buy/getProvinces")
    @ResponseBody
    public List<Areaport> doGetProvinces(Integer id) {
        if(id == null || areaportMapper.getProcvincesOrPortsByParentid(id) == null) throw new NotFoundException();
        return areaportMapper.getProcvincesOrPortsByParentid(id);
    }

    @RequestMapping("/buy/getPorts")
    @ResponseBody
    public List<Areaport> doGetPorts(Integer id) {
        List<Areaport> harbourList = new ArrayList<>();
        if(id != null && id != 0) {
            harbourList = getPorts(id);
        }
        harbourList.add(new Areaport(999, "其它"));
        return harbourList;
    }

    private List<Areaport> getPorts(int id) {
        return areaportMapper.getProcvincesOrPortsByParentid(id);
    }

    @RequestMapping("/getSellinfoStatus")
    @ResponseBody
    public String doGetSellinfoStatus(@RequestParam(value = "id", required = true) int id) {
        SellInfo sellInfo = buyMapper.getSellInfoById(id);
        if (sellInfo == null) throw new NotFoundException();
        return JSON.toString(sellInfo.getStatus());
    }

    //测试邮件
    @RequestMapping("/test/email")
    public void doTestEmail(){
        kittHandlerExceptionResolver.resolveException(null, null, null, new SQLDataException());
    }


}
