package demo.admin.controller;

import com.itextpdf.text.DocumentException;
import demo.admin.annotation.VerifyAuthentication;
import demo.admin.basic.exception.NotFoundException;
import demo.admin.service.Buy;
import demo.admin.service.Session;
import demo.core.domain.*;
import demo.core.persistence.*;
import demo.ext.mybatis.Where;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shiling on 15-1-22.
 */
@Controller
public class OrderManageController implements Serializable {
    @Autowired
    private AreaportMapper areaportMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private BuyMapper buyMapper;
    @Autowired
    private OrderManageMapper orderManageMapper;
    @Autowired
    private Session session;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private Buy buy;
    @Autowired
    private PlatformTransactionManager transactionManager;


    //商城订单进行中 标示的状态参数
    private final EnumOrder[] underway=new EnumOrder[]{EnumOrder.WaitPayment, EnumOrder.WaitSignContract, EnumOrder.WaitVerify, EnumOrder.VerifyPass, EnumOrder.VerifyNotPass, EnumOrder.WaitBalancePayment};
    private final EnumOrder[] sellerUnderWay =new EnumOrder[]{EnumOrder.Completed, EnumOrder.Deleted};
    //商城订单退货中
    private final EnumOrder[] returnway=new EnumOrder[]{ EnumOrder.ReturnGoods };
    private final EnumOrder[] sellerReturnway=new EnumOrder[]{EnumOrder.ReturnCompleted, EnumOrder.Deleted};
    //商城订单已完成
    private final EnumOrder[] completeway=new EnumOrder[]{EnumOrder.Completed, EnumOrder.ReturnCompleted, EnumOrder.Deleted};
    private final EnumOrder[] sellerCompleteway=new EnumOrder[]{EnumOrder.Deleted};
    //商城订单已取消
    private final EnumOrder[] cancelway=new EnumOrder[]{EnumOrder.Canceled};
    private final EnumOrder[] sellerCancelway=new EnumOrder[]{EnumOrder.Deleted};
    //商城订单待复审
    private final EnumOrder[] recheckway=new EnumOrder[]{ EnumOrder.VerifyPass, EnumOrder.ReturnGoods};
    private final EnumOrder[] sellerRecheckway=new EnumOrder[]{EnumOrder.Deleted,EnumOrder.Canceled,EnumOrder.VerifyNotPass,EnumOrder.NULL};
    //撮合订单状态标识
    private final EnumOrder[] sellerMatchUnderway=new EnumOrder[]{ EnumOrder.Deleted, EnumOrder.Completed, EnumOrder.Canceled};
    private final int pageSize = 10;
    //进行中
    private final String UNDERWAY="underway";
    //退货中
    private final String RETURNWAY="returnway";
    //已取消
    private final String CANCELWAY="cancelway";
    //待复审
    private final String RECHECKWAY="recheckway";
    //已完成
    private final String COMPLETEWAY="completeway";

    //撮合订单进行中
    private final String MATCHUNDERWAY="matchunderway";
    //撮合订单已完成
    private final String MATCHCOMPLETEWAY="matchCompleteway";




    @RequestMapping(value = "/order/orderlist",method = RequestMethod.POST)
    @ResponseBody
    @VerifyAuthentication(Trader = true, TraderAssistant = true, Finance = true, Operation = true, Admin = true)
    public Object getMallOrders(@RequestParam Map<String,Object> params){
        Map<String,Object> map = new HashMap<String,Object>();
        int page = Integer.valueOf((String)params.get("page"));
        int regionId = Integer.valueOf((String) params.get("deliveryRegion"));
        int provinceId = 0;
        int portId =0;
        //回显页面上的值
        if(regionId!=0){
            provinceId=Integer.valueOf((String)params.get("deliveryProvince"));
            if(provinceId!=0){
                map.put("provinceList", areaportMapper.getProcvincesOrPortsByParentid(regionId));
                portId = Integer.valueOf((String)params.get("deliveryHarbour"));
                if(portId!=0){
                    map.put("harbourList", areaportMapper.getProcvincesOrPortsByParentid(provinceId));
                }
            }
        }
        //区分查询订单状态
        addStatusParams(params);
        map.put("deliveryRegion", regionId);
        map.put("deliveryProvince", provinceId);
        map.put("deliveryHarbour", portId);
        map.put("regionList", areaportMapper.getAllArea());
        map.put("startDate",params.get("startDate"));
        map.put("endDate",params.get("endDate"));
        map.put("orderid",params.get("orderid"));
        map.put("pid",params.get("pid"));
        map.put("orderList", orderManageMapper.findOrderByStatus(params,page,pageSize));
        return map;
    }

