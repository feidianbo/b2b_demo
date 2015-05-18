package demo.core.persistence;

import demo.core.domain.*;
import demo.core.util.Pager;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jack on 14/12/31.
 */
public interface OrderMapper {
    //订单
    String OrderSelectSQL1 =
            "<if test='deliveryregion!=null'> deliveryregion=#{deliveryregion}</if>" +
            "<if test='deliveryprovince!=null'> and deliveryprovince=#{deliveryprovince}</if>" +
            "<if test='deliveryplace!=null'> and deliveryplace=#{deliveryplace}</if>";
    String startWhere = "<where>";
    String endWhere = "</where>";
    final String limitOffset = " limit #{limit} offset #{offset}";
    final String OrderStatusSeven = "(status=#{status1} or status=#{status2} or status=#{status3} or status=#{status4} or status=#{status5} or status=#{status6} or status=#{status7})";
    final String OrderStatusEight = "(status=#{status1} or status=#{status2} or status=#{status3} or status=#{status4} or status=#{status5} or status=#{status6} or status=#{status7} or status=#{status8})";
    final String OrderStatusOne = "status=#{status}";
    final String OrderStatusTwo = "(status=#{status1} or status=#{status2})";
    final String OrderStatusThree = "(status=#{status1} or status=#{status2} or status=#{status3})";
    final String SellerStatusDelete = "sellerstatus!='Deleted'";
    final String SellerStatus = "sellerstatus=#{sellerstatus}";
    final String Userid = "userid=#{userid}";
    final String Sellerid = "sellerid=#{sellerid}";
    final String OrderType = "ordertype=#{ordertype}";
    final String OrderNumberSQL = "<if test='content!=null'> and orderid like #{content} </if>";
    final String OrderNumberByPidSQL = "<if test='pid!=null'> and pid like #{pid} </if>";

    //我的订单列表-已完成-买家-两种状态
    @Select("<script>" +
            "select * from orders" +
            startWhere + Userid + " and " + OrderStatusTwo + endWhere +
            "order by createtime desc limit #{limit} offset #{offset}" +
            "</script>")
    public List<Order> getTwoStatusOrdersBuy(@Param("userid") int userid,
                                            @Param("status1")EnumOrder status1,
                                            @Param("status2")EnumOrder status2,
                                            @Param("limit") int limit,
                                            @Param("offset") int offset);

    @Select("<script>" +
            "select count(id) from orders " +
            startWhere + Userid + " and " + OrderStatusTwo + endWhere +
            "</script>")
    public int countTwoStatusOrdersBuy(@Param("userid")int userid,
                                      @Param("status1")EnumOrder status1,
                                      @Param("status2")EnumOrder status2);

    //我的订单列表-买家-一种状态
    @Select("<script>" +
            "select * from orders" +
            startWhere + Userid + " and " + OrderStatusOne + endWhere +
            "order by createtime desc limit #{limit} offset #{offset}" +
            "</script>")
    public List<Order> getOneStatusOrdersBuy(@Param("userid") int userid,
                                            @Param("status")EnumOrder status,
                                            @Param("limit") int limit,
                                            @Param("offset") int offset);

    @Select("<script>" +
            "select count(id) from orders" +
            startWhere + Userid + " and " + OrderStatusOne + endWhere +
            "</script>")
    public int countOneStatusOrdersBuy(@Param("userid") int userid,
                                      @Param("status")EnumOrder status);

    //我的订单列表-进行中-买家订单-七种状态
    @Select("<script>" +
            "select * from orders" +
            startWhere + Userid + " and " + OrderStatusSeven + endWhere +
            " order by createtime desc limit #{limit} offset #{offset}" +
            "</script>")
    public List<Order> getSevenStatusOrdersBuy(@Param("userid") int userid,
                                              @Param("status1")EnumOrder status1,
                                              @Param("status2")EnumOrder status2,
                                              @Param("status3")EnumOrder status3,
                                              @Param("status4")EnumOrder status4,
                                              @Param("status5")EnumOrder status5,
                                              @Param("status6")EnumOrder status6,
                                              @Param("status7")EnumOrder status7,
                                              @Param("limit") int limit,
                                              @Param("offset") int offset);

    @Select("<script>" +
            "select count(id) from orders" +
            startWhere + Userid + " and " + OrderStatusSeven + endWhere +
            "</script>")
    public int countSevenStatusOrdersBuy(@Param("userid") int userid,
                                        @Param("status1")EnumOrder status1,
                                        @Param("status2")EnumOrder status2,
                                        @Param("status3")EnumOrder status3,
                                        @Param("status4")EnumOrder status4,
                                        @Param("status5")EnumOrder status5,
                                        @Param("status6")EnumOrder status6,
                                        @Param("status7")EnumOrder status7);

    @Select("<script>" +
            "select count(id) from orders" +
            startWhere +  "pid=#{pid} and " + OrderStatusEight + endWhere +
            "</script>")
    public int countEightStatusOrdersBuy(@Param("pid") String pid,
                                         @Param("status1")EnumOrder status1,
                                         @Param("status2")EnumOrder status2,
                                         @Param("status3")EnumOrder status3,
                                         @Param("status4")EnumOrder status4,
                                         @Param("status5")EnumOrder status5,
                                         @Param("status6")EnumOrder status6,
                                         @Param("status7")EnumOrder status7,
                                         @Param("status8")EnumOrder status8);

