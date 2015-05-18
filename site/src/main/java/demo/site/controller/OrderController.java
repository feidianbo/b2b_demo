package demo.site.controller;

import com.mysql.jdbc.StringUtils;
import freemarker.template.TemplateException;
import demo.core.domain.*;
import demo.core.persistence.BuyMapper;
import demo.core.persistence.CompanyMapper;
import demo.core.persistence.OrderMapper;
import demo.core.persistence.PaymentMapper;
import demo.core.service.PDF;
import demo.site.basic.FileDownload;
import demo.site.basic.JsonController;
import demo.site.basic.annotation.LoginRequired;
import demo.site.basic.exception.NotFoundException;
import demo.site.service.Auth;
import demo.site.service.BuyService;
import demo.site.service.FileService;
import demo.site.service.Freemarker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jack on 15/1/17.
 */
@Controller
@LoginRequired
public class OrderController extends JsonController {
    @Autowired
    private BuyMapper buyMapper;
    @Autowired
    private BuyService buyService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private Freemarker freemarker;
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    protected FileService fileService;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private Auth auth;
    private String contractcontent;

    public static class MyOrderForm {
        protected int id;
        @NotNull(message = "购买数量不能为空")
        protected int amount;
        @NotNull(message = "提货时间不能为空")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        protected LocalDate deliverytime1;
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        protected LocalDate deliverytime2;
        protected int paytype;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
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

        public int getPaytype() {
            return paytype;
        }

        public void setPaytype(int paytype) {
            this.paytype = paytype;
        }
    }

    //新增Order
    @RequestMapping(value = "/buy/addOrder", method = RequestMethod.POST)
    @ResponseBody
    public Object addMyOrder(@Valid MyOrderForm myOrderForm, BindingResult bindingResult) {
        Map<String, Object> map = new HashMap<String, Object>();
        int id = 0;
        SellInfo sellInfo = buyMapper.getSellInfoById(myOrderForm.id);
        if (sellInfo == null || !sellInfo.getStatus().equals(EnumSellInfo.VerifyPass) || myOrderForm.deliverytime1 == null) throw new NotFoundException();
        int price = sellInfo.getYkj() == 0 ? buyService.getJTJByAmontId(myOrderForm.amount, myOrderForm.id) : sellInfo.getYkj();
        if (myOrderForm.deliverytime1.isBefore(LocalDate.now().minusDays(1))) {
            bindingResult.rejectValue("", "timeerror", "提货时间不能在今天之前！");
        } else {
            EnumOrder paytype = (myOrderForm.paytype == 0 ? EnumOrder.PayTheWhole : EnumOrder.PayCashDeposit);
            boolean isfutures = (myOrderForm.deliverytime1.minusDays(7).isAfter(LocalDate.now()) == true ? true : false);
            BigDecimal totalmoney = BigDecimal.valueOf(price * myOrderForm.amount);
            if (myOrderForm.deliverytime2 != null && myOrderForm.deliverytime1.isAfter(myOrderForm.deliverytime2)) {
                bindingResult.rejectValue("", "timeerror", "提货时间区间填写错误！");
            } else if (myOrderForm.deliverytime2 != null && myOrderForm.deliverytime2.isAfter(sellInfo.getDeliverytime2())) {
                bindingResult.rejectValue("", "timeover", "提货时间必须在卖家提货时间内！");
            } else if (sellInfo.getDeliverytime1().isAfter(myOrderForm.deliverytime1) || myOrderForm.deliverytime1.isAfter(sellInfo.getDeliverytime2())) {
                bindingResult.rejectValue("", "timeover", "提货时间必须在卖家提货时间内！");
            } else if (sellInfo.getAvailquantity() < myOrderForm.amount){
                bindingResult.rejectValue("", "amountexceed", "认购瓶数不能超过卖家的可销售库存！");
            } else if (sellInfo.getSeller().equals("自营")) {
                id = buyService.addZYOrder(new Order(sellInfo.getPid(), myOrderForm.id, EnumOrder.WaitConfirmed, BigDecimal.valueOf(price), myOrderForm.amount, LocalDateTime.now(), myOrderForm.deliverytime1, myOrderForm.deliverytime2, sellInfo.getSeller(), sellInfo.getDeliveryregion(), sellInfo.getDeliveryprovince(), sellInfo.getDeliveryplace(), sellInfo.getOtherharbour(), sellInfo.getDeliverymode(), totalmoney, totalmoney, session.getUser().getNickname(), session.getUser().getSecurephone(), session.getUser().getId(), EnumOrder.MallOrder, paytype, isfutures, sellInfo.getSellerid(), sellInfo.getDealerid(), sellInfo.getDealername(), sellInfo.getDealerphone(),sellInfo.getRegionId(),sellInfo.getProvinceId(),sellInfo.getPortId()));
            } else {
                id = buyService.addWTOrder(new Order(sellInfo.getPid(), myOrderForm.id, EnumOrder.MakeMatch, BigDecimal.valueOf(price), myOrderForm.amount, LocalDateTime.now(), myOrderForm.deliverytime1, myOrderForm.deliverytime2, sellInfo.getSeller(), sellInfo.getDeliveryregion(), sellInfo.getDeliveryprovince(), sellInfo.getDeliveryplace(), sellInfo.getOtherharbour(), sellInfo.getDeliverymode(), totalmoney, session.getUser().getNickname(), session.getUser().getSecurephone(), session.getUser().getId(), EnumOrder.OtherOrder, sellInfo.getSellerid(), sellInfo.getDealerid(), sellInfo.getDealername(), sellInfo.getDealerphone(),sellInfo.getRegionId(),sellInfo.getProvinceId(),sellInfo.getPortId()));
            }
        }
        map.put("id", id);
        map.put("result", json(bindingResult));
        return map;
    }