    private void addStatusParams(Map<String,Object> params){
        //判断查询订单状态
        String status = String.valueOf(params.get("status"));
        if(status.equals(UNDERWAY)){
            params.put("orderStatus",underway);
            params.put("sellerStatus",sellerUnderWay);
        }else if(status.equals(RETURNWAY)){
            params.put("orderStatus",returnway);
            params.put("sellerStatus",sellerReturnway);
        }else if(status.equals(COMPLETEWAY)){
            params.put("orderStatus",completeway);
            params.put("sellerStatus",sellerCompleteway);
        }else if(status.equals(CANCELWAY)){
            params.put("orderStatus",cancelway);
            params.put("sellerStatus",sellerCancelway);
        }else if(status.equals(RECHECKWAY)){
            params.put("orderStatus",recheckway);
            params.put("sellerStatus",sellerRecheckway);
        }else if(status.equals(MATCHUNDERWAY)){
            //撮合订单进行中
            params.put("orderStatus",new EnumOrder[]{EnumOrder.MakeMatch});
            params.put("sellerStatus",sellerMatchUnderway);
        }else if(status.equals(MATCHCOMPLETEWAY)){
            //撮合订单结束
            params.put("orderStatus",new EnumOrder[]{EnumOrder.Completed});
            params.put("sellerStatus",new EnumOrder[]{EnumOrder.Deleted});
        }

    }
    @RequestMapping(value = "/order/datacount",method =RequestMethod.POST)
    @ResponseBody
    @VerifyAuthentication(Trader = true, TraderAssistant = true, Finance = true, Operation = true, Admin = true)
    public Integer searchDataCount(@RequestParam Map<String,Object> params){
        addStatusParams(params);
        if(!StringUtils.isEmpty(params.get("pid"))){
            params.put("sellinfoid", Where.$like$((String) params.get("pid")));
        }
        return orderManageMapper.ExportOrder(params).size();
    }