    //我的订单列表-进行中-卖家订单-一种状态
    @Select("<script>" +
            "select * from orders" +
            startWhere + Sellerid + " and " + OrderStatusOne + " and " + SellerStatusDelete + " and " + OrderType + endWhere +
            "order by createtime desc limit #{limit} offset #{offset}" +
            "</script>")
    public List<Order> getOneStatusOrdersSell(@Param("sellerid") int sellerid,
                                              @Param("status") EnumOrder status,
                                              @Param("ordertype") EnumOrder ordertype,
                                              @Param("limit") int limit,
                                              @Param("offset") int offset);

    @Select("<script>" +
            "select count(id) from orders" +
            startWhere + Sellerid + " and " + OrderStatusOne + " and " + SellerStatusDelete + " and " + OrderType + endWhere +
            "</script>")
    public int countOneStatusOrdersSell(@Param("sellerid")int sellerid,
                                        @Param("status")EnumOrder status,
                                        @Param("ordertype")EnumOrder ordertype);

    //添加订单-自营
    @Insert("insert into orders(orderid, pid, sellinfoid, status, price, amount, createtime, deliverytime1, " +
            "deliverytime2, seller, deliveryregion, deliveryprovince, deliveryplace, otherharbour, deliverymode," +
            "totalmoney, waitmoney, linkman, linkmanphone, userid, ordertype, paytype, isfutures, contractno, " +
            "sellerid, dealerid, dealername, dealerphone,regionId,provinceId,portId)  values(dateseq_next_value('ZY'), #{pid}, #{sellinfoid}, #{status}, #{price}, " +
            "#{amount}, #{createtime}, #{deliverytime1}, #{deliverytime2}, #{seller}, #{deliveryregion}, " +
            "#{deliveryprovince}, #{deliveryplace}, #{otherharbour}, #{deliverymode}, #{totalmoney}, " +
            "#{waitmoney}, #{linkman}, #{linkmanphone}, #{userid}, #{ordertype}, #{paytype}, #{isfutures}, " +
            "dateseq_next_value('HT'), #{sellerid}, #{dealerid}, #{dealername}, #{dealerphone},#{regionId},#{provinceId},#{portId})")
    @Options(useGeneratedKeys=true)
    public void addZYOrder(Order order);

    //添加订单-委托
    @Insert("insert into orders(orderid, pid, sellinfoid, status, price, amount, createtime, deliverytime1, " +
            "deliverytime2, seller, deliveryregion, deliveryprovince, deliveryplace, otherharbour, " +
            "deliverymode, totalmoney, linkman, linkmanphone, userid, ordertype, sellerid, dealerid," +
            "dealername, dealerphone,regionId,provinceId,portId) " +
            "values(dateseq_next_value('WT'), #{pid}, #{sellinfoid}, #{status}, #{price}, #{amount}, " +
            "#{createtime}, #{deliverytime1}, #{deliverytime2}, #{seller}, #{deliveryregion}, " +
            "#{deliveryprovince}, #{deliveryplace}, #{otherharbour}, #{deliverymode}, #{totalmoney}, " +
            "#{linkman}, #{linkmanphone}, #{userid}, #{ordertype}, #{sellerid}, #{dealerid}, #{dealername}, #{dealerphone},#{regionId},#{provinceId},#{portId})")
    @Options(useGeneratedKeys=true)
    public void addWTOrder(Order order);

    //设置Order状态
    @Update("update orders set status=#{status} where id=#{id}")
    public void setOrderStatus(@Param("status")EnumOrder status,
                               @Param("id")int id);

    //设置卖家状态
    @Update("update orders set sellerstatus=#{sellerstatus} where id=#{id}")
    public void setOrderSellerStatus(@Param("sellerstatus")EnumOrder sellerstatus,
                                     @Param("id")int id);


    //卖家删除订单
    @Update("update orders set sellerstatus=#{sellerstatus} where id=#{id}")
    public void setSellerStatusOrder(@Param("sellerstatus")EnumOrder sellerstatus,
                                     @Param("id")int id);

    //根据id查询Order
    @Select("select * from orders where id=#{id}")
    public Order getOrderById(int id);

    @Select("select count(*) from orders where status=#{status}")
    public int countAllOrderByStatus(EnumOrder status);

    @Select("<script>select * from orders where status=#{status}" + limitOffset + "</script>")
    public List<Order> listAllOrderByStatus(@Param("status") EnumOrder status,
                                            @Param("limit") int limit,
                                            @Param("offset") int offset);

    public default Pager<Order> pageAllOrderByStatus(EnumOrder status, int page, int pagesize) {
        return Pager.config(this.countAllOrderByStatus(status), (int limit, int offset) ->
                this.listAllOrderByStatus(status, limit, offset))
                .page(page, pagesize);
    }

