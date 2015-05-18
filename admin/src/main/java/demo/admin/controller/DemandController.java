package demo.admin.controller;

import demo.core.domain.Dealer;
import demo.core.domain.Demand;
import demo.core.domain.Quote;
import demo.core.persistence.*;
import org.eclipse.jetty.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fanjun on 14-12-10.
 */
@RestController
public class DemandController {
    @Autowired
    protected DemandMapper demandMapper;
    @Autowired
    protected MydemandMapper mydemandMapper;
    @Autowired
    protected QuoteMapper quoteMapper;
    @Autowired
    private AreaportMapper areaportMapper;
    @Autowired
    private DealerMapper dealerMapper;
    @Autowired
    private MallProductController mallProductController;
    private final String waitStatus="待审核";
    private final String verifyPass="审核通过";

    //需求列表-待审核
    @RequestMapping("/demand/wait")
    public Object waitList(@RequestParam(value = "deliveryRegion", required = false, defaultValue = "0")Integer region,
                           @RequestParam(value = "deliveryProvince", required = false, defaultValue = "0")Integer province,
                           @RequestParam(value = "deliveryHarbour", required = false, defaultValue = "0")Integer harbour,
                           @RequestParam(value = "content", required = false, defaultValue = "")String content,
                           int page) {
        Map<String, Object> map = new HashMap<>();
        map.put("deliveryRegion", region);
        map.put("deliveryProvince", province);
        map.put("deliveryHarbour", harbour);
        map.put("regionList", areaportMapper.getAllArea());
        map.put("content", content);
            map.put("provinceList", mallProductController.getMallProvinces(region));
            map.put("harbourList", mallProductController.getMallPorts(province));
            map.put("demand", demandMapper.pageAllDemandsBySelect(region, province, harbour, content, "待审核", page, 10));
        return map;
    }

    //需求列表-审核通过
    @RequestMapping("/demand/pass")
    public Object passList(@RequestParam(value = "deliveryRegion", required = false, defaultValue = "0")Integer region,
                           @RequestParam(value = "deliveryProvince", required = false, defaultValue = "0")Integer province,
                           @RequestParam(value = "deliveryHarbour", required = false, defaultValue = "0")Integer harbour,
                           @RequestParam(value = "content", required = false, defaultValue = "")String content,
                           int page) {
        Map<String, Object> map = new HashMap<>();
        map.put("deliveryRegion", region);
        map.put("deliveryProvince", province);
        map.put("deliveryHarbour", harbour);
        map.put("regionList", areaportMapper.getAllArea());
        map.put("content", content);
        map.put("provinceList", areaportMapper.getProcvincesOrPortsByParentid(region));
        map.put("harbourList", areaportMapper.getProcvincesOrPortsByParentid(province));
        map.put("demand", demandMapper.pageAllDemandsBySelect(region, province, harbour, content, "审核通过", page, 10));
        return map;
    }

    //需求列表-审核未通过
    @RequestMapping("/demand/fail")
    public Object failList(@RequestParam(value = "deliveryRegion", required = false, defaultValue = "0")Integer region,
                           @RequestParam(value = "deliveryProvince", required = false, defaultValue = "0")Integer province,
                           @RequestParam(value = "deliveryHarbour", required = false, defaultValue = "0")Integer harbour,
                           @RequestParam(value = "content", required = false, defaultValue = "")String content,
                           int page) {
        Map<String, Object> map = new HashMap<>();
        map.put("deliveryRegion", region);
        map.put("deliveryProvince", province);
        map.put("deliveryHarbour", harbour);
        map.put("regionList", areaportMapper.getAllArea());
        map.put("content", content);
        map.put("provinceList", mallProductController.getMallProvinces(region));
        map.put("harbourList", mallProductController.getMallPorts(province));
        map.put("demand", demandMapper.pageAllDemandsBySelect(region, province, harbour, content, "审核未通过", page, 10));
        return map;
    }

    //需求列表的查看
    @RequestMapping("/demand/view")
    public Object demandView(String demandcode) {
        Map<String,Object> map = new HashMap<String,Object>();
        Demand demand = null;
        if(demandcode != null){
            demand = demandMapper.getDemandByDemandcode(demandcode);
            map.put("demand",demand);
            //待审核
            if(waitStatus.equals(demand.getCheckstatus())){
                //查找该港口下的所有交易员,如果港口是其它的情况，查找省份下的所有交易员
                List<Dealer> dealerList = demand.getPortId()!=-1?dealerMapper.findAllDealerByPortId(demand.getPortId()):dealerMapper.findDealerByProvinceId(demand.getProvinceId());
               //省份下面没有港口，查找地区下面的所有交易员
                if(dealerList.size()==0&&demand.getPortId()==-1){
                        dealerList=dealerMapper.findRegionAllDealer(demand.getProvinceId());
                }
                //地区下面没有交易员，就找XX网所有交易员
                if(dealerList.size()==0&&demand.getPortId()==-1){
                    dealerList=dealerMapper.findyiMeiAllDealer();
                }
                map.put("dealerList",dealerList);
            }
            //审核通过
            if(verifyPass.equals(demand.getCheckstatus())){
                //查找交易员
               map.put("dealer",dealerMapper.findDealerById(demand.getTraderid()));
            }
        }
        return map;
    }

    //保存审核状态和备注
    @RequestMapping("/demand/modifyCheckstatusAndComment")
    public Object modifyCheckstatusAndComment(String demandcode,String checkstatus,String comment,@RequestParam(value="dealerId",required = false) Integer dealerId){
        if(demandcode != null){
            if(checkstatus.equals("0")){
                checkstatus = "审核通过";
                //审核通过后修改交易状态,开始报价
                Dealer dealer=dealerMapper.findDealerById(dealerId);
                demandMapper.modifyTradestatusByDemandcode(dealer.getDealername(),dealer.getDealerphone(),"开始报价",demandcode,dealerId);
                //修改我的需求表状态为匹配中
                mydemandMapper.modifyStatusByDemandcode("报价中", demandcode);
            }else{
                checkstatus = "审核未通过";
                mydemandMapper.modifyStatusByDemandcode("审核未通过",demandcode);
            }
            demandMapper.modifyCheckstatusAndComment(checkstatus, comment,demandcode);
        }

        boolean success = true;
        return JSON.toString(success);
    }

    //取消审核通过的需求
    @RequestMapping("/demand/cancelDemand")
    public Object cancelDemand(String demandcode) {
        //主表状态改为删除
        demandMapper.modifyIsdeleteByDemandcode(demandcode);
        //我的需求表改为交易结束
        mydemandMapper.modifyStatusByDemandcode("交易结束",demandcode);
        //如果针对需求有报价,将报价状态改为未中标
        List<Quote> quoteList = quoteMapper.getQuoteByDemandcode(demandcode);
        if(quoteList != null && quoteList.size() > 0){
            for(Quote quote : quoteList){
                quoteMapper.modifyStatusByQuoteid("未中标",quote.getId());
            }
        }
        boolean success = true;
        return JSON.toString(success);
    }
}
