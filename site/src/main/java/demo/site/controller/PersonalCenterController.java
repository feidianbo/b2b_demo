package demo.site.controller;

import demo.core.domain.*;
import demo.core.persistence.*;
import demo.core.util.PageQueryParam;
import demo.site.basic.JsonController;
import demo.site.basic.annotation.LoginRequired;
import demo.site.basic.exception.NotFoundException;
import demo.site.service.Auth;
import demo.site.service.BuyService;
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

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fanjun on 14-12-13.
 * 个人中心
 * 包括我的订单,账号管理,我的关注,我的发布,我的报价,五个部分
 */
@LoginRequired
@Controller
public class PersonalCenterController extends JsonController {
    @Autowired
    private Session session;
    @Autowired
    protected QuoteMapper quoteMapper;
    @Autowired
    protected CompanyMapper companyMapper;
    @Autowired
    protected BuyMapper buyMapper;
    @Autowired
    protected BuyService buyService;
    @Autowired
    protected MydemandMapper mydemandMapper;
    @Autowired
    protected DemandMapper demandMapper;
    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected OrderMapper orderMapper;
    @Autowired
    protected MyInterestMapper myInterestMapper;
    @Autowired
    protected PaymentMapper paymentMapper;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private Auth auth;

    @RequestMapping(value = "/account/individualCenter")
    public String individualCenter(Map<String, Object> model){
            Company company =  companyMapper.getCompanyByUserid(session.getUser().getId());
            User user = userMapper.getUserByPhone(session.getUser().getSecurephone());
            model.put("personPhone",session.getUser().getSecurephone());
            model.put("company",company);
            model.put("users",user);
            return "individualCenter";
    }

    //账号信息
    @RequestMapping(value = "/account/accountInfo")
    public String accountInfo(Map<String, Object> model){
            Company company =  companyMapper.getCompanyByUserid(session.getUser().getId());
            User user = userMapper.getUserByPhone(session.getUser().getSecurephone());
            model.put("personPhone",session.getUser().getSecurephone());
            model.put("company",company);
            model.put("users",user);
            return "individualCenter";
    }

    //认证公司信息
    @RequestMapping(value = "/account/getMyAccount")
    public String Certification(Map<String, Object> model){
            model.put("certification","certification");
            return "individualCenter";
    }

    //账号安全
    @RequestMapping(value = "/account/accountSecurity")
    public String accountSecurity(Map<String, Object> model){
            model.put("accountSecurity","accountSecurity");
            return "individualCenter";
    }

    //我的发布-需求
    @RequestMapping(value = "/account/getMyDemand")
    public String myRelease(@RequestParam(value = "pagesize", required = false, defaultValue = "10")int pagesize,
                            PageQueryParam param,Map<String, Object> model){
        List<Mydemand> demandList = mydemandMapper.getTurnpageListWithUserid(session.getUser().getId(), param.getIndexNum(), pagesize);
        int demandTotalCount = mydemandMapper.countAllMydemands(session.getUser().getId()); //总数
        model.put("demandList",demandList);
        model.put("demandTotalCount",demandTotalCount);
        model.put("demandPagesize", pagesize);
        model.put("pageNumber",param.getPage());
        return "individualCenter";
    }

    //我的发布-需求查看
    /*
        resume: 审核未通过时的重新发布
        chooseQuote: 匹配中的选择报价
     */
    @RequestMapping(value = "/account/viewMyDemand")
    public String viewMyDemand(String demandcode,String reqsource,String resume,String chooseQuote,Map<String, Object> model){
        Demand demand = null;
        if(demandcode != null) {
            if (resume != null && resume != "") {
                model.put("resume", resume);
            }
            if (chooseQuote != null && chooseQuote != "") {
                model.put("chooseQuote", chooseQuote);
            }
             demand = demandMapper.getDemandByDemandcodeAndUserid(demandcode,session.getUser().getId());
            if(demand == null){
                throw new NotFoundException();
            }
            //所有对应此需求的报价列表
            if (reqsource != null && reqsource != "") {
                if (reqsource.equals("myrelease")) {
                    List<Quote> quoteList = quoteMapper.getQuoteByDemandcode(demandcode);
                    if (quoteList != null && quoteList.size() > 0) {
                        model.put("quoteList", quoteList);
                    }

                }
            }
        }
        model.put("demand",demand);
        model.put("reqsource", reqsource);
        return "checkDemandInfo";
    }

