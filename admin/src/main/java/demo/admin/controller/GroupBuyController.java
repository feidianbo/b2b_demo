package demo.admin.controller;

import demo.admin.annotation.VerifyAuthentication;
import demo.admin.service.FileService;
import demo.core.domain.*;
import demo.core.persistence.*;
import demo.core.util.Pager;
import org.eclipse.jetty.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by zhangbolun on 15/1/20.
 */
@RestController
public class GroupBuyController {
    @Autowired
    private ProviderInfoMapper providerInfoMapper;
    @Autowired
    private GroupBuyQualificationMapper groupBuyQualifyMapper;
    @Autowired
    private GroupBuySupplyMapper groupBuySupplyMapper;
    @Autowired
    private GroupBuyOrderMapper groupBuyOrderMapper;
    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected CompanyMapper companyMapper;
    @Autowired
    protected FileService fileService;

    //查询团购供应商列表
    @RequestMapping("/groupBuy/supplierslist")
    @VerifyAuthentication(Trader= true, TraderAssistant = true,Finance = true, Operation = true, Admin = true)
    public Object getProviderInfoList(int page) {
        Map map = new HashMap();
        map.put("supplier", providerInfoMapper.pageAllSuppliers(page, 10));
        return map;
    }

    //新增供应商
    @RequestMapping("/groupBuy/addProviderInfo")
    @VerifyAuthentication(Operation = true,Admin = true)
    public Object addProviderInfo(ProviderInfo providerInfo){
        Map map = new HashMap();
        String message="succeed";
        providerInfo.setCreatetime(LocalDateTime.now());
        //供应商暂时不提供删除功能
        providerInfo.setStatus(ProviderStatus.PROVIDER_FROZEN.toString());
        providerInfoMapper.addProviderInfo(providerInfo);
        ProviderInfo tempProviderInfo= providerInfoMapper.getProviderInfoById(providerInfo.getId());
        if(tempProviderInfo==null)
            message="新增供应商失败";
        map.put("message",message);
        return map;
    }

    //删除供应商
    @RequestMapping("/groupBuy/deleteProviderInfo")
    @VerifyAuthentication(Operation = true,Admin = true)
    public Object deleteProviderInfo(@RequestParam(value="providerinfoid", required = true)int providerinfoid){
        Map map = new HashMap();
        String message="succeed";
        ProviderInfo providerInfo=providerInfoMapper.getProviderInfoById(providerinfoid);
        if(providerInfo==null)
            message="未找到供应商";
        if(providerInfo.getStatus().equals(ProviderStatus.PROVIDER_FROZEN.toString())){
            message="此供应商团购正在进行中，不能删除";
        }else {
            providerInfoMapper.deleteProviderInfoByid(providerInfo.getId());
        }

        map.put("message",message);
        return map;
    }

    //查看供应商发布的团购信息列表--进行中
    @RequestMapping("/groupBuy/getGroupBuyInProcess")
    @VerifyAuthentication(Trader= true, TraderAssistant = true,Finance = true, Operation = true, Admin = true)
    public Object getGroupBuyInProcess(int page,int providerinfoid){
        Map map = new HashMap();
        map.put("groupbuy", groupBuySupplyMapper.pageInProcessGroupBuy(providerinfoid, page, 10));
        return map;
    }

    //查看供应商发布的团购信息列表--已结束
    @VerifyAuthentication(Trader= true, TraderAssistant = true,Finance = true, Operation = true, Admin = true)
    @RequestMapping("/groupBuy/getGroupBuyFinish")
    public Object getGroupBuyFinish(int page,int providerinfoid){
        Map map = new HashMap();
        map.put("groupbuy", groupBuySupplyMapper.pageFinishGroupBuy(providerinfoid, page, 10));
        return map;
    }

    //团购信息下架
    @RequestMapping("/groupBuy/cancelGroupBuySupply")
    @VerifyAuthentication(Operation = true,Admin = true)
    public Object cancelGroupBuySupply(@RequestParam(value="groupbuysupplyid", required = true)int groupbuysupplyid){
        Map map=new HashMap();
        String message="succeed";
        GroupBuySupply groupBuySupply= groupBuySupplyMapper.getGroupBuySupplyById(groupbuysupplyid);
        if(groupBuySupply==null)
            message="团购发布信息未找到";
        if(groupBuySupply.getStatus().equals(GroupBuySupplyStatus.GROUP_BUY_SUPPLY_INPROGRESS.toString())) {
            message = "团购进行中，不能下架";
        }else {
            groupBuySupplyMapper.updateStatusById(groupBuySupply.getId(),GroupBuySupplyStatus.GROUP_BUY_SUPPLY_GIVEUP.toString());
            groupBuySupplyMapper.deleteGroupBuySupplyById(groupBuySupply.getId());
        }

        map.put("message",message);
        return map;
    }

