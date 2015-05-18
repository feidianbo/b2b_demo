package demo.admin.controller;

import demo.admin.annotation.VerifyAuthentication;
import demo.admin.basic.exception.NotFoundException;
import demo.admin.service.Auth;
import demo.admin.service.Buy;
import demo.admin.service.Session;
import demo.core.domain.*;
import demo.core.persistence.*;
import demo.core.service.SMS;
import org.eclipse.jetty.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by jack on 15/1/10.
 */
@RestController
public class DealerController {
    @Autowired
    private AreaportMapper areaportMapper;
    @Autowired
    private DealerMapper dealerMapper;
    @Autowired
    private Session session;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private Auth auth;
    @Autowired
    private SMS sms;
    @Autowired
    private Buy buy;
    @Autowired
    private BuyMapper buyMapper;
    @Autowired
    private DemandMapper demandMapper;
    @Autowired
    private MallProductController mallProductController;

    @RequestMapping("/dealer/list")
    @VerifyAuthentication(BackgroundSupporter = true, Admin = true)
    public Object showDealerlist(
            @RequestParam(value = "deliveryRegion", required = false, defaultValue = "0")int region,
            @RequestParam(value = "deliveryProvince", required = false, defaultValue = "0")int province,
            @RequestParam(value = "deliveryHarbour", required = false, defaultValue = "0")int harbour,
            int page){
        Map<String, Object> map = new HashMap<>();
        List<Areaport> regionList = areaportMapper.getAllArea();
        List<Areaport> addProvinceList = mallProductController.getMallProvinces(region);
        List<Areaport> addHarbourList = mallProductController.getMallPorts(province);
        String deliveryPlace = harbour == 999 ? "其它" : areaportMapper.getNameById(harbour);
        map.put("regionList", regionList);
        map.put("addProvinceList", addProvinceList);
        map.put("addHarbourList", addHarbourList);
        map.put("region", region);
        map.put("province", province);
        map.put("harbour", harbour);
        List<Areaport> harbourList = new ArrayList<>();
        if(province != 0) {
            harbourList = areaportMapper.getProcvincesOrPortsByParentid(province);
            harbourList.add(new Areaport(999, "其它"));
            map.put("harbourList", harbourList);
        }
        if(region == 0){
           // map.put("dealerList", dealerMapper.getAllDealersList(page, 10));
            map.put("dealerList",dealerMapper.pageAllDealer(harbour,page,10));
            map.put("provinceList", areaportMapper.getAllProvince());
        } else{
            String deliveryRegion = areaportMapper.getNameById(region);
            String deliveryProvince = areaportMapper.getNameById(province);
            map.put("provinceList", areaportMapper.getProcvincesOrPortsByParentid(region));
//            map.put("dealerList", dealerMapper.getDealersBySelect(deliveryRegion, deliveryProvince, deliveryPlace, page, 10));
            map.put("dealerList",dealerMapper.pageAllDealer(harbour,page,10));
        }
        return map;
    }
    @RequestMapping("/dealer/phoneIfExist")
    @ResponseBody
    public boolean checkExistPhone(@RequestParam("addDealerPhone")String phoneNum){
        if(dealerMapper.isExistPhone(phoneNum)>0){
            return false;
        }
            return true;
    }

    @RequestMapping(value = "/dealer/addDealer",method = RequestMethod.POST)
    @VerifyAuthentication(BackgroundSupporter = true, Admin = true)
    public Object addDealer(@RequestBody Dealer dealer){
        //添加交易员
        dealer.setCreatetime(LocalDateTime.now());
        dealerMapper.saveDealer(dealer);
        //添加港口
        dealerMapper.addDealerPort(dealer);
        return true;
    }
    @RequestMapping(value = "/dealer/updatePortInDealer")
    public boolean updateDealerPortInfo(@RequestParam("portId") int portId,@RequestParam("dealeId") List<Integer>newDealerIds){

        List<Integer> oldDealerIds = dealerMapper.findAllDealerIdByPortId(portId);
        List<Integer> deleteIds=getSubSet(oldDealerIds,newDealerIds);
        List<Integer> addIds=getSubSet(newDealerIds,oldDealerIds);
        //删除当前港口信息
        if(deleteIds.size()>0){
            dealerMapper.deleteDealerPort(portId,deleteIds);
        }
        //添加该港口下的交易员
        if(addIds.size()>0){
            dealerMapper.addPortInDealer(portId, addIds);
        }
        return true;
    }
    @RequestMapping("dealer/listAllPort")
    public Object listAllAreaport(){
        return  areaportMapper.findAllPort();
    }