    //我的发布-需求删除
    @RequestMapping(value = "/account/deleteMydemand")
    @ResponseBody
    public String deleteMydemand(String demandcode){
        if(demandcode != null){
            //删除我的需求,因为主表已经有所有信息,不需要保留.并且我的需求表没有设删除状态字段
            mydemandMapper.deleteMyDemandByDemandcode(demandcode,session.getUser().getId());
        }
        return JSON.toString("success");
    }

    //我的需求-取消发布
    @RequestMapping(value = "/account/cancelMydemand")
    @ResponseBody
    public String cancelMydemand(String demandcode){
        String message = "success";
        if(demandcode != null){
            Demand demand = demandMapper.getDemandByDemandcodeAndUserid(demandcode,session.getUser().getId());
            if(demand.getCheckstatus().equals("待审核")) {
                //将主表的需求状态设置为已删除,保留信息
                demandMapper.modifyIsdeleteByDemandcodeAndUserid(demandcode, session.getUser().getId());
                //将我的需求表的状态改为交易结束
                mydemandMapper.modifyStatusByDemandcodeAndUserid("交易结束", demandcode, session.getUser().getId());
                //如果针对需求有报价,将报价状态改为未中标
                List<Quote> quoteList = quoteMapper.getQuoteByDemandcode(demandcode);
                if (quoteList != null && quoteList.size() > 0) {
                    for (Quote quote : quoteList) {
                        quoteMapper.modifyStatusByQuoteid("未中标", quote.getId());
                    }
                }
            } else{
              message = "hasChecked";
            }
        }
        return JSON.toString(message);
    }

    public void showMyOrder(int pagesize, PageQueryParam param, Map<String, Object> model){
        List<Order> orderBuyGoingList = orderMapper.getSevenStatusOrdersBuy(session.getUser().getId(),
                EnumOrder.WaitSignContract, EnumOrder.WaitPayment, EnumOrder.WaitVerify, EnumOrder.VerifyPass,
                EnumOrder.VerifyNotPass, EnumOrder.WaitBalancePayment, EnumOrder.MakeMatch,
                pagesize, param.getIndexNum());
        int countBuyGo = orderMapper.countSevenStatusOrdersBuy(session.getUser().getId(),
                EnumOrder.WaitSignContract, EnumOrder.WaitPayment, EnumOrder.WaitVerify, EnumOrder.VerifyPass,
                EnumOrder.VerifyNotPass, EnumOrder.WaitBalancePayment, EnumOrder.MakeMatch);
        model.put("pageNumBuyGo", param.getPage());
        model.put("pagesizeBuyGo", pagesize);
        model.put("countBuyGo", countBuyGo);
        model.put("orderBuyGoList", orderBuyGoingList);
    }

    //我的订单-买货订单-进行中
    @RequestMapping(value = "/account/getMyOrders")
    public String myOrder(@RequestParam(value="pagesize", required = false, defaultValue = "10")int pagesize,
                          PageQueryParam param, Map<String, Object> model) {
        showMyOrder(pagesize, param, model);
        return "individualCenter";
    }

    @RequestMapping(value = "/account/payMyOrders")
    @ResponseBody
    public Object payMyOrder(@RequestParam(value = "id", required = true)int id) {
        Order order = orderMapper.getOrderById(id);
        if(order.getStatus().equals(EnumOrder.WaitPayment) || order.getStatus().equals(EnumOrder.WaitBalancePayment) || order.getStatus().equals(EnumOrder.VerifyNotPass)) {
            orderMapper.setOrderStatus(EnumOrder.WaitVerify, order.getId());
            orderMapper.addOrderVerify(new OrderVerify(EnumOrder.WaitVerify, LocalDateTime.now(), id, session.getUser().getId()));
            orderMapper.addOrdersInfo(new OrdersInfo(EnumOrder.WaitVerify, session.getUser().getNickname(), session.getUser().getId(), order.getId(), order.getOrderid(), "上传支付凭证完成，待审核"));
            return true;
        }
        return false;
    }

