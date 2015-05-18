package demo.site.controller;

import freemarker.template.TemplateException;
import demo.core.domain.*;
import demo.core.persistence.*;
import demo.core.service.PDF;
import demo.core.util.PageQueryParam;
import demo.site.basic.FileDownload;
import demo.site.basic.JsonController;
import demo.site.basic.annotation.LoginRequired;
import demo.site.basic.exception.NotFoundException;
import demo.site.service.FileService;
import demo.site.service.Freemarker;
import demo.site.service.Session;
import org.eclipse.jetty.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by zhangbolun on 15/1/19.
 */
@Controller
public class GroupBuyController extends JsonController {
    @Autowired
    private Session session;
    @Autowired
    private ProviderInfoMapper providerInfoMapper;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private GroupBuyQualificationMapper groupBuyQualifyMapper;
    @Autowired
    private GroupBuySupplyMapper groupBuySupplyMapper;
    @Autowired
    private GroupBuyOrderMapper groupBuyOrderMapper;
    @Autowired
    private Freemarker freemarker;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    protected FileService fileService;
    private String contractcontent;

    //团购首页信息列表
    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public String getAllGroupBuySupplys(Map<String, Object> model){
        model.put("groupBuySupplies", groupBuySupplyMapper.getRemindGroupBuySupplies());
        return "group";
    }

    //获取团购资质
    @RequestMapping(value = "/group/getGroupCertification")
    @LoginRequired
    public String doGetGroupCertification(HttpServletRequest request, Map<String, Object> model) throws IOException, TemplateException, ServletException {
        GroupBuyQualification groupBuyQualification =  new GroupBuyQualification(session.getUser().getId(), QualifyStatus.QUALIFY_START.toString());
        groupBuyQualifyMapper.addGroupBuyQualify(groupBuyQualification);
        contractContent(groupBuyQualification.getId(), null, request, model);
        return "groupQualification";
    }

    //上传保证金图片，申请团购资质
    @LoginRequired
    @RequestMapping(value = "/group/saveCompanyPic", method = RequestMethod.POST)
    @ResponseBody
    public Object saveCompanyPic(@RequestParam(value = "id", required = true)int id,
                                 @RequestParam("file") MultipartFile file, HttpServletResponse response) throws Exception{
        response.setContentType("text/html");
        GroupBuyQualification groupBuyQualification = groupBuyQualifyMapper.getGroupBuyQualifyById(id);
        if(groupBuyQualification == null) throw new NotFoundException();
        Map<String, Object> map = new HashMap<String, Object>();
        String picSavePath = fileService.uploadPicture(file);
        map.put("picSavePath", picSavePath);
        groupBuyQualification.setPhotopath(picSavePath);
        groupBuyQualification.setStatus(QualifyStatus.QUALIFY_APPLY.toString());
        groupBuyQualification.setContractverify(true);
        groupBuyQualifyMapper.updateGroupBuyQualifyById(groupBuyQualification);
        map.put("id", id);
        return map;
    }

    //删除团购资质图片
    @RequestMapping(value = "/group/deletePicture")
    @ResponseBody
    @LoginRequired
    public Object doDeletePicture(@RequestParam(value = "id", required = true)int id){
        GroupBuyQualification groupBuyQualification = groupBuyQualifyMapper.getGroupBuyQualifyById(id);
        if(groupBuyQualification == null) throw new NotFoundException();
        groupBuyQualifyMapper.deletePictureById(id);
        groupBuyQualifyMapper.setGroupBuyQualificationStatus(QualifyStatus.QUALIFY_START.toString(), id);
        return true;
    }

    //检查用户是否有可用团购资质
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/group/checkActiveQualify", method = RequestMethod.POST)
    public Object checkActiveQualify(){
        Map<String, Object> map = new HashMap<String, Object>();
        boolean success = false;
        List<GroupBuyQualification> groupBuyQualificationList = groupBuyQualifyMapper.getGroupBuyQualifyByUserid(session.getUser().getId());
        if(groupBuyQualificationList.size() == 0){
            map.put("error", "none");
        } else {
            for(GroupBuyQualification groupBuyQualification : groupBuyQualificationList){
                if(groupBuyQualification.getStatus().equals(QualifyStatus.QUALIFY_ACTIVE.toString())){
                    success = true;
                    break;
                }
            }
        }
        map.put("success", success);
        return map;
    }