    @RequestMapping(value = "/dealer/update",method =RequestMethod.POST)
    public boolean updateDealerInfo(@RequestParam("dealerId")int dealerId,@RequestParam(value = "addDealerPhone",required = false) String phone,@RequestParam(value = "portCheckbox",required = false)Integer[] portIds){
        List<Integer> oldPortIds = dealerMapper.findPortIdByDealerId(dealerId);
        List<Integer> newPortIds=portIds==null?new ArrayList<Integer>():Arrays.asList(portIds);
        //删除角色信息
        List<Integer> deleteIds=getSubSet(oldPortIds,newPortIds);
       //添加角色信息
        List<Integer> addIds=getSubSet(newPortIds,oldPortIds);
        if(deleteIds.size()>0){
            dealerMapper.deleteDealerInPort(dealerId, deleteIds);
        }
        if(addIds.size()>0){
            dealerMapper.addDealerInPort(dealerId,addIds);
        }
        //修改交易员电话
        Dealer dealer=dealerMapper.findDealerById(dealerId);
        if(!dealer.getDealerphone().equals(phone)){
            dealerMapper.updateDealerPhoneById(phone, dealerId);
        }
        //修改供应信息交易员电话
        demandMapper.updateDealerPhone(phone, dealerId);
        //修改需求信息交易员电话
        buyMapper.updateDealerPhone(phone,dealerId);
        return true;
    }

    //得到两个集合的差集
    private  List<Integer> getSubSet(List<Integer> c1,List<Integer> c2){
        List<Integer> result = new ArrayList<Integer>();
        result.addAll(c1);
        result.removeAll(c2);
        return result;
    }

    @RequestMapping("/dealer/changeStatus")
    @VerifyAuthentication(BackgroundSupporter = true, Admin = true)
    public Object doChangeDealer(@RequestParam(value="id", required = true)int id,
                                 @RequestParam(value="status", required = true)String status){
        Dealer dealer = dealerMapper.getDealerById(id);
        if(dealer == null)  {
            throw new NotFoundException();
        }
        dealerMapper.setDealerStatusById(status, id);
        if(status.equals("已离职")) {
            List<Dealer> dealerList = dealerMapper.getDealerByDealerId(dealer.getDealerid());
            List<SellInfo> sellInfoList;
            List<Demand> demandList;
            if (dealerList == null || dealerList.size() == 0) {
                Admin admin = adminMapper.getAdminByJobNum(dealer.getDealerid());
                if(admin != null) {
                    adminMapper.setIsActiveByJobnum(false, dealer.getDealerid());
                }
                sellInfoList = buyMapper.getSellinfoByDealerId(dealer.getDealerid());
                demandList = demandMapper.getDemandListByDealerid(dealer.getDealerid());
            } else {
                sellInfoList = buyMapper.getSellInfoByRegionProvinceHarbour(dealer.getDeliveryregion(), dealer.getDeliveryprovince(), dealer.getDeliveryplace());
                demandList = demandMapper.getDemandListByRegionProvinceHarbour(dealer.getDeliveryregion(), dealer.getDeliveryprovince(), dealer.getDeliveryplace());
            }
            for (SellInfo sellInfo : sellInfoList) {
                Dealer newDealer = buy.doGetDealer(sellInfo.getDeliveryregion(), sellInfo.getDeliveryprovince(), sellInfo.getDeliveryplace());
                buyMapper.updateSellinfoDealer(newDealer.getDealerid(), newDealer.getDealername(), newDealer.getDealerphone(), sellInfo.getId());
            }
            for(Demand demand : demandList) {
                Dealer newDealer = buy.doGetDealer(demand.getDeliverydistrict(), demand.getDeliveryprovince(), demand.getDeliveryplace());
                demandMapper.updateDemandDealer(newDealer.getDealerid(), newDealer.getDealername(), newDealer.getDealerphone(), demand.getId());
            }
        }
        return true;
    }