    //我的订单-卖货订单-进行中
    @RequestMapping(value="/account/getMySellOrders")
    public String mySellOrder(@RequestParam(value = "pagesize", required = false, defaultValue = "10")int pagesize,
                              PageQueryParam param, Map<String, Object> model){
        List<Order> orderSellGoingList = orderMapper.getOneStatusOrdersSell(session.getUser().getId(), EnumOrder.MakeMatch, EnumOrder.OtherOrder, pagesize, param.getIndexNum());
        int countSellGo = orderMapper.countOneStatusOrdersSell(session.getUser().getId(), EnumOrder.MakeMatch, EnumOrder.OtherOrder);
        model.put("pageNumSellGo", param.getPage());
        model.put("pagesizeSellGo", pagesize);
        model.put("countSellGo", countSellGo);
        model.put("orderSellGoList", orderSellGoingList);
        return "individualCenter";
    }

    //我的订单-买货订单-已完成
    @RequestMapping(value = "/account/getMyFinishedOrders")
    @LoginRequired
    public String getMyFinishedOrders(@RequestParam(value = "pagesize", required = false, defaultValue = "10")int pagesize,
                                      PageQueryParam param, Map<String, Object> model) {
        List<Order> orderCompList = orderMapper.getTwoStatusOrdersBuy(session.getUser().getId(), EnumOrder.Completed, EnumOrder.ReturnCompleted, pagesize, param.getIndexNum());
        int countComp = orderMapper.countTwoStatusOrdersBuy(session.getUser().getId(), EnumOrder.Completed, EnumOrder.ReturnCompleted);
        model.put("pageNumComp", param.getPage());
        model.put("pagesizeComp", pagesize);
        model.put("countComp", countComp);
        model.put("orderCompList", orderCompList);
        return "individualCenter";
    }

    //我的订单-卖货订单-已完成
    @RequestMapping(value = "/account/getMySellFinishedOrders")
    public String getMySellFinishedOrders(@RequestParam(value = "pagesize", required = false, defaultValue = "10")int pagesize,
                                          PageQueryParam param, Map<String, Object> model) {
        List<Order> orderCompList = orderMapper.getOneStatusOrdersSell(session.getUser().getId(), EnumOrder.Completed, EnumOrder.OtherOrder, pagesize, param.getIndexNum());
        int countComp = orderMapper.countOneStatusOrdersSell(session.getUser().getId(), EnumOrder.Completed, EnumOrder.OtherOrder);
        model.put("pageNumSellComp", param.getPage());
        model.put("pagesizeSellComp", pagesize);
        model.put("countSellComp", countComp);
        model.put("orderSellCompList", orderCompList);
        return "individualCenter";
    }

    //我的订单-买货订单-已取消
    @RequestMapping(value = "/account/getMyCanceledOrders")
    public String getMyCancelOrders(@RequestParam(value = "pagesize", required = false, defaultValue = "10")int pagesize,
                                    PageQueryParam param, Map<String, Object> model) {
        List<Order> orderCancelList = orderMapper.getOneStatusOrdersBuy(session.getUser().getId(), EnumOrder.Canceled, pagesize, param.getIndexNum());
        int countCancel = orderMapper.countOneStatusOrdersBuy(session.getUser().getId(), EnumOrder.Canceled);
        model.put("pageNumCancel", param.getPage());
        model.put("pagesizeCancel", pagesize);
        model.put("countCancel", countCancel);
        model.put("orderCancelList", orderCancelList);
        return "individualCenter";
    }

