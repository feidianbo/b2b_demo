package demo.admin.controller;

import demo.admin.basic.FileDownload;
import demo.admin.basic.JsonController;
import demo.admin.basic.exception.NotFoundException;
import demo.core.domain.EnumOrder;
import demo.core.domain.Order;
import demo.core.domain.OrdersInfo;
import demo.core.domain.Payment;
import demo.core.persistence.OrderMapper;
import demo.core.persistence.PaymentMapper;
import demo.core.service.FileStore;
import demo.core.util.Pager;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jack on 14/12/12.
 */
@RestController
public class OrderVerController extends JsonController{
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private FileDownload fileDownload;
    @Autowired
    private FileStore fileStore;

    @RequestMapping("/certificate/wait")
    public Object waitList(@RequestParam(value = "content", required = false, defaultValue = "")String scontent,
                           @RequestParam(value = "pid", required = false, defaultValue = "")String tempPid,
                           int page){
        return new Object(){
            public String content = scontent;
            public String pid = tempPid;
            public Pager<Order> orderList = orderMapper.pageAllOrderBySelect(EnumOrder.WaitVerify, scontent, pid, page, 10);
        };
    }

    @RequestMapping("/certificate/tail")
    public Object tailList(@RequestParam(value = "content", required = false, defaultValue = "")String scontent,
                           @RequestParam(value = "pid", required = false, defaultValue = "")String tempPid,
                           int page){
        return new Object() {
            public String content = scontent;
            public String pid = tempPid;
            public Pager<Order> orderList = orderMapper.pageAllOrderBySelect(EnumOrder.WaitBalancePayment, scontent, pid, page, 10);
        };
    }

    @RequestMapping("/certificate/pass")
    public Object passList(@RequestParam(value = "content", required = false, defaultValue = "")String scontent,
                           @RequestParam(value = "pid", required = false, defaultValue = "")String tempPid,
                           int page){
        return new Object(){
            public String content = scontent;
            public String pid = tempPid;
            public Pager<Order> orderList = orderMapper.pageAllOrderBySelect(EnumOrder.VerifyPass, scontent, pid, page, 10);
        };
    }

    @RequestMapping("/certificate/fail")
    public Object failList(@RequestParam(value = "content", required = false, defaultValue = "")String scontent,
                           @RequestParam(value = "pid", required = false, defaultValue = "")String tempPid,
                           int page){
        return new Object(){
            public String content = scontent;
            public String pid = tempPid;
            public Pager<Order> orderList = orderMapper.pageAllOrderBySelect(EnumOrder.VerifyNotPass, scontent, pid, page, 10);
        };
    }

    @RequestMapping("/certificate/detail")
    public Object showOrderDetail(int id){
        Map<String, Object> map = new HashMap<String, Object>();
        Order order = orderMapper.getOrderById(id);
        if (order == null) throw new NotFoundException();
        map.put("order", order);
        List<Payment> paymentList = paymentMapper.getPaymentList(order.getId(), false);
        if(paymentList.size() > 0){
            map.put("payment1", paymentList.get(0));
            if(paymentList.size() > 1){
                map.put("payment2", paymentList.get(1));
                if(paymentList.size() > 2){
                    map.put("payment3", paymentList.get(2));
                }
            }
        }
        map.put("paymentList", paymentList);
        map.put("success", true);
        return map;
    }

    @RequestMapping("/order/checkCertification")
    public Object doCertificateOrderPaid(@RequestParam(value="id", required = true)int id,
                                         @RequestParam(value="checkStatus", required = true)String checkStatus,
                                         @RequestParam(value="comment", required = false, defaultValue = "")String comment,
                                         @RequestParam(value="id01", required = false, defaultValue = "0")int id01,
                                         @RequestParam(value="money01", required = false, defaultValue = "0")BigDecimal money01,
                                         @RequestParam(value="id02", required = false, defaultValue = "0")int id02,
                                         @RequestParam(value="money02", required = false, defaultValue = "0")BigDecimal money02,
                                         @RequestParam(value="id03", required = false, defaultValue = "0")int id03,
                                         @RequestParam(value="money03", required = false, defaultValue = "0")BigDecimal money03){
        Order order = orderMapper.getOrderById(id);
        if (order == null) throw new NotFoundException();
        Payment payment01 = paymentMapper.getPaymentById(id01);
        Payment payment02 = paymentMapper.getPaymentById(id02);
        Payment payment03 = paymentMapper.getPaymentById(id03);
        if(payment01 != null) paymentMapper.verifyPayment(false, BigDecimal.valueOf(0), session.getAdmin().getUsername(), session.getAdmin().getId(), id01);
        if(payment02 != null) paymentMapper.verifyPayment(false, BigDecimal.valueOf(0), session.getAdmin().getUsername(), session.getAdmin().getId(), id02);
        if(payment03 != null) paymentMapper.verifyPayment(false, BigDecimal.valueOf(0), session.getAdmin().getUsername(), session.getAdmin().getId(), id03);
        BigDecimal paidmoney = (money01.compareTo(BigDecimal.valueOf(0)) == 0 ? BigDecimal.valueOf(0) : money01.add(money02).add(money03).add(order.getPaidmoney()));
        BigDecimal waitmoney = (paidmoney.compareTo(order.getTotalmoney()) == -1 ? order.getTotalmoney().subtract(paidmoney):BigDecimal.valueOf(0));
        BigDecimal savemoney = (paidmoney.compareTo(order.getTotalmoney()) == 1 ? paidmoney.subtract(order.getTotalmoney()):BigDecimal.valueOf(0));
        orderMapper.setOrderVerifyMoney(paidmoney, waitmoney, savemoney, id);
        if (checkStatus.equals("审核未通过")) {
            orderMapper.verifyOrderPaidCredit(EnumOrder.VerifyNotPass, comment, id);
            orderMapper.verifyOrderVerifyPaidCredit(EnumOrder.VerifyNotPass, session.getAdmin().getUsername(), LocalDateTime.now(), comment, id);
            orderMapper.addOrdersInfo(new OrdersInfo(EnumOrder.VerifyNotPass, session.getAdmin().getUsername(), session.getAdmin().getId(), order.getId(), order.getOrderid(), comment));
        } else if(checkStatus.equals("审核通过")) {
            orderMapper.verifyOrderPaidCredit(EnumOrder.VerifyPass, comment, id);
            orderMapper.verifyOrderVerifyPaidCredit(EnumOrder.VerifyPass, session.getAdmin().getUsername(), LocalDateTime.now(), comment, id);
            orderMapper.addOrdersInfo(new OrdersInfo(EnumOrder.VerifyPass, session.getAdmin().getUsername(), session.getAdmin().getId(), order.getId(), order.getOrderid(), comment));
        } else{
            orderMapper.verifyOrderPaidCredit(EnumOrder.WaitBalancePayment, comment, id);
            orderMapper.verifyOrderVerifyPaidCredit(EnumOrder.WaitBalancePayment, session.getAdmin().getUsername(), LocalDateTime.now(), comment, id);
            orderMapper.addOrdersInfo(new OrdersInfo(EnumOrder.WaitBalancePayment, session.getAdmin().getUsername(), session.getAdmin().getId(), order.getId(), order.getOrderid(), comment));
        }
        return true;
    }

    //下载图片
    @RequestMapping("/certificate/downloadCertification")
    public HttpEntity<byte[]> doDownloadCertification(@RequestParam(value = "url", required = true)String url) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment",url);
        return new HttpEntity<byte[]>(FileUtils.readFileToByteArray(fileStore.getFileByFilePath(url)), headers);
    }

}