    //export excel
    @RequestMapping(value = "/order/exportExcel",method =RequestMethod.GET)
    @VerifyAuthentication(Trader = true, TraderAssistant = true, Finance = true, Operation = true, Admin = true)
    public HttpEntity<byte[]> downloadManualsell(@RequestParam Map<String,Object> params) throws IOException, DocumentException {
            addStatusParams(params);
            if(!StringUtils.isEmpty(params.get("pid"))){
             params.put("sellinfoid", Where.$like$((String) params.get("pid")));
            }
            List<Map<String,Object>> orders=orderManageMapper.ExportOrder(params);
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet();
            HSSFRow row = sheet.createRow(0);
            CellStyle cellStyle = wb.createCellStyle();
            cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
            cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            sheet.setVerticallyCenter(true);
            sheet.setHorizontallyCenter(true);
            String[] excelHeader = {"序号 ","订单编号","产品编号","品种","港口","酒精度数","含糖量(%)","酒类指标2","酒类指标3(%)","酒类指标6(%)","全水分(%)","内水分(%)","数量(T)","价格(元)","合计(元)","交易员"};
            for (int i = 0; i < excelHeader.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(excelHeader[i]);
                cell.setCellStyle(cellStyle);
                sheet.autoSizeColumn(i,true);
            }
            for(int i=0;i<orders.size();i++){
                Map<String,Object> resultSet =  orders.get(i);
                sheet.autoSizeColumn(i,true);
                row = sheet.createRow(i+1);
                row.setRowStyle(cellStyle);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(String.valueOf(resultSet.get("orderid")));
                row.createCell(2).setCellValue(String.valueOf(resultSet.get("pid")));
                row.createCell(3).setCellValue(String.valueOf(resultSet.get("pname")));
                row.createCell(4).setCellValue(String.valueOf(resultSet.get("deliveryplace")));
                row.createCell(5).setCellValue(String.valueOf(resultSet.get("NCV")));
                row.createCell(6).setCellValue(String.valueOf(resultSet.get("RS")));
                row.createCell(7).setCellValue(String.valueOf(resultSet.get("ADV")));
                row.createCell(8).setCellValue(String.valueOf(resultSet.get("RV")));
                row.createCell(9).setCellValue(String.valueOf(resultSet.get("ASH")));
                row.createCell(10).setCellValue(String.valueOf(resultSet.get("TM")));
                row.createCell(11).setCellValue(String.valueOf(resultSet.get("IM")));
                row.createCell(12).setCellValue(String.valueOf(resultSet.get("amount")));
                row.createCell(13).setCellValue(String.valueOf(resultSet.get("price")));
                row.createCell(14).setCellValue(String.valueOf(resultSet.get("totalmoney")));
                row.createCell(15).setCellValue(String.valueOf(resultSet.get("dealername")));
            }
            File file = File.createTempFile(".xls",".xls");
            OutputStream out = new FileOutputStream(file);
            wb.write(out);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", URLEncoder.encode("订单数据", "UTF-8")+LocalDate.now()+".xls");
            return new HttpEntity<byte[]>(FileUtils.readFileToByteArray(file), headers);
    }
    //撮合订单 -- 待复审
    @RequestMapping("/order/recheck")
    @ResponseBody
    @VerifyAuthentication(Operation = true, Admin = true)
    public Object getAllMatchRecheckOrders(
            @RequestParam(value = "deliveryRegion", required = false, defaultValue = "0")int region,
            @RequestParam(value = "deliveryProvince", required = false, defaultValue = "0")int province,
            @RequestParam(value = "deliveryHarbour", required = false, defaultValue = "0")int harbour,
            @RequestParam(value = "orderid", required = false)String orderId,
            @RequestParam(value = "pid", required = false)String pid,
            int page){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("deliveryRegion", region);
            map.put("deliveryProvince", province);
            map.put("deliveryHarbour", harbour);
            map.put("orderid",orderId);
            map.put("pid",pid);
            map.put("regionList", areaportMapper.getAllArea());
            pid=StringUtils.isEmpty(pid)?null:Where.$like$(pid);
            map.put("provinceList", areaportMapper.getProcvincesOrPortsByParentid(region));
            map.put("harbourList", areaportMapper.getProcvincesOrPortsByParentid(province));
            map.put("orderRecheckList", orderManageMapper.findMatchOrderRecheck(region, province, harbour, orderId, pid, page, 10));
        return map;
    }


    //订单详细
    @RequestMapping("/order/detail")
    @ResponseBody
    @VerifyAuthentication(Trader = true, TraderAssistant = true, Finance = true, Operation = true, Admin = true)
    public Object showDetail(int id){
        Map<String, Object> map = new HashMap<String, Object>();
        boolean success = false;
        if(session.getAdmin() == null){
            map.put("error", "notlogin");
        } else {
            Order order = orderMapper.getOrderById(id);
            if (order == null) throw new NotFoundException();
            User buyer = userMapper.getUserById(order.getUserid());
            User seller = userMapper.getUserById(order.getSellerid());
            Company buyCompany = companyMapper.getCompanyByUserid(buyer.getId());
            map.put("order", order);
            map.put("buyer", buyer);
            map.put("buyCompany", buyCompany);
            map.put("seller", seller);
            success = true;
        }
        map.put("success", success);
        return map;
    }