    //查看团购详细信息
    @LoginRequired
    @RequestMapping(value = "/group/showGroupInfo", method = RequestMethod.GET)
    public String groupBuySupplyDetail(@RequestParam(value = "groupBuySupplyId", required = true)int groupBuySupplyId, Map<String, Object> model){
        GroupBuySupply groupBuySupply = groupBuySupplyMapper.getGroupBuySupplyById(groupBuySupplyId);
        if(groupBuySupply == null) throw new NotFoundException();

        ProviderInfo providerInfo = providerInfoMapper.getProviderInfoById(groupBuySupply.getProviderinfoid());
        if(providerInfo == null) throw new NotFoundException();
        //获取最新成交记录
        List<TransactionOrder> transactionOrders = new ArrayList<TransactionOrder>();
        List<GroupBuyOrder> groupBuyOrders = groupBuyOrderMapper.getTransactionOrders();
        for(GroupBuyOrder groupBuyOrder : groupBuyOrders){
            TransactionOrder transactionOrder = new TransactionOrder();
            transactionOrder.setVolume(groupBuyOrder.getVolume());
            GroupBuySupply temp = groupBuySupplyMapper.getGroupBuySupplyById(groupBuyOrder.getGroupbuysupplyid());
            if(temp != null){
                transactionOrder.setGroupbuyprice(temp.getGroupbuyprice());
                transactionOrder.setCoaltype(temp.getCoaltype());
                transactionOrder.setDeliverytime(groupBuyOrder.getCreatetime());
                transactionOrder.setMarketprice(temp.getMarketprice());
                transactionOrder.setPort(temp.getPort());
            }
            transactionOrders.add(transactionOrder);
        }
        model.put("groupBuySupply", groupBuySupply);
        model.put("providerInfo", providerInfo);
        model.put("transactionOrders", transactionOrders);
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        model.put("serverdatetime",df.format(new Date()));
        return "groupActivityDetail";
    }

    //验证团购订单
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/group/checkGroupBuyOrder", method = RequestMethod.POST)
    public Object checkGroupBuyOrder(@RequestParam(value = "groupBuySupplyId", required = true)int groupBuySupplyId,@RequestParam(value = "volume", required = true)int volume){
        GroupBuySupply groupBuySupply = groupBuySupplyMapper.getGroupBuySupplyById(groupBuySupplyId);
        Map<String, Object> map = new HashMap<String, Object>();
        boolean success = false;
        String message="test";
        if(groupBuySupply!=null){
            LocalDateTime now=LocalDateTime.now();
            if(groupBuySupply.getGroupbuybegindate().isBefore(now)&&groupBuySupply.getGroupbuyenddate().isAfter(now)&&volume>=groupBuySupply.getMinimumamount()) {
                success= true;
            }else {
                if(volume<groupBuySupply.getMinimumamount()) {
                    message= "minimumamount";
                }else if(groupBuySupply.getGroupbuybegindate().isAfter(now)){
                    message= "notbegin";
                }else {
                    message= "finished";
                }
            }
        }
        map.put("success",success);
        map.put("message",message);
        return map;
    }