    //我的订单-卖货订单-已取消
    @RequestMapping(value = "/account/getMySellCanceledOrders")
    public String getMySellCancelOrders(@RequestParam(value = "pagesize", required = false, defaultValue = "10")int pagesize,
            PageQueryParam param, Map<String, Object> model) {
        List<Order> orderCancelList = orderMapper.getOneStatusOrdersSell(session.getUser().getId(), EnumOrder.Canceled, EnumOrder.OtherOrder, pagesize, param.getIndexNum());
        int countCancel = orderMapper.countOneStatusOrdersSell(session.getUser().getId(), EnumOrder.Canceled, EnumOrder.OtherOrder);
        model.put("pageNumSellCancel", param.getPage());
        model.put("pagesizeSellCancel", pagesize);
        model.put("countSellCancel", countCancel);
        model.put("orderSellCancelList", orderCancelList);
        return "individualCenter";
    }

    //我的订单-买货订单-退货中
    @RequestMapping(value = "/account/getMyReturnedOrders")
    public String getMyReturnOrders(@RequestParam(value = "pagesize", required = false, defaultValue = "10")int pagesize,
                                    PageQueryParam param, Map<String, Object> model) {
        List<Order> orderReturnList = orderMapper.getOneStatusOrdersBuy(session.getUser().getId(), EnumOrder.ReturnGoods, pagesize, param.getIndexNum());
        int countReturn = orderMapper.countOneStatusOrdersBuy(session.getUser().getId(), EnumOrder.ReturnGoods);
        model.put("pageNumReturn", param.getPage());
        model.put("pagesizeReturn", param.getPageSize());
        model.put("countReturn", countReturn);
        model.put("orderReturnList", orderReturnList);
        return "individualCenter";
    }

    //申请退货
    @RequestMapping("/account/applyReturnGoods")
    @ResponseBody
    public Object doApplyReturnGoods(@RequestParam(value="id", required = true)int id){
        Map<String, Object> map = new HashMap<String, Object>();
        Order order = orderMapper.getOrderById(id);
        if(order == null) throw new NotFoundException();
        auth.doCheckUserRight(order.getUserid());
        if(!order.getStatus().equals(EnumOrder.ReturnGoods) || !order.getStatus().equals(EnumOrder.Completed)) {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            TransactionStatus status = transactionManager.getTransaction(def);
            orderMapper.addOrderReturn(new OrderReturn(order.getStatus(), order.getId(), order.getOrderid(), session.getUser().getId(), session.getUser().getNickname(), false));
            orderMapper.addOrdersInfo(new OrdersInfo(EnumOrder.ReturnGoods, session.getUser().getNickname(), session.getUser().getId(), order.getId(), order.getOrderid(), "申请退货"));
            if(!order.getStatus().equals(EnumOrder.ReturnGoods)) {
                transactionManager.commit(status);
                orderMapper.setOrderStatus(EnumOrder.ReturnGoods, id);
            } else{
                transactionManager.rollback(status);
            }
        }
        map.put("success", true);
        return map;
    }

    //撤销退货
    @RequestMapping("/account/cancelReturnGoods")
    @ResponseBody
    public Object doCancelReturnGoods(@RequestParam(value="id", required = true)int id){
        Order order = orderMapper.getOrderById(id);
        if(order == null) throw new NotFoundException();
        auth.doCheckUserRight(order.getUserid());
        OrderReturn orderReturn = orderMapper.getOrderReturnByOrderId(order.getId());
        if(orderReturn == null) throw new NotFoundException();
        if(order.getStatus().equals(EnumOrder.ReturnGoods)) {
            orderMapper.addOrdersInfo(new OrdersInfo(orderReturn.getLaststatus(), session.getUser().getNickname(), session.getUser().getId(), order.getId(), order.getOrderid(), "取消退货"));
            orderMapper.setOrderStatus(orderReturn.getLaststatus(), id);
            orderMapper.setOrderReturnIsCanceled(orderReturn.getId());
            return true;
        } else{
            return false;
        }
    }