    //商城订单 -- 申请完成
    @RequestMapping("/mall/applyComplete")
    @ResponseBody
    @VerifyAuthentication(Trader = true, Admin = true)
    public Object doApplyCompleteMallOrder(int id){
        Order order = orderMapper.getOrderById(id);
        if(order == null) throw new NotFoundException();
        SellInfo sellInfo = buyMapper.getSellInfoById(order.getSellinfoid());
        if(sellInfo == null) throw new NotFoundException();
        if(!order.getSellerstatus().equals(EnumOrder.Completed)) {
            orderMapper.setOrderSellerStatus(EnumOrder.Completed, id);
            orderMapper.addOrdersInfo(new OrdersInfo(order.getStatus(), session.getAdmin().getUsername(), session.getAdmin().getId(), order.getId(), order.getOrderid(), "申请完成"));
            return true;
        }
        return false;
    }

    //撮合订单 -- 申请完成
    @RequestMapping("/order/applyComplete")
    @ResponseBody
    @VerifyAuthentication(Trader = true, Admin = true)
    public Object doApplyCompleteMatchOrder(int id, int amount){
        Order order = orderMapper.getOrderById(id);
        if(order == null) throw new NotFoundException();
        SellInfo sellInfo = buyMapper.getSellInfoById(order.getSellinfoid());
        if(sellInfo == null) throw new NotFoundException();
        if(sellInfo.getAvailquantity() >= amount && !order.getSellerstatus().equals(EnumOrder.Completed)) {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            TransactionStatus status = transactionManager.getTransaction(def);
            buy.minusSellinfoQuantity(order.getAmount(), order.getSellinfoid());
            if(sellInfo.getAvailquantity() >= 0 && !order.getSellerstatus().equals(EnumOrder.Completed)){
                transactionManager.commit(status);
                orderMapper.setOrderSellerStatus(EnumOrder.Completed, id);
                orderMapper.addOrdersInfo(new OrdersInfo(order.getStatus(), session.getAdmin().getUsername(), session.getAdmin().getId(), order.getId(), order.getOrderid(), "申请完成"));
                return true;
            } else {
                transactionManager.rollback(status);
                return false;
            }
        }
        return false;
    }

    //商城订单 -- 提报退货申请
    @RequestMapping("/mall/applyReturn")
    @ResponseBody
    @VerifyAuthentication(Trader = true, Admin = true)
    public Object doApplyReturnOrder(int id){
        Order order = orderMapper.getOrderById(id);
        if(order == null) throw new NotFoundException();
        if(!order.getSellerstatus().equals(EnumOrder.ReturnCompleted)) {
            orderMapper.setOrderSellerStatus(EnumOrder.ReturnCompleted, id);
            orderMapper.addOrdersInfo(new OrdersInfo(order.getStatus(), session.getAdmin().getUsername(), session.getAdmin().getId(), order.getId(), order.getOrderid(), "提报退货申请"));
            return true;
        }
        return false;
    }

    //申请取消
    @RequestMapping("/order/applyCancel")
    @ResponseBody
    @VerifyAuthentication(Trader = true, Admin = true)
    public Object doApplyCancelOrder(int id){
        Order order = orderMapper.getOrderById(id);
        if(order == null) throw new NotFoundException();
        if(!order.getSellerstatus().equals(EnumOrder.Canceled)) {
            orderMapper.setOrderSellerStatus(EnumOrder.Canceled, id);
            orderMapper.addOrdersInfo(new OrdersInfo(EnumOrder.Canceled, session.getAdmin().getUsername(), session.getAdmin().getId(), order.getId(), order.getOrderid(), "申请取消订单"));
            return true;
        }
        return false;
    }