    //查看团购发布详细信息与相应的团购订单列表
    @RequestMapping("/groupBuy/getGroupBuySupplyDetail")
    @VerifyAuthentication(Trader= true, TraderAssistant = true,Finance = true, Operation = true, Admin = true)
    public Object getGroupBuySupplyDetail(@RequestParam(value="groupbuyid", required = true)int groupbuyid,int page) {
        Map map = new HashMap();
        GroupBuySupply groupBuySupply = groupBuySupplyMapper.getGroupBuySupplyById(groupbuyid);
        Pager<GroupBuyOrder> pager= groupBuyOrderMapper.pageAllGroupBuyOrder(groupbuyid, page, 5);
        List<GroupBuyOrder> orderPagerList= pager.getList();
        ProviderInfo providerInfo=null;
        if(orderPagerList!=null) {
            for (GroupBuyOrder gbo : orderPagerList) {
                providerInfo = providerInfoMapper.getProviderInfoById(groupBuySupply.getProviderinfoid());
                gbo.setCompanyname(providerInfo.getProvidername());
                gbo.setDeliverydatestart(groupBuySupply.getDeliverydatestart());
                gbo.setDeliverydateend(groupBuySupply.getDeliverydateend());
            }
        }
        pager.setList(orderPagerList);
        map.put("groupbuy", groupBuySupply);
        map.put("groupbuyorder", pager);
        map.put("surplusamount",groupBuySupply.getSurplusamount());
        map.put("totalamount",groupBuySupply.getSelledamount());
        return map;
    }

    //解除资质与订单的绑定
    @RequestMapping("/groupBuy/removeGroupBuyQualify")
    @VerifyAuthentication(Operation = true,Admin = true)
    public Object removeGroupBuyQualify(@RequestParam(value = "qualificationcode", required = true)String qualificationcode,@RequestParam(value="issucceed", required = true)int issucceed){
        Map map = new HashMap();
        String message = "succeed";
        GroupBuyQualification groupBuyQualification= groupBuyQualifyMapper.getGroupBuyQualifyByCode(qualificationcode);
        if(groupBuyQualification==null)
            message="用户资质未找到";
        if(issucceed==1) {
            groupBuyQualifyMapper.updateStatusByCode(qualificationcode, QualifyStatus.QUALIFY_ACTIVE.toString());
        }else {
            groupBuyQualifyMapper.updateStatusByCode(qualificationcode, QualifyStatus.QUALIFY_CANCEL.toString());
        }
        map.put("message", message);
        return map;
    }

    //发布团购信息
    @RequestMapping("/groupBuy/releaseGroupBuySupply")
    @VerifyAuthentication(Operation = true,Admin = true)
    public Object releaseGroupBuySupply(GroupBuySupply groupBuySupply){
        Map map = new HashMap();
        if(groupBuySupply.getGroupbuysupplycode()==null){
            groupBuySupply.setStatus(GroupBuySupplyStatus.GROUP_BUY_SUPPLY_RELEASE.toString());
            groupBuySupply.setCreatetime(LocalDateTime.now());
            groupBuySupply.setGroupbuyordercount(0);
            groupBuySupply.setSurplusamount(groupBuySupply.getSupplyamount());
            groupBuySupply.setSelledamount(0);
            groupBuySupplyMapper.addGroupBuySupply(groupBuySupply);
            groupBuySupply=groupBuySupplyMapper.getGroupBuySupplyById(groupBuySupply.getId());
        }else {
            groupBuySupplyMapper.updateGroupBuySupply(groupBuySupply);
        }

        map.put("groupbuysupplycode",groupBuySupply.getGroupbuysupplycode());
        return map;
    }

    //团购发布校验页面
    @RequestMapping("/groupBuy/getGroupBuyForCheck")
    public Object getGroupBuyForCheck(String groupbuysupplycode){
        GroupBuySupply groupbuy = groupBuySupplyMapper.getGroupBuySupplyByCode(groupbuysupplycode);
        Map map = new HashMap();
        map.put("groupbuy", groupbuy);
        return map;
    }

    //可用不可用
    @RequestMapping("/groupBuy/resetQualifyStatus")
    @VerifyAuthentication(Operation = true,Admin = true)
    public Object resetQualifyStatus(String isOk,String qualificationcode,String ordercode){
        Map map = new HashMap();
        if(isOk.equals("true")){
            groupBuyQualifyMapper.updateStatusByCode(qualificationcode,QualifyStatus.QUALIFY_ACTIVE.toString());
        }else {
            groupBuyQualifyMapper.updateStatusByCode(qualificationcode, QualifyStatus.QUALIFY_CANCEL.toString());
        }
        boolean success = true;
        return JSON.toString(success);
    }