    @RequestMapping("/dealer/checkIfExist")
    @VerifyAuthentication(BackgroundSupporter = true, Admin = true)
    public Object doCheckIfExist(@RequestParam(value = "addDealerName", required = true)String dealername,
                                 @RequestParam(value = "addDealerPhone", required = true)String dealerphone,
                                 @RequestParam(value = "addDeliveryRegion", required = true)int region,
                                 @RequestParam(value = "addDeliveryProvince", required = true)int province,
                                 @RequestParam(value = "addDeliveryPlace", required = true)int place){
        String deliveryregion = areaportMapper.getNameById(region);
        String deliveryprovince = areaportMapper.getNameById(province);
        String deliveryplace = place == 999 ? "其它" : areaportMapper.getNameById(place);
        List<Dealer> oldDealerList = dealerMapper.getDealerByRegionProvincePlace(deliveryregion, deliveryprovince, deliveryplace);
        if(oldDealerList == null || oldDealerList.size() == 0){
            return new Object(){
              public boolean success = true;
            };
        } else if(oldDealerList.size() == 1 && oldDealerList.get(0).getDealername().equals(dealername) && oldDealerList.get(0).getDealerphone().equals(dealerphone)){
            return new Object(){
                public boolean success = false;
                public String error = "exist";
            };
        } else{
            for(Dealer dealer : oldDealerList){
                if(dealer.getStatus().equals("在职")){
                    return new Object(){
                        public boolean success = false;
                        public String dealerString = dealer.getDealername() + "，电话：" + dealer.getDealerphone();
                    };
                }
            }
            return new Object(){
                public boolean success = true;
            };
        }
    }