    //取消订单
    @RequestMapping("/account/cancelOrder")
    @ResponseBody
    public Object doCancelOrder(@RequestParam(value = "id", required = true)int id, @RequestParam(value = "cancelType", required = false)String cancelType){
        Map<String, Object> map = new HashMap<String, Object>();
        boolean success = false;
        Order order = orderMapper.getOrderById(id);
        if(order == null || order.getPid() == null) throw new NotFoundException();
        auth.doCheckUserRight(order.getUserid());
        SellInfo sellInfo = buyMapper.getSellInfoById(order.getSellinfoid());
        if(sellInfo == null) throw new NotFoundException();
        if(order.getOrdertype().equals(EnumOrder.MallOrder)) {
            if(!order.getStatus().equals(EnumOrder.Canceled)) {
                orderMapper.setOrderStatus(EnumOrder.Canceled, id);
                buyService.plusSellinfoQuantity(order.getAmount(), order.getSellinfoid());
                success = true;
            } else{
                success = false;
            }
        } else if(order.getOrdertype().equals(EnumOrder.OtherOrder)){
            if(order.getSellerstatus().equals(EnumOrder.Completed)){
                buyService.plusSellinfoQuantity(order.getAmount(), order.getSellinfoid());
            }
            orderMapper.setOrderStatus(EnumOrder.Canceled, id);
            success = true;
        }
        orderMapper.addOrdersInfo(new OrdersInfo(EnumOrder.Canceled, session.getUser().getNickname(), session.getUser().getId(), order.getId(), order.getOrderid(), "取消订单"));
        map.put("cancelType", cancelType);
        map.put("success", success);
        return map;
    }

    //删除订单-买货
    @RequestMapping("/account/deleteOrder")
    @ResponseBody
    public Object doDeleteBuyOrder(@RequestParam(value = "id", required = true)int id){
        Order order = orderMapper.getOrderById(id);
        if(order == null) throw new NotFoundException();
        auth.doCheckUserRight(order.getUserid());
        orderMapper.setOrderStatus(EnumOrder.Deleted, id);
        orderMapper.addOrdersInfo(new OrdersInfo(EnumOrder.Deleted, session.getUser().getNickname(), session.getUser().getId(), order.getId(), order.getOrderid(), "删除订单--买货订单"));
        return true;
    }

    //删除订单-卖货
    @RequestMapping("/account/deleteSellOrder")
    @ResponseBody
    public Object doDeleteSellOrder(@RequestParam(value = "id", required = true)int id, @RequestParam(value="delType", required = true)String delType){
        Map<String, Object> map = new HashMap<String, Object>();
        Order order = orderMapper.getOrderById(id);
        if(order == null || order.getOrderid() == null) throw new NotFoundException();
        auth.doCheckUserRight(order.getSellerid());
        orderMapper.setSellerStatusOrder(EnumOrder.Deleted, id);
        orderMapper.addOrdersInfo(new OrdersInfo(EnumOrder.Deleted, session.getUser().getNickname(), session.getUser().getId(), order.getId(), order.getOrderid(), "删除订单--卖货订单"));
        map.put("delType", delType);
        map.put("success", true);
        return map;
    }

    //我的关注列表-产品
    @RequestMapping("/account/getMyInterest")
    public String getMyInterest(@RequestParam(value = "pagesize", required = false, defaultValue = "10")int pagesize,
                                Map<String, Object> model, PageQueryParam param){
        List<MyInterest> mySupplyList = myInterestMapper.getMyInterestList("supply", session.getUser().getId(), pagesize, param.getIndexNum());
        int supplyCount = myInterestMapper.getMyInterestCount("supply", session.getUser().getId());
        model.put("pageNumber", param.getPage());
        model.put("pagesizeSupply", pagesize);
        model.put("supplyMap", mySupplyList);
        model.put("supplyCount", supplyCount);
        return "individualCenter";
    }

