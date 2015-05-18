package demo.admin.controller;

import demo.admin.annotation.VerifyAuthentication;
import demo.admin.basic.JsonController;
import demo.admin.basic.exception.NotFoundException;
import demo.admin.service.Buy;
import demo.core.domain.*;
import demo.core.persistence.AreaportMapper;
import demo.core.persistence.BuyMapper;
import demo.core.persistence.DealerMapper;
import demo.core.persistence.PriceLadderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jack on 14/12/10.
 */
@Controller
public class SellInfoController extends JsonController {
    @Autowired
    private BuyMapper buyMapper;
    @Autowired
    private AreaportMapper areaportMapper;
    @Autowired
    private PriceLadderMapper priceLadderMapper;
    @Autowired
    private DealerMapper dealerMapper;
    @Autowired
    private MallProductController mallProductController;
    @Autowired
    private Buy buy;

    @RequestMapping("/supply/pass")
    @ResponseBody
    @VerifyAuthentication(Operation = true, Admin = true)
    public Object getPassList(@RequestParam(value = "productNo", required = false, defaultValue = "")String productNo,
                              @RequestParam(value = "deliveryRegion", required = false, defaultValue = "0")int region,
                              @RequestParam(value = "deliveryProvince", required = false, defaultValue = "0")int province,
                              @RequestParam(value = "deliveryHarbour", required = false, defaultValue = "0")int harbour,
                              int page) {
        Map<String, Object> map = new HashMap<>();
        map.put("deliveryRegion", region);
        map.put("deliveryProvince", province);
        map.put("deliveryHarbour", harbour);
        map.put("productNo", productNo);
        map.put("regionList", areaportMapper.getAllArea());
        map.put("provinceList", mallProductController.getMallProvinces(region));
        map.put("harbourList", mallProductController.getMallPorts(province));
        map.put("sellInfo", buyMapper.getSellInfoBySelect(region, province, harbour, productNo, EnumSellInfo.VerifyPass, null, 1, page, 10));
        return map;
    }

    @RequestMapping("/supply/fail")
    @ResponseBody
    @VerifyAuthentication(Trader = true, TraderAssistant = true, Finance = true, Operation = true, Admin = true)
    public Object getFailList(@RequestParam(value = "productNo", required = false, defaultValue = "")String productNo,
                              @RequestParam(value = "deliveryRegion", required = false, defaultValue = "0")int region,
                              @RequestParam(value = "deliveryProvince", required = false, defaultValue = "0")int province,
                              @RequestParam(value = "deliveryHarbour", required = false, defaultValue = "0")int harbour,
                              int page){
        Map<String, Object> map = new HashMap<>();
        map.put("deliveryRegion", region);
        map.put("deliveryProvince", province);
        map.put("deliveryHarbour", harbour);
        map.put("productNo", productNo);
        map.put("regionList", areaportMapper.getAllArea());
        map.put("provinceList", mallProductController.getMallProvinces(region));
        map.put("harbourList", mallProductController.getMallPorts(province));
        map.put("sellInfo", buyMapper.getSellInfoBySelect(region, province, harbour, productNo, EnumSellInfo.VerifyNotPass, null, 1, page, 10));
        return map;
    }

    @RequestMapping("/supply/wait")
    @ResponseBody
    @VerifyAuthentication(Trader = true, TraderAssistant = true, Finance = true, Operation = true, Admin = true)
    public Object getWaitList(@RequestParam(value = "productNo", required = false, defaultValue = "")String productNo,
                              @RequestParam(value = "deliveryRegion", required = false, defaultValue = "0")Integer region,
                              @RequestParam(value = "deliveryProvince", required = false, defaultValue = "0")Integer province,
                              @RequestParam(value = "deliveryHarbour", required = false, defaultValue = "0")Integer harbour,
                              int page){
        Map<String, Object> map = new HashMap<>();
        map.put("deliveryRegion", region);
        map.put("deliveryProvince", province);
        map.put("deliveryHarbour", harbour);
        map.put("productNo", productNo);
        map.put("regionList", areaportMapper.getAllArea());
        map.put("provinceList", mallProductController.getMallProvinces(region));
        map.put("harbourList", mallProductController.getMallProvinces(province));
        map.put("sellInfo", buyMapper.getSellInfoBySelect(region, province, harbour, productNo, EnumSellInfo.WaitVerify, null, 1, page, 10));
        return map;
    }

