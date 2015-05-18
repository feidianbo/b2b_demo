package demo.admin.controller;

import com.mysql.jdbc.StringUtils;
import demo.admin.annotation.VerifyAuthentication;
import demo.admin.basic.exception.NotFoundException;
import demo.admin.service.Buy;
import demo.admin.service.Session;
import demo.core.domain.*;
import demo.core.persistence.AreaportMapper;
import demo.core.persistence.BuyMapper;
import demo.core.persistence.DealerMapper;
import demo.core.persistence.DictionaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jack on 15/1/7.
 */
@RestController
public class MallProductController {
    @Autowired
    private BuyMapper buyMapper;
    @Autowired
    private AreaportMapper areaportMapper;
    @Autowired
    private Buy buyService;
    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Autowired
    private Session session;

    @Autowired
    private DealerMapper dealerMapper;

    @RequestMapping("/mall/sales")
    @VerifyAuthentication(Trader = true, TraderAssistant = true, Finance = true, Operation = true, Admin = true)
    public Object getSellSellInfoList(
            @RequestParam(value = "deliveryRegion", required = false, defaultValue = "0")int region,
            @RequestParam(value = "deliveryProvince", required = false, defaultValue = "0")int province,
            @RequestParam(value = "deliveryHarbour", required = false, defaultValue = "0")int harbour,
            @RequestParam(value = "productNo", required = false, defaultValue = "")String productNo,
            int page){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("deliveryRegion", region);
        map.put("deliveryProvince", province);
        map.put("deliveryHarbour", harbour);
        map.put("regionList", areaportMapper.getAllArea());
        if(!StringUtils.isNullOrEmpty(productNo)){
            map.put("productNo", productNo);
        }
        map.put("mallList", buyMapper.getSellInfoBySelect(region, province, harbour, productNo, EnumSellInfo.VerifyPass, null, 0, page, 10));
        map.put("provinceList", getMallProvinces(region));
        map.put("harbourList", getMallPorts(province));
        return map;
    }

    @RequestMapping("/mall/pulls")
    @VerifyAuthentication(Trader = true, TraderAssistant = true, Finance = true, Operation = true, Admin = true)
    public Object getOffSellinfoList(
            @RequestParam(value = "deliveryRegion", required = false, defaultValue = "0")int region,
            @RequestParam(value = "deliveryProvince", required = false, defaultValue = "0")int province,
            @RequestParam(value = "deliveryHarbour", required = false, defaultValue = "0")int harbour,
            @RequestParam(value = "productNo", required = false, defaultValue = "")String productNo,
            int page){
        Map<String, Object> map = new HashMap<>();
        map.put("deliveryRegion", region);
        map.put("deliveryProvince", province);
        map.put("deliveryHarbour", harbour);
        map.put("regionList", areaportMapper.getAllArea());
        if(!StringUtils.isNullOrEmpty(productNo)) {
            map.put("productNo", productNo);
        }
        map.put("mallList", buyMapper.getSellInfoBySelect(region, province, harbour, productNo, EnumSellInfo.OutOfStack, EnumSellInfo.OutOfDate, 0, page, 10));
        map.put("provinceList", getMallProvinces(region));
        map.put("harbourList", getMallPorts(province));
        return map;
    }

