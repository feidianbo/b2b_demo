package demo.site.basic;

import demo.core.domain.*;
import demo.core.persistence.*;
import demo.core.service.SMS;
import demo.site.basic.exception.NotFoundException;
import demo.site.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
* Created by fanjun on 14-12-23.
*/
@Configuration
@EnableScheduling
public class ScheduledTasks {
    @Autowired
    protected TimeTaskMapper timeTaskMapper;
    @Autowired
    protected OrderMapper orderMapper;
    @Autowired
    protected BuyMapper buyMapper;
    @Autowired
    protected SMS sms;
    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected PaymentMapper paymentMapper;
    @Autowired
    protected BuyService buyService;
    @Autowired
    private ProviderInfoMapper providerInfoMapper;
    @Autowired
    private GroupBuyQualificationMapper groupBuyQualifyMapper;
    @Autowired
    private GroupBuySupplyMapper groupBuySupplyMapper;
    @Autowired
    private GroupBuyOrderMapper groupBuyOrderMapper;

    @Scheduled(fixedRate = 180000)
    public void doCheckOrderStatus() throws Exception {
        //1个小时不确认订单，订单删除
        List<Order> orderWaitList = orderMapper.getOrderListByStatus(EnumOrder.WaitConfirmed);
        for(Order order : orderWaitList){
            if(order.getLastupdatetime().plusHours(1).isBefore(LocalDateTime.now())){
                buyService.plusSellinfoQuantity(order.getAmount(), order.getSellinfoid());
                orderMapper.setOrderStatus(EnumOrder.Deleted, order.getId());
                if(buyMapper.getSellInfoById(order.getSellinfoid()) == null){
                    throw new NotFoundException();
                }
                orderMapper.addOrdersInfo(new OrdersInfo(EnumOrder.Deleted, "XX网", 0, order.getId(), order.getOrderid(), "1小时不确认订单，XX网将订单删除。"));
            }
        }

        //2个小时不签订电子合同，订单取消
        List<Order> orderContractList = orderMapper.getOrderListByStatus(EnumOrder.WaitSignContract);
        for(Order order : orderContractList){
            if(order.getLastupdatetime().plusHours(2).isBefore(LocalDateTime.now())){
                orderMapper.setOrderStatus(EnumOrder.Canceled, order.getId());
                orderMapper.addOrdersInfo(new OrdersInfo(EnumOrder.Canceled, "XX网", 0, order.getId(), order.getOrderid(), "超过2个小时未签订电子合同，XX网自动取消订单。"));
                if(buyMapper.getSellInfoById(order.getSellinfoid()) == null) throw new NotFoundException();
                buyService.plusSellinfoQuantity(order.getAmount(), order.getSellinfoid());
            }
        }

        //24小时不付款，订单取消
        List<Order> orderPayList = orderMapper.getOrderListByStatus(EnumOrder.WaitPayment);
        for(Order order : orderPayList){
            if(order.getLastupdatetime().plusDays(1).isBefore(LocalDateTime.now())){
                orderMapper.setOrderStatus(EnumOrder.Canceled, order.getId());
                orderMapper.addOrdersInfo(new OrdersInfo(EnumOrder.Canceled, "XX网", 0, order.getId(), order.getOrderid(), "超过24小时未支付订单，XX网自动取消订单"));
                if(buyMapper.getSellInfoById(order.getSellinfoid()) == null) throw new NotFoundException();
                buyService.plusSellinfoQuantity(order.getAmount(), order.getSellinfoid());
            }
        }

        //供应信息过期后，将其状态改为已过期
        List<SellInfo> sellInfoList = buyMapper.getAllSellInfo(EnumSellInfo.VerifyPass);
        for(SellInfo sellInfo : sellInfoList){
            if(sellInfo.getDeliverytime2().isBefore(LocalDate.now())){
                buyMapper.updateSellInfoStatus(EnumSellInfo.OutOfDate, "已过期，XX网将该产品下架", sellInfo.getId());
            }
            /*if(sellInfo.getAvailquantity() <= 0 && sellInfo.getLastupdatetime().plusDays(7).isBefore(LocalDateTime.now())){
                buyMapper.updateSellInfoStatus(EnumSellInfo.OutOfStack, "产品卖完7天后，XX网将该产品下架", sellInfo.getId());
            }*/
        }

        //供应信息，60分钟不确认，系统将其删除
        List<SellInfo> sellInfoWaitList = buyMapper.getAllSellInfo(EnumSellInfo.WaitConfirmed);
        for(SellInfo sellinfo : sellInfoWaitList){
            if(sellinfo.getCreatetime().plusHours(1).isBefore(LocalDateTime.now())){
                buyMapper.deleteSellinfo(sellinfo.getId());
            }
        }

        //供应信息

    }