    @RequestMapping("/supply/detail")
    @ResponseBody
    @VerifyAuthentication(Trader = true, TraderAssistant = true, Finance = true, Operation = true, Admin = true)
    public Object showDetail(int id, String type){
        Map<String, Object> map = new HashMap<>();
        SellInfo sellInfo = buyMapper.getSellInfoById(id);
        if (sellInfo == null) throw new NotFoundException();
        map.put("sellInfo", sellInfo);
        List<PriceLadder> priceLadders = priceLadderMapper.getPriceLadderListBySellinfoId(id);
        switch (priceLadders.size()){
            case 5:
                map.put("jtj05Obj", priceLadders.get(4));
            case 4:
                map.put("jtj04Obj", priceLadders.get(3));
            case 3:
                map.put("jtj03Obj", priceLadders.get(2));
            case 2:
                map.put("jtj01Obj", priceLadders.get(0));
                map.put("jtj02Obj", priceLadders.get(1));
                break;
            default:
                break;
        }
        //待审核供应信息显示交易员信息
        if(EnumSellInfo.WaitVerify.toString().equals(type)){
            //查找该港口下的所有交易员,如果港口是其它的情况，查找省份下的所有交易员
            List<Dealer> dealerList = sellInfo.getPortId()!=-1?dealerMapper.findAllDealerByPortId(sellInfo.getPortId()):dealerMapper.findDealerByProvinceId(sellInfo.getProvinceId());
            if(dealerList.size() == 0&&sellInfo.getPortId()==-1) {
                dealerList=dealerMapper.findRegionAllDealer(sellInfo.getProvinceId());
            }
            if(dealerList.size() == 0&&sellInfo.getPortId()==-1){
                dealerList=dealerMapper.findyiMeiAllDealer();
            }
            map.put("dealerList",dealerList);
        }
        if(EnumSellInfo.VerifyPass.toString().equals(type)){
            //查找交易员
            map.put("dealer",dealerMapper.findDealerById(sellInfo.getTraderid()));
        }
        map.put("success", true);
        map.put("type",type);
        return map;
    }

    @RequestMapping("/supply/checkInfo")
    @ResponseBody
    @VerifyAuthentication(Trader = true, Admin = true)
    public Object doVerifySupply(@RequestParam(value = "id", required = true)int id,
                                 @RequestParam(value = "version", required = true)int version,
                                 int checkResult, String comment, int editnum,
            @RequestParam(value="dealerId", required = false)Integer dealerId) {
        SellInfo sellinfo = buyMapper.getSellInfoById(id);
        SupplyVerify supplyVerify = buyMapper.getSupplyVerifyBySellinfoId(id);
        if (sellinfo == null || supplyVerify == null) throw new NotFoundException();
        //return false if this id is not the latest record (editnum)
        int latestEditNum = buyMapper.getSupplyLatestEditNum(sellinfo.getPid());
        if (editnum != latestEditNum) return false;
        // return false if status is not WaitVerify
        if (!EnumSellInfo.WaitVerify.equals( sellinfo.getStatus()) ) return false;
        buy.verifySellinfo(checkResult, id, version, dealerId, comment, supplyVerify);
        return true;
    }

        @RequestMapping("/supply/cancelSupply")
        @ResponseBody
        @VerifyAuthentication(Operation = true, Admin = true)
        public Object doCancelSupply ( @RequestParam(value = "id", required = true) int id,
                                       @RequestParam(value = "version", required = true) int version){
            SellInfo sellInfo = buyMapper.getSellInfoById(id);
            if (sellInfo == null) throw new NotFoundException();
            buy.updateSellInfoStatusByIdVersion(id, version);
            return true;
        }




}