    @RequestMapping("/dealer/deleteOldDealers")
    @VerifyAuthentication(BackgroundSupporter = true, Admin = true)
    public Object doDeleteOldDealers(
            @RequestParam(value = "addDeliveryRegion", required = true)int region,
            @RequestParam(value = "addDeliveryProvince", required = true)int province,
            @RequestParam(value = "addDeliveryPlace", required = true)int place){
        String deliveryregion = areaportMapper.getNameById(region);
        String deliveryprovince = areaportMapper.getNameById(province);
        String deliveryplace = place == 999 ? "其它" : areaportMapper.getNameById(place);
        List<Dealer> dealerList = dealerMapper.getDealerByRegionProvincePlace(deliveryregion, deliveryprovince, deliveryplace);
        if(dealerList != null && dealerList.size() != 0){
            for(Dealer dealer : dealerList){
                dealerMapper.setDealerStatusById("已删除", dealer.getId());
            }
        }
        return true;
    }



//    @RequestMapping("/dealer/addDealer")
//    @VerifyAuthentication(BackgroundSupporter = true, Admin = true)
//    public Object doAddDealer(
//            @RequestParam(value = "addDealerName", required = true)String dealername,
//            @RequestParam(value = "addDealerPhone", required = true)String dealerphone,
//            @RequestParam(value = "addDeliveryRegion", required = true)int region,
//            @RequestParam(value = "addDeliveryProvince", required = true)int province,
//            @RequestParam(value = "addDeliveryPlace", required = true)int place) throws Exception {
//        Map<String, Object> map = new HashMap<>();
//        boolean success = false;
//        String error = null;
//        String deliveryregion = areaportMapper.getNameById(region);
//        String deliveryprovince = areaportMapper.getNameById(province);
//        String deliveryplace = place == 999 ? "其它" : areaportMapper.getNameById(place);
//        String hellowords = dealername;
//        String signature = "【XX网】";
//        String username = auth.HanyuToPinyin(dealername);
//        username = username.length() > 15 ? username.substring(0, 15) : username;
//        Dealer dealer = new Dealer(dealername, dealerphone, deliveryregion, deliveryprovince, deliveryplace, session.getAdmin().getId(), session.getAdmin().getUsername());
//        if (adminMapper.getByUsername(username) == null) {
//            if (adminMapper.getByPhone(dealerphone) == null) {
//                success = buy.addDealer(dealer, username, hellowords, signature);
//            } else {
//                error = "phoneexist";
//            }
//
//        } else {
//            if (adminMapper.getAdminByNameAndPhone(dealername, dealerphone) == null) {
//                if (adminMapper.getByPhone(dealerphone) != null) {
//                    error = "phoneexist";
//                } else {
//                    success = buy.addDealer(dealer, username, hellowords, signature);
//                }
//            } else {
//                String dealerid = adminMapper.getJobnumByNamePhone(dealername, dealerphone);
//                Dealer dealer1 = dealerMapper.getDealerByProvincePlaceDealerid(deliveryprovince, deliveryplace, dealerid);
//                if (dealer1 != null) {
//                    if (dealer1.getStatus().equals("在职")) {
//                        error = "exist";
//                    } else {
//                        dealerMapper.setDealerStatusById("在职", dealer1.getId());
//                        Admin admin1 = adminMapper.getAdminByJobNum(dealerid);
//                        if (!admin1.isIsactive()) {
//                            adminMapper.setIsActiveByJobnum(true, dealerid);
//                        }
//                        buy.updateDealers(dealer1);
//                        String content = ": 女士/先生，你好，你现在被XX网安排为：" + deliveryregion + deliveryprovince + deliveryplace + " 交易员，你现在可以登录XX网管理员平台(http://admin.xxx.com)查看相关工作。";
//                        sms.send(dealerphone, content, hellowords, signature);
//                        success = true;
//                    }
//                } else {
//                    Dealer dealer2 = new Dealer(dealerid, dealername, dealerphone, deliveryregion, deliveryprovince, deliveryplace, session.getAdmin().getId(), session.getAdmin().getUsername());
//                    dealerMapper.addDealerPart(dealer2);
//                    buy.updateDealers(dealer2);
//                    String content = ": 女士/先生，你好，你现在被XX网安排为：" + deliveryregion + deliveryprovince + deliveryplace + " 交易员，你现在可以登录XX网管理员平台(http://admin.xxx.com)查看相关工作。";
//                    sms.send(dealerphone, content, hellowords, signature);
//                    success = true;
//                }
//            }
//        }
//        map.put("error", error);
//        map.put("success", success);
//        return map;
//    }

    @RequestMapping("/dealer/getPhone")
    @VerifyAuthentication(BackgroundSupporter = true, Admin = true)
    public Object doGetPhone(@RequestParam(value = "name")String name){
        if(adminMapper.getByName(name) != null && adminMapper.getByName(name).size() != 0) {
            Admin admin = adminMapper.getByName(name).get(0);
            if (admin != null) {
                return JSON.toString(admin.getPhone());
            }
        }
        return JSON.toString("NULL");
    }

    @RequestMapping("/dealer/getName")
    @VerifyAuthentication(BackgroundSupporter = true, Admin = true)
    public Object doGetName(@RequestParam(value = "phone", required = true)String phone){
        Admin admin = adminMapper.getByPhone(phone);
        if(admin != null) {
            return JSON.toString(admin.getName());
        }
        return JSON.toString("NULL");
    }

    //加载交易员
    @RequestMapping("/dealer/loadDealer")
    public Object loadDealer(@RequestParam(value = "provinceId",defaultValue = "0") int provinceId,@RequestParam(value = "portId",defaultValue = "-1") int portId){
            List<Dealer> dealerList = new ArrayList<Dealer>();
        //-1是没有港口的情况，找属于这个省下面的所有交易员
            if(portId==-1){
                 dealerList =dealerMapper.findDealerByProvinceId(provinceId);
                //如果省下面没有交易员，找省所属地区的交易员
                if(dealerList.size()==0){
                  dealerList =  dealerMapper.findRegionAllDealer(provinceId);
                }
                //地区下面没有交易员，就找XX网所有交易员
                if(dealerList.size()==0){
                    dealerList=dealerMapper.findyiMeiAllDealer();
                }
            }else{
                dealerList=dealerMapper.findAllDealerByPortId(portId);
            }
        return  dealerList;
    }

}