    /*@Scheduled(cron="0 31 9 * *")
    public void sendRemindSMS() throws Exception {
        //待付尾款的订单，提货日前3天，
        List<Order> orderLeftMoneyList = orderMapper.getOrderListByStatus("待付尾款");
        for(int i=0; i<orderLeftMoneyList.size(); i++) {
            Order order = orderLeftMoneyList.get(i);
            if ((order.getDeliverytime2() != null && order.getDeliverytime2().minusDays(4).isBefore(LocalDate.now()) && LocalDate.now().isBefore(order.getDeliverytime2())) || (order.getDeliverytime2() == null && order.getDeliverytime1().minusDays(4).isBefore(LocalDate.now()) && LocalDate.now().isBefore(order.getDeliverytime1()))) {
                if (LocalDateTime.now().getHour() == 9) {
                    User user = userMapper.getUserById(order.getUserid());
                    if(user != null) {
                        String hellowordsClient = "温馨提示：";
                        String contentClient = "贵公司在XX网上的采购订单，订单编号：" + order.getOrderid() + "，已到达最后付款日，请尽快补交尾款。";
                        String signature = "【XX网】";
                        sms.send(user.getSecurephone(), contentClient, hellowordsClient, signature); //发送短信验证码
                        String hellowordsDealer = "您好：";
                        String contentDealer = "您的客户，" + order.getSeller() + "在XX网上的采购订单，订单编号：" + order.getOrderid() + "，已到达最后付款日，请尽快催促买方付款。";
                        sms.send(order.getDealerphone(), contentDealer, hellowordsDealer, signature);
                    }
                }
            }
        }
    }*/

    //second, minute, hour, day, month, weekday
    @Scheduled(cron="0 0 9 14 4 ?")
    public void scheduleTaskUsingCronExpression() throws Exception {
        List<User> userInfoList = userMapper.getAllInfoUserList();
        if(userInfoList != null && userInfoList.size() != 0){
            String content = "感谢您成为会员！温馨提示：请您完善企业信息，以便享受更加快捷、安全的交易功能与服务。";
            String signature = "【XX网】";
            String hellowords = "";
            for(User user : userInfoList) {
                sms.send(user.getSecurephone(), content, hellowords, signature);
                System.out.println("@@@@@@@@@@@@@@@@" + user.getSecurephone() + "----------------------");
            }
        }
    }

    /* fj
     1.每天0点检查修改今天报价截止的需求记录改为报价结束
     2.将超过7天的记录状态改为删除
     3.过了报价截止时间后,交易状态改为匹配中
     4.提货截止时间到后,将我的需求表中匹配中的记录改为交易结束
     5.将报价表到了报价截止日期的记录改为比价中
     6.将报价表过了提货时间截止仍没有中标的改为未中标
     */



    @Scheduled(cron="0 0 1 * * *")
    public void modifyTradeStatus(){
        timeTaskMapper.modifyTradestatusTask();
        timeTaskMapper.modifyIsdeleteTask();
        timeTaskMapper.modifyStatusToMatchingTask();
        timeTaskMapper.modifyStatusToTradeOverTask();
        timeTaskMapper.modifyStatusToCompareQuoteTask();
        timeTaskMapper.modifyStatusToNotBidTask();
    }

    /* fj
    "0 0 * * * *" = the top of every hour of every day.
    "*//*10 * * * * *" = every ten seconds.
    "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
    "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day.
    "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
    "0 0 0 25 12 ?" = every Christmas Day at midnight
    分别对应的是 秒 分 时 日 月 周 年
    前5个是必须的，最后一个可选*/

    //检查团购截止时间，修改状态
    @Scheduled(fixedRate = 3600000)
    public void updateGroupBuyStatus(){
        List<GroupBuySupply> groupBuySupplies= groupBuySupplyMapper.getGroupBuySuppliesByStatus(GroupBuySupplyStatus.GROUP_BUY_SUPPLY_INPROGRESS.toString());
        List<GroupBuySupply> deadlineList=new ArrayList<GroupBuySupply>();
        for(GroupBuySupply gbs:groupBuySupplies){
            if(gbs.getGroupbuyenddate().isBefore(LocalDateTime.now())){
                deadlineList.add(gbs);
            }
        }
        if(deadlineList.size()>0){
            for(GroupBuySupply gs:deadlineList) {
                if(gs.getSupplyamount()==gs.getSelledamount()&&gs.getSurplusamount()==0) {
                    groupBuySupplyMapper.updateStatusById(gs.getId(), GroupBuySupplyStatus.GROUP_BUY_SUPPLY_FINISH.toString());
                    List<GroupBuyOrder> groupBuyOrders = groupBuyOrderMapper.getOrdersBySupplyId(gs.getId());
                    if (groupBuyOrders != null) {
                        for (GroupBuyOrder bgo : groupBuyOrders) {
                            groupBuyOrderMapper.updateStatusById(bgo.getId(), OrderStatus.ORDER_FINISH.toString());
                            groupBuyQualifyMapper.updateStatusByCode(bgo.getQualificationcode(), QualifyStatus.QUALIFY_FINISH.toString());
                        }
                    }
                }else {
                    groupBuySupplyMapper.updateStatusById(gs.getId(), GroupBuySupplyStatus.GROUP_BUY_SUPPLY_FAIL.toString());
                    List<GroupBuyOrder> groupBuyOrders = groupBuyOrderMapper.getOrdersBySupplyId(gs.getId());
                    if (groupBuyOrders != null) {
                        for (GroupBuyOrder bgo : groupBuyOrders) {
                            groupBuyOrderMapper.updateStatusById(bgo.getId(), OrderStatus.ORDER_FAIL.toString());
                            groupBuyQualifyMapper.updateStatusByCode(bgo.getQualificationcode(), QualifyStatus.QUALIFY_ACTIVE.toString());
                        }
                    }
                }
            }
        }

    }
}