    //生成团购订单信息
    @LoginRequired
    @RequestMapping(value = "/group/generateGroupBuyOrder", method = RequestMethod.GET)
    public String generateGroupBuyOrder(@RequestParam(value = "groupBuySupplyId", required = true)int groupBuySupplyId,
                                        @RequestParam(value = "volume", required = true)int volume,
                                        @RequestParam(value = "type", required = false, defaultValue = "null")String type,
                                        Map<String, Object> model){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        GroupBuySupply groupBuySupply = groupBuySupplyMapper.getGroupBuySupplyById(groupBuySupplyId);
        //绑定的资质
        GroupBuyQualification groupBuyQualify = groupBuyQualifyMapper.getGroupBuyQualifyByStatusId(session.getUser().getId(), QualifyStatus.QUALIFY_ACTIVE.toString()).get(0);
        //生成团购订单
        GroupBuyOrder groupBuyOrder = new GroupBuyOrder();
        groupBuyOrder.setUserid(session.getUser().getId());
        groupBuyOrder.setCreatetime(LocalDateTime.now());
        groupBuyOrder.setStatus(OrderStatus.ORDER_CREATE.toString());
        groupBuyOrder.setGroupbuysupplyid(groupBuySupplyId);
        groupBuyOrder.setVolume(volume);
        groupBuyOrder.setQualificationcode(groupBuyQualify.getQualificationcode());
        groupBuyOrder.setGroupbuysupplycode(groupBuySupply.getGroupbuysupplycode());
        //保存团购订单
        groupBuyOrderMapper.deleteOrder(groupBuySupply.getGroupbuysupplycode(), groupBuyQualify.getQualificationcode());
        groupBuyOrderMapper.addGroupBuyOrder(groupBuyOrder);
        GroupBuyOrder groupBuyOrderReturn = groupBuyOrderMapper.getOrderById(groupBuyOrder.getId());

        if (groupBuySupply.getSurplusamount()>=groupBuyOrderReturn.getVolume()) {
            transactionManager.commit(status);
            model.put("groupBuyOrder", groupBuyOrderReturn);
            model.put("groupBuySupply", groupBuySupply);
            model.put("volume", volume);
            model.put("groupbuyordercode", groupBuyOrderReturn.getGroupbuyordercode());
            model.put("type", type);
            return "purchaseDetail";
        } else {
            transactionManager.rollback(status);
            groupBuySupply = groupBuySupplyMapper.getGroupBuySupplyById(groupBuySupplyId);
            if(groupBuySupply == null) throw new NotFoundException();

            ProviderInfo providerInfo = providerInfoMapper.getProviderInfoById(groupBuySupply.getProviderinfoid());
            if(providerInfo == null) throw new NotFoundException();
            //获取最新成交记录
            List<TransactionOrder> transactionOrders = new ArrayList<TransactionOrder>();
            List<GroupBuyOrder> groupBuyOrders = groupBuyOrderMapper.getTransactionOrders();
            for(GroupBuyOrder groupBuyOrderTemp : groupBuyOrders){
                TransactionOrder transactionOrder = new TransactionOrder();
                transactionOrder.setVolume(groupBuyOrderTemp.getVolume());
                GroupBuySupply temp = groupBuySupplyMapper.getGroupBuySupplyById(groupBuyOrderTemp.getGroupbuysupplyid());
                if(temp != null){
                    transactionOrder.setGroupbuyprice(temp.getGroupbuyprice());
                    transactionOrder.setCoaltype(temp.getCoaltype());
                    transactionOrder.setDeliverytime(groupBuyOrderTemp.getCreatetime());
                    transactionOrder.setMarketprice(temp.getMarketprice());
                    transactionOrder.setPort(temp.getPort());
                }
                transactionOrders.add(transactionOrder);
            }
            model.put("groupBuySupply", groupBuySupply);
            model.put("providerInfo", providerInfo);
            model.put("transactionOrders", transactionOrders);
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            model.put("serverdatetime",df.format(new Date()));
            return "groupActivityDetail";
        }

    }