    //检查可售库存是否充足
    @RequestMapping(value = "/buy/checkAmount", method = RequestMethod.POST)
    @ResponseBody
    public Object doCheckAmount(@RequestParam(value = "id", required = true) int id,
                                @RequestParam(value = "buyNum", required = true) int buyNum) {
        SellInfo sellInfo = buyMapper.getSellInfoById(id);
        if (sellInfo == null || !sellInfo.getStatus().equals(EnumSellInfo.VerifyPass)) throw new NotFoundException();
        if (buyNum > sellInfo.getAvailquantity()) {
            return false;
        }
        return true;
    }

    //判断时间是否超过7天
    @RequestMapping("/check7days")
    @ResponseBody
    public Object doCheckDays(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "deliverytime1", required = true) LocalDate deliverytime1) {
        if (!deliverytime1.minusDays(7).isAfter(LocalDate.now())) {
            return true;
        }
        return false;
    }

    //修改订单
    @RequestMapping("/buy/editOrder")
    public String doEditOrder(@RequestParam(value = "id", required = true) int id,
                              Map<String, Object> model) {
        Order order = orderMapper.getOrderById(id);
        if (order == null) throw new NotFoundException();
        auth.doCheckUserRight(order.getUserid());
        SellInfo sellInfo = buyMapper.getSellInfoById(order.getSellinfoid());
        if(sellInfo == null || !sellInfo.getStatus().equals(EnumSellInfo.VerifyPass)) throw new NotFoundException();
        if (order.getStatus().equals(EnumOrder.WaitConfirmed) && !order.isIschange()) {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            TransactionStatus status = transactionManager.getTransaction(def);
            buyService.plusSellinfoQuantity(order.getAmount(), order.getSellinfoid());
            orderMapper.setOrderStatus(EnumOrder.Deleted, order.getId());
            if(!order.isIschange()) {
                transactionManager.commit(status);
                orderMapper.setOrderIsChange(id);
            } else {
                transactionManager.rollback(status);
            }
        }
        buyService.showJTJ(order.getSellinfoid(), model);
        model.put("totalmoney", order.getTotalmoney());
        model.put("order", order);
        model.put("sellInfo", buyMapper.getSellInfoById(order.getSellinfoid()));
        return "fixedInfo";
    }

    public void contractContent(int id, HttpServletRequest request, Map<String, Object> model) throws IOException, TemplateException, ServletException {
        Order order = orderMapper.getOrderById(id);
        if (order == null) throw new NotFoundException();
        auth.doCheckUserRight(order.getUserid());
        SellInfo sellInfo = buyMapper.getSellInfoById(order.getSellinfoid());
        if (sellInfo == null) throw new NotFoundException();
        Company company = companyMapper.getCompanyByUserid(order.getUserid());
        if (company == null) throw new NotFoundException();
        String contracttype = null;
        switch (order.getDeliverymode()){
            case "港口平仓":
                contracttype = "/contracts/portUnwinding";
                break;
            case "到岸舱底":
                contracttype = "/contracts/shoreBottom";
                break;
            case "场地自提":
                if(order.getPaytype().equals(EnumOrder.PayTheWhole)){
                    contracttype = "/contracts/spaceDeliveryPayAll";
                } else{
                    contracttype = "/contracts/spaceDeliveryPayHalf";
                }
                break;
            default:
                break;
        }
        String contract = freemarker.render(contracttype, new HashMap<String, Object>() {{
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
            //order表信息
            put("contractno", order.getContractno());
            put("createtime", order.getCreatetime().toLocalDate());
            put("orderpid", order.getPid());
            put("orderamount", order.getAmount());
            put("orderprice", order.getPrice());
            put("orderdeliveryregion", order.getDeliveryregion());
            put("orderdeliveryprovince", order.getDeliveryprovince());
            put("orderdeliveryplace", order.getDeliveryplace().equals("其它") == true ? order.getOtherharbour() : order.getDeliveryplace());
            put("orderdeliverytime1year", String.valueOf(order.getDeliverytime1().getYear()));
            put("orderdeliverytime1month", order.getDeliverytime1().getMonthValue());
            put("orderdeliverytime1day", order.getDeliverytime1().getDayOfMonth());
            if(order.getDeliverymode().equals("场地自提")) {
                put("orderdeliverytime2year", String.valueOf(order.getDeliverytime2().getYear()));
                put("orderdeliverytime2month", order.getDeliverytime2().getMonthValue());
                put("orderdeliverytime2day", order.getDeliverytime2().getDayOfMonth());
            }
            //sellinfo表信息
            put("sellInfoNCV", String.valueOf(sellInfo.getNCV()));
            put("sellInfoRS", sellInfo.getRS());
            put("sellInfoinspectorg", sellInfo.getInspectorg());

            put("localhost", getCurrentURL(request));
        }});
        contractcontent = contract;
        model.put("order", order);
        model.put("company", company);
        model.put("sellInfo", sellInfo);
        model.put("contract", contract);
    }

    //确认订单
    @RequestMapping("/mall/confirmOrder")
    public String doConfirmOrder(@RequestParam(value = "id", required = true) int id,
                                 HttpServletRequest request, Map<String, Object> model) throws IOException, TemplateException, ServletException {
        Order order = orderMapper.getOrderById(id);
        if (order == null) throw new NotFoundException();
        auth.doCheckUserRight(order.getUserid());
        if (order.getStatus().equals(EnumOrder.WaitConfirmed) || order.getStatus().equals(EnumOrder.WaitSignContract)) {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            TransactionStatus status = transactionManager.getTransaction(def);
            orderMapper.addOrdersInfo(new OrdersInfo(EnumOrder.WaitSignContract, session.getUser().getNickname(), session.getUser().getId(), order.getId(), order.getOrderid(), "确认订单"));
            if(order.getStatus().equals(EnumOrder.WaitConfirmed)) {
                transactionManager.commit(status);
                orderMapper.setOrderStatus(EnumOrder.WaitSignContract, id);
                contractContent(id, request, model);
                return "contract";
            } else {
                transactionManager.rollback(status);
                return "/";
            }
        }
        return "/";
    }

    //去签合同
    @RequestMapping("/mall/contract")
    public String doSignContract(@RequestParam(value = "id", required = true)int id,
                                 HttpServletRequest request, Map<String, Object> model) throws IOException, TemplateException, ServletException {
        Order order = orderMapper.getOrderById(id);
        if(order == null) throw new NotFoundException();
        auth.doCheckUserRight(order.getUserid());
        if(order.getStatus().equals(EnumOrder.WaitSignContract)){
            contractContent(id, request, model);
            return "contract";
        }
        return "/";
    }

    //下载电子合同
    @RequestMapping("/downloadContract")
    public void downDZHT(@RequestParam(value="id", required = true)int id, HttpServletResponse response) throws Exception {
        Order order = orderMapper.getOrderById(id);
        if(order == null) throw new NotFoundException();
        auth.doCheckUserRight(order.getUserid());
        String filename = order.getContractno() + ".pdf";
        File target = fileService.getDownloadFileByFileName(filename);
        if(!target.exists()) {
            File file = PDF.create(contractcontent);
            fileService.copyToDownload(file, filename);
        }
        FileDownload.doDownloadFile(target, response);
    }

    //查看合同
    @RequestMapping("/account/showContract")
    public String showContract(@RequestParam(value="orderId", required = true)int id,
                               HttpServletRequest request, Map<String, Object> model) throws IOException, TemplateException, ServletException {
        contractContent(id, request, model);
        return "showContract";
    }

    //签订电子合同
    @RequestMapping("/mall/elecContract")
    @ResponseBody
    public Object doElecContract(@RequestParam(value = "id", required = true) int id) {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean success = false;
        String error = "";
        Order order = orderMapper.getOrderById(id);
        if (order == null) throw new NotFoundException();
        auth.doCheckUserRight(order.getUserid());
        if (order.getStatus().equals(EnumOrder.Canceled)) {
            error = "cancel";
        } else if (order.getStatus().equals(EnumOrder.WaitSignContract)) {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            TransactionStatus status = transactionManager.getTransaction(def);
            orderMapper.addOrdersInfo(new OrdersInfo(EnumOrder.WaitPayment, session.getUser().getNickname(), session.getUser().getId(), order.getId(), order.getOrderid(), "已签电子合同，待付款"));
            if(order.getStatus().equals(EnumOrder.WaitSignContract)){
                transactionManager.commit(status);
                orderMapper.setOrderStatus(EnumOrder.WaitPayment, id);
                success = true;
            } else{
                transactionManager.rollback(status);
                error = "repeat";
            }
        } else {
            error = "repeat";
        }
        map.put("success", success);
        map.put("error", error);
        return map;
    }

    //去付款
    @RequestMapping("/mall/payment")
    public String goPayment(@RequestParam(value = "id", required = true) int id, Map<String, Object> model) {
        Order order = orderMapper.getOrderById(id);
        if (order == null) throw new NotFoundException();
        auth.doCheckUserRight(order.getUserid());
        if (order.getStatus().equals(EnumOrder.WaitPayment) || order.getStatus().equals(EnumOrder.WaitBalancePayment) || order.getStatus().equals(EnumOrder.VerifyNotPass)) {
            model.put("order", order);
            return "payment";
        }
        return "/";
    }

    //保存支付凭证
    @RequestMapping(value = "/saveCertificationPic", method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired
    public Object savePayDocumentPic(@RequestParam(value = "id", required = true)int id,
                                     @RequestParam("file") MultipartFile file,
                                     HttpServletResponse response) throws Exception{
        if(file.getSize() / 1000 / 1000 <= 10) {
            Map<String, Object> map = new HashMap<String, Object>();
            response.setContentType("text/html");
            String picSavePath = fileService.uploadPicture(file);
            if (StringUtils.isNullOrEmpty(picSavePath)) throw new NotFoundException();
            Order order = orderMapper.getOrderById(id);
            auth.doCheckUserRight(order.getUserid());
            if (order == null) throw new NotFoundException();
            Payment payment = new Payment(order.getOrderid(), order.getId(), session.getUser().getId(), session.getUser().getNickname(), picSavePath);
            paymentMapper.addPayment(payment);
            int pid = payment.getId();
            map.put("picSavePath", picSavePath);
            map.put("pid", pid);
            return map;
        }
        return null;
    }

    //删除支付凭证
    @RequestMapping(value = "/deleteCertificationPic", method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired
    public void deletePayDocumentPic(@RequestParam(value="id", required = true)int id){
        Payment payment = paymentMapper.getPaymentById(id);
        if(payment == null) throw new NotFoundException();
        paymentMapper.deletePaymentById(id);
    }

    //我的订单详情
    @RequestMapping("/mall/OrderInfo")
    public String doGetMyOrders(@RequestParam(value = "id", required = false) int id,
                                String reqsource, Map<String, Object> model) {
        Order order = orderMapper.getOrderById(id);
        if (order == null) throw new NotFoundException();
        buyService.orderInfos(order, reqsource, model);
        return "orderInfo";
    }

    //获取阶梯价
    @RequestMapping("/getjtj")
    @ResponseBody
    public Object getJTJ(int amount, int id) {
        SellInfo sellInfo = buyMapper.getSellInfoById(id);
        if (sellInfo == null) throw new NotFoundException();
        return buyService.getJTJByAmontId(amount, id);
    }

    //可售库存是否充足
    @RequestMapping("/checkAvailableAmount")
    @ResponseBody
    public Object getAvailAmount(@RequestParam(value = "id", required = true)int id,
                                 @RequestParam(value = "amount", required = false, defaultValue = "1")int amount){
        SellInfo sellInfo = buyMapper.getSellInfoById(id);
        if(sellInfo == null) throw new NotFoundException();
        return sellInfo.getAvailquantity() < amount ? false : true;
    }

    public Object getCurrentURL(HttpServletRequest request) throws UnknownHostException {
        return "http://" + request.getServerName() + ":" + request.getServerPort();
    }

}