    //商城订单 -- 确认退货
    @RequestMapping("/mall/confirmReturn")
    @ResponseBody
    @VerifyAuthentication(Operation = true, Admin = true)
    public Object doConfirmReturnOrder(int id){
        Order order = orderMapper.getOrderById(id);
        if(order == null) throw new NotFoundException();
        SellInfo sellInfo = buyMapper.getSellInfoById(order.getSellinfoid());
        if(sellInfo == null) throw new NotFoundException();
        if(!order.getStatus().equals(EnumOrder.ReturnCompleted)) {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            TransactionStatus status = transactionManager.getTransaction(def);
            buy.plusSellinfoQuantity(order.getAmount(), order.getSellinfoid());
            if(!order.getStatus().equals(EnumOrder.ReturnCompleted)) {
                transactionManager.commit(status);
                orderMapper.setOrderStatus(EnumOrder.ReturnCompleted, id);
                orderMapper.addOrdersInfo(new OrdersInfo(EnumOrder.ReturnCompleted, session.getAdmin().getUsername(), session.getAdmin().getId(), order.getId(), order.getOrderid(), "确认退货完成"));
            } else{
                transactionManager.rollback(status);
            }
            return true;
        }
        return false;
    }

    //确认完成
    @RequestMapping("/mall/confirmComplete")
    @ResponseBody
    @VerifyAuthentication(Operation = true, Admin = true)
    public Object doConfirmCompleteOrder(int id){
        Order order = orderMapper.getOrderById(id);
        if(order == null) throw new NotFoundException();
        if(!order.getStatus().equals(EnumOrder.Completed)) {
            orderMapper.setOrderStatus(EnumOrder.Completed, id);
            orderMapper.addOrdersInfo(new OrdersInfo(EnumOrder.Completed, session.getAdmin().getUsername(), session.getAdmin().getId(), order.getId(), order.getOrderid(), "确认完成"));
            return true;
        }
        return false;
    }

    //撮合订单--确认取消
    @RequestMapping("/order/confirmCancel")
    @ResponseBody
    @VerifyAuthentication(Operation = true, Admin = true)
    public Object doConfirmCancel(int id){
        Order order = orderMapper.getOrderById(id);
        if(order == null) throw new NotFoundException();
        if(!order.getStatus().equals(EnumOrder.Canceled)){
            orderMapper.setOrderStatus(EnumOrder.Canceled, id);
            orderMapper.addOrdersInfo(new OrdersInfo(EnumOrder.Canceled, session.getAdmin().getUsername(), session.getAdmin().getId(), order.getId(), order.getOrderid(), "确认取消"));
            return true;
        }
        return false;
    }


    //删除订单
    @RequestMapping("/order/deleteOrder")
    @ResponseBody
    public Object doDeleteOrder(int id){
        Order order = orderMapper.getOrderById(id);
        if(order == null) throw new NotFoundException();
        if(!order.getSellerstatus().equals(EnumOrder.Deleted)) {
            orderMapper.setSellerStatusOrder(EnumOrder.Deleted, id);
            orderMapper.addOrdersInfo(new OrdersInfo(order.getStatus(), session.getAdmin().getUsername(), session.getAdmin().getId(), order.getId(), order.getOrderid(), "删除订单--卖家"));
            return true;
        }
        return false;
    }

    //复审未通过
    @RequestMapping("/order/verifyNotPass")
    @ResponseBody
    public Object doVerifyNotPass(int id){
        Order order = orderMapper.getOrderById(id);
        if(order == null) throw new NotFoundException();
        if(!order.getSellerstatus().equals(EnumOrder.VerifyNotPass)){
            orderMapper.setSellerStatusOrder(EnumOrder.VerifyNotPass, id);
            orderMapper.addOrdersInfo(new OrdersInfo(order.getStatus(), session.getAdmin().getUsername(), session.getAdmin().getId(), order.getId(), order.getOrderid(), "复审未通过"));
            return true;
        }
        return false;
    }


}