    @RequestMapping("/supplyInfo")
    @VerifyAuthentication(Operation = true, Admin = true)
    public Object getSupplyInfo() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Dictionary> inspectorgs = dictionaryMapper.getAllInspectionagencys();
        inspectorgs.add(new Dictionary(100, "inspectionagency", "其它"));
        map.put("deliveryproviences", areaportMapper.getAllProvince());
        map.put("inspectorgs", inspectorgs);
        map.put("pnames", dictionaryMapper.getAllCoalTypes());
        return map;
    }

    //添加供应信息
    @RequestMapping("/addSellinfo")
    @VerifyAuthentication(Operation = true, Admin = true)
    public Object doAddSellinfo(@RequestParam(value="id",required = false, defaultValue = "0")int sid,
                                @RequestParam(value="pname", required = true) String pname,
                                @RequestParam(value="NCV", required = true)int NCV,
                                @RequestParam(value="RS", required = false)BigDecimal RS,
                                @RequestParam(value="ADS", required = false, defaultValue = "0")BigDecimal ADS,
                                @RequestParam(value="TM", required=true)BigDecimal TM,
                                @RequestParam(value="IM", required = false, defaultValue = "0")BigDecimal IM,
                                @RequestParam(value="ADV", required = true)BigDecimal ADV,
                                @RequestParam(value="RV", required = false, defaultValue = "0")BigDecimal RV,
                                @RequestParam(value="ASH", required = false, defaultValue = "0") BigDecimal ASH,
                                @RequestParam(value="AFT", required = false, defaultValue = "0")int AFT,
                                @RequestParam(value="HGI", required = false, defaultValue = "0")int HGI,
                                @RequestParam(value="ykj", required = false, defaultValue = "0")int ykj,
                                @RequestParam(value="jtj01", required = false, defaultValue = "0")int jtj01,
                                @RequestParam(value="amount12", required = false, defaultValue = "0")int amount12,
                                @RequestParam(value="jtj02", required = false, defaultValue = "0")int jtj02,
                                @RequestParam(value="amount21", required = false, defaultValue = "0")int amount21,
                                @RequestParam(value="amount22", required = false, defaultValue = "0")int amount22,
                                @RequestParam(value="jtj03", required = false, defaultValue = "0")int jtj03,
                                @RequestParam(value="amount31", required = false, defaultValue = "0")int amount31,
                                @RequestParam(value="amount32", required = false, defaultValue = "0")int amount32,
                                @RequestParam(value="jtj04", required = false, defaultValue = "0")int jtj04,
                                @RequestParam(value="amount41", required = false, defaultValue = "0")int amount41,
                                @RequestParam(value="amount42", required = false, defaultValue = "0")int amount42,
                                @RequestParam(value="jtj05", required = false, defaultValue = "0")int jtj05,
                                @RequestParam(value="amount51", required = false, defaultValue = "0")int amount51,
                                @RequestParam(value="amount52", required = false, defaultValue = "0")int amount52,
                                @RequestParam(value="deliveryprovience", required = true)Integer provinceId,
                                @RequestParam(value="deliveryplace", required = true)Integer portId,
                                @RequestParam(value="deliverymode", required = true)String deliverymode,
                                @RequestParam(value="deliverytime1", required = true)String deliverytime1,
                                @RequestParam(value="deliverytime2", required = true)String deliverytime2,
                                @RequestParam(value="supplyquantity", required = true)int supplyquantity,
                                @RequestParam(value="inspectorg", required = false)String inspectorg,
                                @RequestParam(value="otherinspectorg", required = false, defaultValue = "")String otherinspectorg,
                                @RequestParam(value="otherharbour", required = false, defaultValue = "")String otherharbour,
                                @RequestParam(value="producttype", required = false, defaultValue = "Common")String producttype,
                                @RequestParam(value="originplace", required = true)String originplace,
                                @RequestParam(value="releaseremarks", required = true)String releaseremarks,
                                @RequestParam(value="dealerSel", required = true)int dealerId,
                                @RequestParam(value="version", required = false, defaultValue = "0")int version) {
        Map<String, Object> map = new HashMap<>();
        boolean success = false;
        List<PriceLadder> priceLadderList = new ArrayList<>();
        Integer[] priceList = new Integer[]{jtj01, jtj02, jtj03, jtj04, jtj05};
        Integer[] amount1List = new Integer[]{0, amount21, amount31, amount41, amount51};
        Integer[] amount2List = new Integer[]{amount12, amount22, amount32, amount42, amount52};
        int id = 0;
        LocalDate deliverytime1d = LocalDate.parse(deliverytime1);
        LocalDate deliverytime2d = LocalDate.parse(deliverytime2);
        inspectorg = (StringUtils.isNullOrEmpty(inspectorg) == true ? "无" : inspectorg);
        //设置交易员
        Dealer dealer=dealerMapper.findDealerById(dealerId);
        //区域id
        int regionId=areaportMapper.getParentidById(provinceId);
        //获取区域id
        String  regionName=areaportMapper.getNameById(regionId);
        //获取省份id
        String provinceName=areaportMapper.getNameById(provinceId);
        //获取港口id
        String portName=areaportMapper.getNameById(portId);
        //如果没有港口信息，比如港口为其它的情况，把portId设置为-1
        portId = portId==null?-1:portId;
        if (deliverytime1d.isBefore(LocalDate.now().minusDays(1))) {
            map.put("error", "before");
        } else if(ykj == 0 && (jtj01 == 0 || jtj02 == 0)){
            throw new NotFoundException();
        } else {
            if (sid == 0) {
                SellInfo sell = new SellInfo(regionName, provinceName, portName, EnumSellInfo.VerifyPass, pname, NCV, ADS, RS, TM, IM, ADV, RV, ASH, AFT, HGI, ykj, "自营", otherharbour, deliverymode, deliverytime1d, deliverytime2d, supplyquantity, supplyquantity, inspectorg, otherinspectorg, LocalDateTime.now(), 0, session.getAdmin().getUsername() + "--" + session.getAdmin().getJobnum(), dealer.getId(), dealer.getDealername(), dealer.getDealerphone(), producttype, regionId, provinceId, portId, originplace, releaseremarks);
                if (portId == -1) {
                    sell.setDeliveryplace("其它");
                }
                id = buyService.addSellInfo(sell, priceLadderList);
                if (id == 0) throw new NotFoundException();
                success = true;
            } else {
                SellInfo sellInfo = buyMapper.getSellInfoById(sid);
                if(sellInfo == null) throw new NotFoundException();
                SellInfo newSellInfo = new SellInfo(sid, pname, NCV, ADS, RS, TM, IM, ADV, RV, ASH, AFT, HGI, ykj, "自营", otherharbour, deliverymode, deliverytime1d, deliverytime2d, supplyquantity, supplyquantity, inspectorg, otherinspectorg, LocalDateTime.now(), session.getAdmin().getUsername() + "--" + session.getAdmin().getJobnum(), dealer.getId(), dealer.getDealername(), dealer.getDealerphone(), producttype, regionId, provinceId, portId, originplace, releaseremarks, sellInfo.getVersion());
                buyService.updateSellInfo(newSellInfo, sid, version, priceLadderList);
                id = sid;
                success = true;
            }
        }
        map.put("id", id);
        map.put("success", success);
        return map;
    }

    //查询省份
    @RequestMapping("/mall/getProvinces")
    public List<Areaport> getMallProvinces(Integer id){
        if(id == null || id == 0){
            return new ArrayList<>();
        } else {
            return areaportMapper.getProcvincesOrPortsByParentid(id);
        }
    }

    @RequestMapping("/mall/getPorts")
    public List<Areaport> getMallPorts(Integer id){
        List<Areaport> deliveryplace = new ArrayList<>();
        if(id != null && id != 0) {
            deliveryplace.addAll(getPorts(id));
        }
        deliveryplace.add(new Areaport(-1, "其它"));
        return deliveryplace;
    }

    @RequestMapping("/getDealerPorts")
    public List<Areaport> getDealerPorts(Integer id){
        List<Areaport> deliveryplaceList = new ArrayList<>();
        if(id != null && id != 0) {
            deliveryplaceList.addAll(getPorts(id));
            deliveryplaceList.add(new Areaport(-1, "其它"));
        }
        return deliveryplaceList;
    }

    public List<Areaport> getPorts(int id){
        return areaportMapper.getProcvincesOrPortsByParentid(id);
    }

    @RequestMapping("/getProviences")
    public List<Areaport> getBuyProviences(String name){
        if(StringUtils.isNullOrEmpty(name)) throw new NotFoundException();
        return areaportMapper.getProcvincesOrPortsByParentname(name);
    }

    @RequestMapping("/getBuyPorts")
    public Object getBuyPorts(String name){
        Map<String, Object> map = new HashMap<>();
        if(!StringUtils.isNullOrEmpty(name)) {
            List<Areaport> deliveryplaces = areaportMapper.getProcvincesOrPortsByParentname(name);
            deliveryplaces.add(new Areaport(-1, "其它"));
            map.put("deliveryplaces", deliveryplaces);
        } else{
            map.put("deliveryplaces", "null");
        }
        return map;
    }

    @RequestMapping("/mall/putOffShelves")
    @VerifyAuthentication(Operation = true, Admin = true)
    public Object doPutOffShelves(@RequestParam(value = "id", required = true)int id){
        if (buyMapper.getSellInfoById(id) == null) throw new NotFoundException();
        if (buyMapper.putOffMallProduct(EnumSellInfo.OutOfStack, id) == 1) {
            return true;
        }
        return false;
    }

    @RequestMapping("/mall/changeRecommend")
    @VerifyAuthentication(Operation = true, Admin = true)
    public Object doRecommendProduct(@RequestParam(value = "id", required = true)int id,
                                     @RequestParam(value = "recommend", required = true)String recommend){
        Map<String, Object> map = new HashMap<>();
        boolean success = false;
        SellInfo sellInfo = buyMapper.getSellInfoById(id);
        if(sellInfo == null) throw new NotFoundException();
        if(buyMapper.setRecommentStatus(recommend, id) == 1){
            map.put("recommend", recommend);
            success = true;
        }
        map.put("success", success);
        return map;
    }
    
}