    //我的关注列表-需求
    @RequestMapping("/account/getMyInterestDemand")
    public String getMyInterestDemand(@RequestParam(value = "pagesize", required = false, defaultValue = "10")int pagesize,
                                      Map<String, Object> model, PageQueryParam param){
        List<MyInterest> myDemandList = myInterestMapper.getMyInterestList("demand", session.getUser().getId(), pagesize, param.getIndexNum());
        int demandCount = myInterestMapper.getMyInterestCount("demand", session.getUser().getId());
        model.put("pageNumber", param.getPage());
        model.put("pagesizeDemand", pagesize);
        model.put("demandMap", myDemandList);
        model.put("demandCount", demandCount);
        return "individualCenter";
    }

    //取消我的关注
    @RequestMapping("/account/cancelMyInterest")
    @ResponseBody
    public Object doCancelMyInterest(int id){
        MyInterest myInterest = myInterestMapper.getMyInterestById(id);
        if(myInterest == null) throw new NotFoundException();
        if(!myInterest.isIsdelete()) {
            myInterestMapper.cancelMyInterest(id);
        }
        return true;
    }

    //个人中心-我的报价-删除
    @RequestMapping(value = "/account/deleteMyquote", method = RequestMethod.POST)
    @ResponseBody
    public String deleteMyquote(@RequestParam("id") Integer id){
        quoteMapper.modifyIsdeleteById(id,session.getUser().getId());
        return JSON.toString("success");
    }

    //我的报价-进行中
    @RequestMapping(value = "/account/getMyQuote")
    public String myQuoteUnderway(@RequestParam(value = "pagesize", required = false, defaultValue = "10")int pagesize,
                                  PageQueryParam param,Map<String, Object> model){
        List<Quote> quoteList = quoteMapper.getTurnpageUnderwayList(session.getUser().getId(), param.getIndexNum(), pagesize);
        int totalCount = quoteMapper.countAllMyQuoteUnderway(session.getUser().getId()); //总数
        model.put("quoteUnderwayList", quoteList);
        model.put("underwayTotalCount", totalCount);
        model.put("pagesizeQuote", pagesize);
        return "individualCenter";
    }

    //我的报价-查看
    @RequestMapping(value = "/account/viewMyQuote")
    public String viewMyQuote(@RequestParam("demandid") Integer demandid, String reqsource,Map<String, Object> model){
        Demand demand = demandMapper.getDemandById(demandid);
        if(demand == null){
            throw new NotFoundException();}
        Quote quote = quoteMapper.getQuoteByUserIdAndDemandid(session.getUser().getId(),demandid);
        if(reqsource != null && reqsource != ""){
            model.put("reqsource",reqsource);
        }
        model.put("demand", demand);
        model.put("quote", quote);
        model.put("modifyPrice","modifyPrice");
        return "sellInfo";
    }

    //我的报价-已中标
    @RequestMapping(value = "/account/getMyQuoteBid")
    public String myQuoteBid(@RequestParam(value = "pagesize", required = false, defaultValue = "10")int pagesize,
                             PageQueryParam param,Map<String, Object> model){
        List<Quote> quoteList = quoteMapper.getTurnpageBidList(session.getUser().getId(), param.getIndexNum(), pagesize);
        int totalCount = quoteMapper.countAllMyQuoteBid(session.getUser().getId()); //总数
        model.put("quoteBidList", quoteList);
        model.put("bidTotalCount", totalCount);
        model.put("pagesizeBid", pagesize);
        return "individualCenter";
    }

    //我的报价-未中标
    @RequestMapping(value = "/account/getMyQuoteNotBid")
    public String myQuoteNotBid(@RequestParam(value = "pagesize", required = false, defaultValue = "10")int pagesize,
                                PageQueryParam param,Map<String, Object> model){
        List<Quote> quoteList = quoteMapper.getTurnpageNotBidList(session.getUser().getId(), param.getIndexNum(), pagesize);
        int totalCount = quoteMapper.countAllMyQuoteNotBid(session.getUser().getId()); //总数
        model.put("quoteNotBidList", quoteList);
        model.put("notBidTotalCount", totalCount);
        model.put("pagesizeNotBid", pagesize);
        return "individualCenter";
    }