    //提交团购订单,合同授权,更新团购供应量
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "/group/confirmGroupBuyOrder", method = RequestMethod.POST)
    public String confirmGroupBuyOrder(@RequestParam(value = "groupbuyordercode", required = true)String groupbuyordercode, @RequestParam(value = "volume", required = true)int volume){
        GroupBuyOrder groupBuyOrder = groupBuyOrderMapper.getOrderByCode(groupbuyordercode);
        if(groupBuyOrder!=null) {
            GroupBuySupply groupBuySupply = groupBuySupplyMapper.getGroupBuySupplyById(groupBuyOrder.getGroupbuysupplyid());
            int selledamount = groupBuySupply.getSelledamount() + volume;
            int surplusamount = groupBuySupply.getSupplyamount()-selledamount;
            if(surplusamount>=0) {
                if (groupBuySupply != null) {
                    LocalDateTime now=LocalDateTime.now();
                    //查看团购时间是否开始
                    if(groupBuySupply.getGroupbuybegindate().isBefore(now)&&groupBuySupply.getGroupbuyenddate().isAfter(now)) {
                        //事物并发处理
                        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
                        TransactionStatus status = transactionManager.getTransaction(def);

                        //设置资质状态已经绑定
                        GroupBuyQualification groupBuyQualify = groupBuyQualifyMapper.getGroupBuyQualifyByCode(groupBuyOrder.getQualificationcode());
                        if (groupBuyQualify != null) {
                            groupBuyQualifyMapper.bindOrderForQualify(groupBuyQualify.getId(), QualifyStatus.QUALIFY_INPROCESS.toString(), groupBuyOrder.getGroupbuyordercode());
                        } else {
                            return JSON.toString(false);
                        }
                        //增加团购订单量
                        groupBuySupply.setGroupbuyordercount(groupBuySupply.getGroupbuyordercount() + 1);
                        groupBuySupply.setStatus(GroupBuySupplyStatus.GROUP_BUY_SUPPLY_INPROGRESS.toString());
                        //更新团购供应量
                        groupBuySupply.setSelledamount(selledamount);
                        groupBuySupply.setSurplusamount(surplusamount);
                        groupBuySupplyMapper.updateGroupBuySupply(groupBuySupply);
                        //修改订单状态
                        groupBuyOrderMapper.updateStatusByOrderCode(groupBuyOrder.getGroupbuyordercode(), OrderStatus.ORDER_ACTIVE.toString());

                        if (surplusamount >= 0) {
                            transactionManager.commit(status);
                            return JSON.toString(true);
                        } else {
                            transactionManager.rollback(status);
                            return JSON.toString(false);
                        }
                    }else {
                        return JSON.toString(true);
                    }

                } else {
                    return JSON.toString(false);
                }
            } else{
                return JSON.toString(false);
            }
        } else {
            return JSON.toString(false);
        }
    }

    //个人中心获取我的团购资质列表
    @LoginRequired
    @RequestMapping(value = "/account/getMyQualification", method = RequestMethod.GET)
    public String getMyQualifcation(@RequestParam(value="pagesize", required = false, defaultValue = "10")int pagesize,
                                    PageQueryParam param, Map<String, Object> model){
        List<GroupBuyQualification> groupBuyQualificationList =  groupBuyQualifyMapper.getGroupBuyQualifyByStatusIdPage(session.getUser().getId(), pagesize, param.getIndexNum());
        int totalCount = groupBuyQualifyMapper.countGroupBuyQualifyBuStatusId(session.getUser().getId());
        model.put("groupBuyQualificationList", groupBuyQualificationList);
        model.put("qualifyCount", totalCount);
        model.put("pagesizeQualify", pagesize);
        model.put("qualifyPageNumber", param.getPage());
        return "individualCenter";
    }

    //个人中心-获取我的团购订单-进行中的团购
    @LoginRequired
    @RequestMapping(value = "/account/getMyOrderActive", method = RequestMethod.GET)
    public String getMyOrderActive(@RequestParam(value = "pagesize", required = false, defaultValue = "10")int pagesize,
                                   PageQueryParam param, Map<String, Object> model){
        List<GroupBuyOrder> groupBuyOrdersActiveList = groupBuyOrderMapper.getOrdersByUserId(session.getUser().getId(), OrderStatus.ORDER_ACTIVE.toString(), pagesize, param.getIndexNum());
        if(groupBuyOrdersActiveList!=null){
            for(GroupBuyOrder groupBuyOrder:groupBuyOrdersActiveList){
                GroupBuySupply groupBuySupply=  groupBuySupplyMapper.getGroupBuySupplyById(groupBuyOrder.getGroupbuysupplyid());
                if(groupBuySupply!=null){
                    groupBuyOrder.setGroupbuyprice(groupBuySupply.getGroupbuyprice());
                    groupBuyOrder.setGroupbuyenddate(groupBuySupply.getGroupbuyenddate());
                }
            }
        }
        int totalCount = groupBuyOrderMapper.countOrdersByUserId(session.getUser().getId(), OrderStatus.ORDER_ACTIVE.toString());
        model.put("groupBuyOrdersActiveList", groupBuyOrdersActiveList);
        model.put("activeSupplyCount", totalCount);
        model.put("activePageNumber", param.getPage());
        model.put("pagesizeGroupActive", pagesize);
        return "individualCenter";
    }

    //个人中心-获取我的团购订单-已结束的团购
    @LoginRequired
    @RequestMapping(value = "/account/getMyOrderFinish", method = RequestMethod.GET)
    public String getMyOrderFinish(@RequestParam(value = "pagesize", required = false, defaultValue = "10")int pagesize,
                                   PageQueryParam param, Map<String, Object> model){
        List<GroupBuyOrder> groupBuyOrdersFinishes= groupBuyOrderMapper.getOrdersByStatuses(session.getUser().getId(), OrderStatus.ORDER_FINISH.toString(),OrderStatus.ORDER_FAIL.toString(), pagesize, param.getIndexNum());
        if(groupBuyOrdersFinishes!=null){
            for(GroupBuyOrder groupBuyOrder:groupBuyOrdersFinishes){
                GroupBuySupply groupBuySupply=  groupBuySupplyMapper.getGroupBuySupplyById(groupBuyOrder.getGroupbuysupplyid());
                if(groupBuySupply!=null){
                    groupBuyOrder.setGroupbuyprice(groupBuySupply.getGroupbuyprice());
                    groupBuyOrder.setGroupbuyenddate(groupBuySupply.getGroupbuyenddate());
                }
            }
        }
        int totalCount = groupBuyOrderMapper.countOrdersByStatuses(session.getUser().getId(), OrderStatus.ORDER_FINISH.toString(), OrderStatus.ORDER_FAIL.toString());
        model.put("groupBuyOrdersFinishes",groupBuyOrdersFinishes);
        model.put("finishedSupplyCount", totalCount);
        model.put("pagesizeGroupFinish", pagesize);
        model.put("finishedPageNumber", param.getPage());
        return "individualCenter";
    }

    //个人中心-团购保证金-放弃资格
    @LoginRequired
    @RequestMapping(value = "/account/giveupQualify", method = RequestMethod.POST)
    @ResponseBody
    public Object giveupQualify(@RequestParam(value = "qualificationcode", required = true) String qualificationcode){
        GroupBuyQualification groupBuyQualify = groupBuyQualifyMapper.getGroupBuyQualifyByCode(qualificationcode);
        if(groupBuyQualify == null) throw new NotFoundException();
        if(groupBuyQualify.getStatus().equals(QualifyStatus.QUALIFY_ACTIVE.toString())) {
            groupBuyQualifyMapper.updateStatusByCode(qualificationcode, QualifyStatus.QUALIFY_GIVEUP.toString());
            return true;
        }
        return false;
    }

    //个人中心-进行中的团购-查看详情
    @LoginRequired
    @RequestMapping(value = "/account/selectOrderDetail", method = RequestMethod.GET)
    public String selectOrderDetail(@RequestParam(value = "groupbuyordercode", required = true)String groupbuyordercode,
                                    Map<String, Object> model){
        GroupBuyOrder groupBuyOrder = groupBuyOrderMapper.getOrderByCode(groupbuyordercode);
        if(groupBuyOrder == null) throw new NotFoundException();
        GroupBuySupply groupBuySupply = groupBuySupplyMapper.getGroupBuySupplyById(groupBuyOrder.getGroupbuysupplyid());
        if(groupBuySupply == null) throw new NotFoundException();
        model.put("groupBuyOrder", groupBuyOrder);
        model.put("groupBuySupply",groupBuySupply);
        return "purchaseDetail";
    }

    //是否删除
    @LoginRequired
    @RequestMapping(value = "/account/deleteFinishedGroupOrder", method = RequestMethod.POST)
    @ResponseBody
    public Object doDeleteGroupOrder(@RequestParam(value = "groupbuyordercode", required = true)String groupbuyordercode){
        GroupBuyOrder groupBuyOrder = groupBuyOrderMapper.getOrderByCode(groupbuyordercode);
        if(groupBuyOrder == null) throw new NotFoundException();
        groupBuyOrderMapper.deleteOrderByCode(groupbuyordercode);
        return true;
    }

    //下载电子合同
    @RequestMapping("/group/downloadContract")
    public void testHtml(@RequestParam(value="id", required = true)int id,
                         @RequestParam(value="type", required = true)String type,
                         HttpServletResponse response) throws Exception {
        GroupBuyQualification groupBuyQualification = groupBuyQualifyMapper.getGroupBuyQualifyById(id);
        if (groupBuyQualification == null) throw new NotFoundException();
        String filename = groupBuyQualification.getQualificationcode()+".pdf";
        File target = fileService.getDownloadFileByFileName(filename);
        if(!target.exists()) {
            File file = PDF.create(contractcontent);
            fileService.copyToDownload(file,filename);
        }
        FileDownload.doDownloadFile(target, response);
    }

    //查看合同
    @RequestMapping("/account/showDepositContract")
    public String showContract(@RequestParam(value = "id", required = true)int id,
                               @RequestParam(value = "type", required = true)String type,
                               @RequestParam(value = "root", required = true)String root,
                               HttpServletRequest request,
                               Map<String, Object> model) throws IOException, TemplateException, ServletException {
        contractContent(id, root, request, model);
        return "groupQualification";
    }

    //查看合同--履约金
    @RequestMapping("/account/showExecutionContract")
    public String showExecutionContract(@RequestParam(value = "id", required = true)int id,
                                        @RequestParam(value = "root", required = true)String root,
                                        HttpServletRequest request,
                                        Map<String, Object> model) throws IOException, TemplateException, ServletException {
        contractContent(id, root, request, model);
        return "showExecutionContract";
    }

    //电子合同
    public void contractContent(@RequestParam(value = "id", required = true)int id,
                                @RequestParam(value = "root", required = false, defaultValue = "null")String root,
                                HttpServletRequest request,
                                Map<String, Object> model) throws IOException, TemplateException, ServletException {
        GroupBuyQualification groupBuyQualification = groupBuyQualifyMapper.getGroupBuyQualifyById(id);
        if (groupBuyQualification == null) throw new NotFoundException();
        Company company = companyMapper.getCompanyByUserid(groupBuyQualification.getUserid());
        if (company == null) throw new NotFoundException();
        String contract = freemarker.render("/contracts/depositContract", new HashMap<String, Object>() {{
            put("contractno", groupBuyQualification.getMarginscode());
            put("createtime", LocalDateTime.now());
            //公司表信息
            put("companyname", company.getName());
            put("companyaddress", company.getAddress());
            put("companylegalpersonname", company.getLegalpersonname());
            put("companyphone", company.getPhone());
            put("companyopeningbank", company.getOpeningbank());
            put("companyaccount", company.getAccount());
            put("companyidentificationnumword", company.getIdentificationnumword());
            put("companyfax", company.getFax());
            put("companyzipcode", company.getZipcode());
            //签订时间
            put("sellsignyear", String.valueOf(LocalDate.now().getYear()));
            put("sellsignmonth", LocalDate.now().getMonthValue());
            put("sellsignday", LocalDate.now().getDayOfMonth());
            put("buysignyear", String.valueOf(LocalDate.now().getYear()));
            put("buysignmonth", LocalDate.now().getMonthValue());
            put("buysignday", LocalDate.now().getDayOfMonth());
            //
            put("localhost", getCurrentURL(request));
        }});
        contractcontent = contract;
        model.put("root", root);
        model.put("id", id);
        model.put("contract", contract);
    }

    public Object getCurrentURL(HttpServletRequest request)
            throws IOException, ServletException {
        return "http://" + request.getServerName() + ":" + request.getServerPort();
    }

}