    //查询团购资质列表
    @RequestMapping("/groupBuy/getGroupBuyQualifyList")
    @VerifyAuthentication(Trader = true, TraderAssistant = true,Finance = true,Operation = true, Admin = true)
    public Object getGroupBuyQualifyList(String status,int page){
        Map map = new HashMap();
        Pager<GroupBuyQualification> pager=null;
        if(status.equals("wait")){
            pager = groupBuyQualifyMapper.pageGroupBuyQualifyByStatus(QualifyStatus.QUALIFY_APPLY.toString(), page, 10);
        }else if(status.equals("pass")){
            pager = groupBuyQualifyMapper.pageGroupBuyQualifyPass(page, 10);
        }else {
            pager = groupBuyQualifyMapper.pageGroupBuyQualifyByStatus(QualifyStatus.QUALIFY_NOT_ENOUGH.toString(), page, 10);
        }

        List<GroupBuyQualification> groupBuyQualifications= pager.getList();
        if(groupBuyQualifications!=null) {
            for (GroupBuyQualification gbq : groupBuyQualifications) {
                User user = userMapper.getUserById(gbq.getUserid());
                gbq.setUsername(user.getNickname());
                gbq.setUserphone(user.getSecurephone());
            }
            pager.setList(groupBuyQualifications);
        }
        map.put("groupBuyQualifications",pager);
        return map;
    }

    //查看团购资质详细信息
    @RequestMapping("/groupBuy/getGroupBuyQualifyDetail")
    @VerifyAuthentication(Trader = true, TraderAssistant = true,Finance = true,Operation = true, Admin = true)
    public Object getGroupBuyQualifyDetail(String code){
        Map map = new HashMap();
        GroupBuyQualification groupBuyQualification= groupBuyQualifyMapper.getGroupBuyQualifyByCode(code);
        if(groupBuyQualification!=null){
            User user = userMapper.getUserById(groupBuyQualification.getUserid());
            if(user!=null){
                groupBuyQualification.setUserphone(user.getSecurephone());
                Company company= companyMapper.getCompanyByUserid(user.getId());
                if(company!=null){
                    groupBuyQualification.setCompanyname(company.getName());
                }
            }
        }
        map.put("groupBuyQualification",groupBuyQualification);
        return map;
    }

    //提交团购资质审核
    @RequestMapping("/groupBuy/confirmGroupBuyQualifyDetail")
    @VerifyAuthentication(Finance = true,Admin = true)
    public Object confirmGroupBuyQualifyDetail(String qualifyCode,String comment,BigDecimal margins){
        Map map = new HashMap();
        String message="";
        GroupBuyQualification groupBuyQualification= groupBuyQualifyMapper.getGroupBuyQualifyByCode(qualifyCode);
        if(groupBuyQualification==null){
            message="fail";
        }else {
             double value= margins.doubleValue();
                if(value>=100000){
                    groupBuyQualification.setStatus(QualifyStatus.QUALIFY_ACTIVE.toString());
                }else {
                    groupBuyQualification.setStatus(QualifyStatus.QUALIFY_NOT_ENOUGH.toString());
                }
            groupBuyQualification.setMargins(margins);
            groupBuyQualification.setComment(comment);
            groupBuyQualifyMapper.updateGroupBuyQualifyById(groupBuyQualification);
            message="succeed";
        }
        map.put("message",message);
        return map;
    }

    //查询申请放弃团购资质列表
    @RequestMapping("/groupBuy/getGiveupQualifyList")
    @VerifyAuthentication(TraderAssistant = true, LegalPersonnel = true, Admin = true, Operation = true)
    public Object getGiveupQualifyList(int page,int userid){
        Map map = new HashMap();
        Pager<GroupBuyQualification> pagerResult = groupBuyQualifyMapper.pageGroupBuyQualifyByStatusId( QualifyStatus.QUALIFY_GIVEUP.toString(),userid,page,10);
        map.put("pagerResult",pagerResult);
        return map;
    }

    //审批放弃团购资质
    @VerifyAuthentication(TraderAssistant = true,Admin = true)
    @RequestMapping("/groupBuy/confirmGiveupQualify")
    public Object confirmGiveupQualify(String qualifyCode){
        groupBuyQualifyMapper.updateStatusByCode(qualifyCode, QualifyStatus.QUALIFY_GIVEUPED.toString());
        boolean success = true;
        return JSON.toString(success);
    }

    //保存团购发布图片
    @RequestMapping("/groupBuy/saveGroupBuyPic")
    public Object saveGroupBuyPic(@RequestParam("file") MultipartFile file) throws Exception{
        String filePath = fileService.uploadPicture(file);
        return JSON.toString(filePath);
    }
}