    @Select("<script>" +
            "select count(*) from orders where status=#{status} " +
            OrderNumberSQL +
            OrderNumberByPidSQL +
            "</script>")
    public int countOrderBySelect(@Param("status")EnumOrder status,
                                  @Param("content")String content,
                                  @Param("pid")String pid);
    @Select("<script>" +
            "select * from orders where status=#{status}" +
            OrderNumberSQL +
            OrderNumberByPidSQL +
            " order by pid, createtime desc " + limitOffset + "</script>")
    public List<Order> listOrderBySelect(@Param("status") EnumOrder status,
                                         @Param("content") String content,
                                         @Param("pid") String pid,
                                         @Param("limit") int limit,
                                         @Param("offset") int offset);
    public default Pager<Order> pageAllOrderBySelect(EnumOrder status, String content, String pid, int page, int pagesize){
        String searchcontent = "%" + content + "%";
        String searchpid = "%" + pid + "%";
        return Pager.config(this.countOrderBySelect(status, searchcontent, searchpid), (int limit, int offset) ->
                this.listOrderBySelect(status, searchcontent, searchpid, limit, offset)).page(page, pagesize);
    }

    //添加审核信息
    @Insert("insert into orderverify(status, applytime, orderid, userid) values(#{status}, #{applytime}, #{orderid}, #{userid})")
    public void addOrderVerify(OrderVerify orderVerify);

    @Update("update orderverify set status=#{status}, verifyman=#{verifyman}, verifytime=#{verifytime}, remarks=#{remarks} where id=#{id}")
    public void verifyOrderVerifyPaidCredit(@Param("status") EnumOrder status, @Param("verifyman") String verifyman, @Param("verifytime") LocalDateTime verifytime, @Param("remarks") String remarks, @Param("id") int id);

    @Select("select * from orders where status=#{status}")
    public List<Order> getOrderListByStatus(EnumOrder status);

    @Insert("insert into ordersinfo(status, operateman, operatemanid, createtime, oid, orderid, remarks) values(#{status}, #{operateman}, #{operatemanid}, now(), #{oid}, #{orderid}, #{remarks})")
    public void addOrdersInfo(OrdersInfo ordersInfo);

    @Update("update orders set paidmoney=#{paidmoney}, waitmoney=#{waitmoney}, savemoney=#{savemoney} where id=#{id}")
    public void setOrderVerifyMoney(@Param("paidmoney") BigDecimal paidmoney,
                                    @Param("waitmoney") BigDecimal waitmoney,
                                    @Param("savemoney") BigDecimal savemoney,
                                    @Param("id") int id);

    //admin审核订单支付凭证
    @Update("update orders set status=#{status}, remarks=#{remarks} where id=#{id}")
    public void verifyOrderPaidCredit(@Param("status") EnumOrder status,
                                      @Param("remarks") String remarks,
                                      @Param("id") int id);

    //退货单--处理
    @Insert("insert into orderreturn(laststatus, orderid, oid, userid, username, iscanceled, createtime) value(#{laststatus}, #{orderid}, #{oid}, #{userid}, #{username}, #{iscanceled}, now())")
    public void addOrderReturn(OrderReturn orderReturn);

    //查询OrderReturn
    @Select("select * from orderreturn where orderid=#{orderid} and iscanceled=0")
    public OrderReturn getOrderReturnByOrderId(int orderid);

    //设置OrderReturn 是否已经撤销退货
    @Update("update orderreturn set iscanceled=1 where id=#{id} and iscanceled='0'")
    public void setOrderReturnIsCanceled(int id);

    //我的客户---订单
    final String listOrderSql =
                     "<where><if test='status!=null'>and status = #{status}</if>" +
                    "<if test='paytype!=null'> and paytype=#{paytype}</if>" +
                    "<if test='user.isAdmin!=true'> and dealerid=#{user.jobnum}</if></where>";

    //我的客户---订单
    @Select("<script>select count(1) from orders "+ listOrderSql + " </script>")
    public int listAllOrdersCount(@Param("status")EnumOrder status,@Param("paytype")EnumOrder paytype,@Param("user")Admin currentUser);


    @Select("<script> select id,orderid,createtime,amount,totalmoney,contractno,seller,paidmoney,waitmoney,status,dealername from orders "+listOrderSql +" order by id limit #{limit} offset #{offset} </script>")
    public List<Order> showAllOrders(@Param("limit")int limit,@Param("offset")int offset,@Param("status")EnumOrder status,@Param("paytype")EnumOrder paytype,@Param("user")Admin currentUser);

    public default Pager<Order> getAllOrderList(int page, int pagesize,EnumOrder status,EnumOrder paytype,Admin currentUser ){
      return Pager.config(this.listAllOrdersCount(status,paytype,currentUser), (int limit, int offset) -> this.showAllOrders(limit,offset,status,paytype,currentUser))
              .page(page, pagesize);
    }

    //最近成交记录 30条
    @Select("select * from orders where status = 'Completed' order by createtime desc limit 30")
    public List<Order> listRecentTransactionOrders();

    //订单是否已经修改
    @Update("update orders set ischange=1 where id=#{id}")
    public void setOrderIsChange(int id);

}