    //查看供应信息详细
    @RequestMapping("/account/getSupplyDetail")
    public String doGetSellDetail(int id, String reqsource, Map<String, Object> model){
        SellInfo sellInfo = buyMapper.getSellInfoById(id);
        if (sellInfo == null) throw new NotFoundException();
        auth.doCheckUserRight(sellInfo.getSellerid());
        buyService.showJTJ(id, model);
        buyService.showEditHist(sellInfo.getId(),sellInfo.getParentid(),model);
        model.put("supplyInfo", sellInfo);
        model.put("reqsource", reqsource);
        return "checkSupplyInfo";
    }

    //我的供应
    @RequestMapping(value = "/account/getMySupply")
    public String doGetMySupply(@RequestParam(value = "pagesize", required = false, defaultValue = "10")int pagesize,
                                PageQueryParam param,Map<String, Object> model){
        List<SellInfo> sellInfoList = buyMapper.getSellinfoByUserid(session.getUser().getId(), pagesize, param.getIndexNum());
        int count = buyMapper.getSellinfoCountByUserid(session.getUser().getId());
        model.put("supplyList", sellInfoList);
        model.put("count", count);
        model.put("pagesizeMySupply", pagesize);
        model.put("pageNumber", param.getPage());
        return "individualCenter";
    }

    //删除供应信息
    @RequestMapping("/account/deleteMySupply")
    @ResponseBody
    public Object doDeleteMySupply(int id){
        SellInfo sellInfo = buyMapper.getSellInfoById(id);
        if(sellInfo == null)  throw new NotFoundException();
        auth.doCheckUserRight(sellInfo.getSellerid());
        if(!sellInfo.getStatus().equals(EnumSellInfo.Deleted)) {
            buyService.deleteSellinfo(id);
        }
        return true;
    }

    //取消供应信息
    @RequestMapping("/account/cancelRelease")
    @ResponseBody
    public Object doCancelSellinfo(@RequestParam("supplyId")int id){
        Map<String, Object> map = new HashMap<String, Object>();
        boolean success = false;
        String error = null;
        SellInfo sellInfo = buyMapper.getSellInfoById(id);
        if (sellInfo == null) throw new NotFoundException();
        auth.doCheckUserRight(sellInfo.getSellerid());
        int ordersCount = orderMapper.countEightStatusOrdersBuy(sellInfo.getPid(), EnumOrder.ReturnGoods,
                EnumOrder.WaitSignContract, EnumOrder.WaitPayment, EnumOrder.WaitVerify, EnumOrder.VerifyPass,
                EnumOrder.VerifyNotPass, EnumOrder.WaitBalancePayment, EnumOrder.MakeMatch);
        if(ordersCount != 0){
            error = "order";
        } else {
            buyMapper.cancelSellInfoById(id);
            success = true;
        }
        map.put("error", error);
        map.put("success", success);
        return map;
    }

    //个人中心-账号信息,固定电话和qq
    @RequestMapping(value = "/account/saveMyaccount", method = RequestMethod.POST)
    @ResponseBody
    public String saveMyaccount(String telephone,String qq){
        if(telephone != null || qq != null){
            userMapper.modifyPhoneAndQQ(telephone,qq,session.getUser().getId());
        }
        return JSON.toString("success");
    }


    //我的订单详情
    @RequestMapping("/account/OrderInfo")
    public String doGetMyOrders(@RequestParam(value="id", required = true)int id, String reqsource, Map<String, Object> model) {
        Order order = orderMapper.getOrderById(id);
        if (order == null) throw new NotFoundException();
        buyService.orderInfos(order, reqsource, model);
        return "orderInfo";
    }

    @RequestMapping("/account/getOrderStatus")
    @ResponseBody
    public Object doGetSellinfoStatus(@RequestParam(value = "id", required = true) int id) {
        Order order = orderMapper.getOrderById(id);
        if (order == null) throw new NotFoundException();
        return order.getStatus();
    }

}

