package demo.core.persistence;

import demo.core.domain.EnumOrder;
import demo.core.domain.Order;
import demo.core.util.Pager;
import demo.ext.mybatis.Where;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by jack on 15/1/22.
 */
public interface OrderManageMapper {
    String OrderSelectSQL1 =
            "<if test='deliveryregion!=null'> deliveryregion=#{deliveryregion}</if>" +
            "<if test='deliveryprovince!=null'> and deliveryprovince=#{deliveryprovince}</if>" +
            "<if test='deliveryplace!=null'> and deliveryplace=#{deliveryplace}</if>";
    String startWhere = "<where>";
    String endWhere = "</where>";
    final String limitOffset = " limit #{limit} offset #{offset}";
    final String OrderByCreatetimeDesc = " order by createtime desc";
    final String OrderStatusSeven = "(status=#{status1} or status=#{status2} or status=#{status3} or status=#{status4} or status=#{status5} or status=#{status6} or status=#{status7})";
    final String OrderStatusSix = "(status=#{status1} or status=#{status2} or status=#{status3} or status=#{status4} or status=#{status5} or status=#{status6})";
    final String OrderStatusOne = "status=#{status}";
    final String OrderStatusTwo = "(status=#{status1} or status=#{status2})";
    final String OrderStatusThree = "(status=#{status1} or status=#{status2} or status=#{status3})";
    final String SellerStatusNotEquals = "<if test='sellerstatus!=null'> and sellerstatus!=#{sellerstatus}</if>";
    final String SellerStatusNotEqualsTwo =
            "<if test='sellerstatus1!=null'> and sellerstatus!=#{sellerstatus1}</if>" +
            "<if test='sellerstatus2!=null'> and sellerstatus!=#{sellerstatus2}</if>";
    final String SellerStatusNotEqualsThree =
            "<if test='sellerstatus1!=null'> and sellerstatus!=#{sellerstatus1}</if>" +
            "<if test='sellerstatus2!=null'> and sellerstatus!=#{sellerstatus2}</if>" +
            "<if test='sellerstatus3!=null'> and sellerstatus!=#{sellerstatus3}</if>";
    final String OrderType = "<if test='ordertype!=null'> and ordertype=#{ordertype}</if>";
    final String SellerStatusTwo = "(sellerstatus=#{sellerstatus1} or sellerstatus=#{sellerstatus2})";
    final String SellerStatusOne = "sellerstatus=#{sellerstatus}";


    @Select("<script>select  count(1) from orders " +
            "<where>    <if test='sellinfoid!=null and sellinfoid!=\"\"'> and pid like #{sellinfoid}</if> " +
            "<if test='orderid!=null and orderid!=\"\"'> and orderid=#{orderid}</if>" +
            "<if test='deliveryRegion!=\"0\"'> and regionId=${deliveryRegion}</if>" +
            "<if test='deliveryProvince!=\"0\"'> and provinceId=${deliveryProvince}</if>" +
            "<if test='deliveryHarbour!=\"0\"'> and portId=${deliveryHarbour}</if>" +
            "<if test='startDate!=null and  endDate!=null'> and DATE_FORMAT(createtime,'%Y-%m-%d') between  #{startDate} and  #{endDate}</if> " +
            "<if test='startDate!=null and  endDate==null'> and DATE_FORMAT(createtime,'%Y-%m-%d') between  #{startDate} and  DATE_FORMAT(now(),'%Y-%m-%d')</if>"+
            "<if test='orderType!=null'> and orderType=#{orderType}</if>" +
            "<if test='orderStatus!=null'> and status in <foreach collection='orderStatus' index='index' item='item' open='(' separator=',' close=')'>#{item}</foreach></if>"+
            "<if test='sellerStatus!=null'><foreach collection='sellerStatus' index='i' item='item'> and sellerstatus !=#{item} </foreach></if>"+
            "</where></script>")
    public int countOrderByStatus(Map<String,Object> params);


    @Select("<script>select  id,orderid,dealername,pid,status,deliveryprovince,deliveryplace,createtime,sellerstatus  from orders " +
            "<where>  <if test='params.sellinfoid!=null and params.sellinfoid!=\"\"'> and pid like #{params.sellinfoid}</if>" +
            "<if test='params.orderid!=null and params.orderid!=\"\"'> and orderid=#{params.orderid}</if>" +
            "<if test='params.deliveryRegion!=\"0\"'> and regionId=${params.deliveryRegion}</if>" +
            "<if test='params.deliveryProvince!=\"0\"'> and provinceId=${params.deliveryProvince}</if>" +
            "<if test='params.deliveryHarbour!=\"0\"'> and portId=${params.deliveryHarbour}</if>" +
            "<if test='params.startDate!=null and  params.endDate!=null'> and DATE_FORMAT(createtime,'%Y-%m-%d') between  #{params.startDate} and  #{params.endDate}</if> " +
            "<if test='params.startDate!=null and params.endDate==null '> and DATE_FORMAT(createtime,'%Y-%m-%d') between  #{params.startDate} and  DATE_FORMAT(now(),'%Y-%m-%d')</if>"+
            "<if test='params.orderType!=null'> and orderType=#{params.orderType}</if>" +
            "<if test='params.orderStatus!=null'> and status in <foreach collection='params.orderStatus' index='index' item='item' open='(' separator=',' close=')'>#{item}</foreach></if>"+
            "<if test='params.sellerStatus!=null'><foreach collection='params.sellerStatus' index='i' item='item'> and sellerstatus !=#{item} </foreach></if>"+
            "</where> order by pid, createtime  limit #{limit} offset #{offset}</script>")
    public List<Order> findOrder(@Param("params")Map<String,Object> params,@Param("limit") int page,@Param("offset") int offset);


    @Select("<script>select o.orderid,o.pid,s.pname,o.deliveryplace,s.NCV,s.RS,s.ADV,s.RV,s.ASH,s.TM,s.IM,o.amount,o.price,o.totalmoney,o.dealername from orders o inner join sellinfo s on o.sellinfoid=s.id" +
            "<where>  <if test='params.sellinfoid!=null and params.sellinfoid!=\"\"'> and o.pid like #{params.sellinfoid}</if> " +
            " <if test='params.orderid!=null and params.orderid!=\"\"'> and o.orderid=#{params.orderid}</if>" +
            "<if test='params.deliveryRegion!=\"0\"'> and o.regionId=${params.deliveryRegion}</if>" +
            "<if test='params.deliveryProvince!=\"0\"'> and o.provinceId=${params.deliveryProvince}</if>" +
            "<if test='params.deliveryHarbour!=\"0\"'> and o.portId=${params.deliveryHarbour}</if>" +
            "<if test='params.startDate!=null and  params.endDate!=null'> and DATE_FORMAT(o.createtime,'%Y-%m-%d') between  #{params.startDate} and  #{params.endDate}</if> " +
            "<if test='params.startDate!=null and params.endDate==null '> and DATE_FORMAT(o.createtime,'%Y-%m-%d') between  #{params.startDate} and  DATE_FORMAT(now(),'%Y-%m-%d')</if>"+
            "<if test='params.orderType!=null'> and o.orderType=#{params.orderType}</if>" +
            "<if test='params.orderStatus!=null'> and o.status in <foreach collection='params.orderStatus' index='index' item='item' open='(' separator=',' close=')'>#{item}</foreach></if>"+
            "<if test='params.sellerStatus!=null'><foreach collection='params.sellerStatus' index='i' item='item'> and o.sellerstatus !=#{item} </foreach></if>"+
            "</where>  order by o.pid, o.createtime  </script>")
    public List<Map<String,Object>> ExportOrder(@Param("params") Map<String,Object> parmas);


    public default Pager<Order> findOrderByStatus(Map<String,Object> params, int page, int pagesize){
        if(!StringUtils.isEmpty(params.get("pid"))){
            params.put("sellinfoid", Where.$like$((String)params.get("pid")));
        }
        return Pager.config(this.countOrderByStatus(params), (int limit, int offset) -> this.findOrder(params, limit, offset))
                .page(page, pagesize);
    }

    //查找撮合订单-待复审
    @Select("<script>select count(1) from orders WHERE status='MakeMatch' and(sellerstatus='Completed' or sellerstatus='Canceled') and ordertype='OtherOrder'" +
            "<if test='regionId!=0'> and regionId=#{regionId}</if>" +
            "<if test='provinceId!=0'> and provinceId=#{provinceId}</if>" +
            "<if test='portId!=0'> and portId=#{portId}</if>" +
            "<if test='orderId!=null and orderId!=\"\"'> and orderid=#{orderId}</if>" +
            "<if test='pid!=null and pid!=\"\"'> and pid like #{pid}</if>  </script>")
    public int countMatchOrderRecheck(@Param("regionId")int regionId,@Param("provinceId")int province,@Param("portId")int portId,@Param("orderId")String orderId,@Param("pid")String pid);


    @Select("<script>select * from orders WHERE status='MakeMatch' and(sellerstatus='Completed' or sellerstatus='Canceled') and ordertype='OtherOrder'" +
            "<if test='regionId!=0'> and regionId=#{regionId}</if>" +
            "<if test='provinceId!=0'> and provinceId=#{provinceId}</if>" +
            "<if test='portId!=0'> and portId=#{portId}</if>" +
            "<if test='orderId!=null and orderId!=\"\"'> and orderid=#{orderId}</if>" +
            "<if test='pid!=null and pid!=\"\"'> and pid like #{pid}</if> order by pid,createtime  limit #{limit} offset #{offset} </script>")
    public List<Order> findMatchRecheck(@Param("regionId")int regionId,@Param("provinceId")int province,@Param("portId")int portId,@Param("orderId")String orderId,@Param("pid")String pid,@Param("limit")int page, @Param("offset")int pagesize);



    public default Pager<Order> findMatchOrderRecheck(int regionId,int provinceId,int portId,String orderId,String pid, int page, int pagesize){
        return Pager.config(this.countMatchOrderRecheck(regionId,provinceId,portId,orderId,pid), (int limit, int offset) -> this.findMatchRecheck(regionId, regionId, portId, orderId, pid, limit, offset))
                .page(page, pagesize);
    }

    //订单列表-7种状态
    @Select("<script>" +
            "select count(*) from orders " +
            startWhere + OrderStatusSeven + OrderType + SellerStatusNotEquals + endWhere +
            "</script>")
    public int countSevenStatusOrders(@Param("status1")EnumOrder status1,
                                      @Param("status2")EnumOrder status2,
                                      @Param("status3")EnumOrder status3,
                                      @Param("status4")EnumOrder status4,
                                      @Param("status5")EnumOrder status5,
                                      @Param("status6")EnumOrder status6,
                                      @Param("status7")EnumOrder status7,
                                      @Param("ordertype")EnumOrder ordertype,
                                      @Param("sellerstatus")EnumOrder sellerstatus);
    @Select("<script>" +
            "select * from orders " +
            startWhere + OrderStatusSeven + OrderType + SellerStatusNotEquals + endWhere + OrderByCreatetimeDesc + limitOffset +
            "</script>")
    public List<Order> listSevenStatusOrders(@Param("status1")EnumOrder status1,
                                             @Param("status2")EnumOrder status2,
                                             @Param("status3")EnumOrder status3,
                                             @Param("status4")EnumOrder status4,
                                             @Param("status5")EnumOrder status5,
                                             @Param("status6")EnumOrder status6,
                                             @Param("status7")EnumOrder status7,
                                             @Param("ordertype")EnumOrder ordertype,
                                             @Param("sellerstatus")EnumOrder sellerstatus,
                                             @Param("limit") int limit,
                                             @Param("offset") int offset);

    public default Pager<Order> getSevenStatusOrders(EnumOrder status1, EnumOrder status2, EnumOrder status3, EnumOrder status4, EnumOrder status5, EnumOrder status6, EnumOrder status7, EnumOrder ordertype, EnumOrder sellerstatus, int page, int pagesize){
        return Pager.config(this.countSevenStatusOrders(status1, status2, status3, status4, status5, status6, status7, ordertype, sellerstatus), (int limit, int offset) -> this.listSevenStatusOrders(status1, status2, status3, status4, status5, status6, status7, ordertype, sellerstatus, limit, offset))
                .page(page, pagesize);
    }

    //订单列表-6种状态
    @Select("<script>" +
            "select count(*) from orders " +
            startWhere + OrderStatusSix + OrderType + SellerStatusNotEqualsTwo + endWhere +
            "</script>")
    public int countSixStatusOrders(@Param("status1")EnumOrder status1,
                                    @Param("status2")EnumOrder status2,
                                    @Param("status3")EnumOrder status3,
                                    @Param("status4")EnumOrder status4,
                                    @Param("status5")EnumOrder status5,
                                    @Param("status6")EnumOrder status6,
                                    @Param("ordertype")EnumOrder ordertype,
                                    @Param("sellerstatus1")EnumOrder sellerstatus1,
                                    @Param("sellerstatus2")EnumOrder sellerstatus2,
                                    @Param("startDate") LocalDate startDate,
                                    @Param("endDate") LocalDate endDate);
    @Select("<script>" +
            "select * from orders " +
            startWhere + OrderStatusSix + OrderType + SellerStatusNotEqualsTwo  + endWhere + OrderByCreatetimeDesc + limitOffset +
            "</script>")
    public List<Order> listSixStatusOrders(@Param("status1")EnumOrder status1,
                                           @Param("status2")EnumOrder status2,
                                           @Param("status3")EnumOrder status3,
                                           @Param("status4")EnumOrder status4,
                                           @Param("status5")EnumOrder status5,
                                           @Param("status6")EnumOrder status6,
                                           @Param("ordertype")EnumOrder ordertype,
                                           @Param("sellerstatus1")EnumOrder sellerstatus1,
                                           @Param("sellerstatus2")EnumOrder sellerstatus2,
                                           @Param("limit") int limit,
                                           @Param("offset") int offset,
                                           @Param("startDate") LocalDate startDate,
                                           @Param("endDate") LocalDate endDate);
    public default Pager<Order> getSixStatusOrders(EnumOrder status1, EnumOrder status2, EnumOrder status3, EnumOrder status4, EnumOrder status5, EnumOrder status6, EnumOrder ordertype, EnumOrder sellerstatus1, EnumOrder sellerstatus2, int page, int pagesize,LocalDate startDate,LocalDate endDate){
        return Pager.config(this.countSixStatusOrders(status1, status2, status3, status4, status5, status6, ordertype, sellerstatus1, sellerstatus2, startDate, endDate), (int limit, int offset) -> this.listSixStatusOrders(status1, status2, status3, status4, status5, status6, ordertype, sellerstatus1, sellerstatus2, limit, offset, startDate, endDate))
                .page(page, pagesize);
    }

    //订单列表-一种状态
    @Select("<script>" +
            "select count(*) from orders " +
            startWhere + OrderStatusOne + OrderType + SellerStatusNotEqualsTwo + endWhere +
            "</script>")
    public int countOneStatusOrders(@Param("status")EnumOrder status,
                                    @Param("ordertype")EnumOrder ordertype,
                                    @Param("sellerstatus1")EnumOrder sellerstatus1,
                                    @Param("sellerstatus2")EnumOrder sellerstatus2);
    @Select("<script>" +
            "select * from orders " +
            startWhere + OrderStatusOne + OrderType + SellerStatusNotEqualsTwo + endWhere + OrderByCreatetimeDesc + limitOffset +
            "</script>")
    public List<Order> listOneStatusOrders(@Param("status")EnumOrder status,
                                           @Param("ordertype")EnumOrder ordertype,
                                           @Param("sellerstatus1")EnumOrder sellerstatus1,
                                           @Param("sellerstatus2")EnumOrder sellerstatus2,
                                           @Param("limit") int limit,
                                           @Param("offset") int offset);
    public default Pager<Order> getOneStatusOrders(EnumOrder status, EnumOrder ordertype, EnumOrder sellerstatus1, EnumOrder sellerstatus2, int page, int pagesize){
        return Pager.config(this.countOneStatusOrders(status, ordertype, sellerstatus1, sellerstatus2), (int limit, int offset) -> this.listOneStatusOrders(status, ordertype, sellerstatus1, sellerstatus2, limit, offset))
                .page(page, pagesize);
    }


    //撮合订单列表-一种状态-3种卖家状态
    @Select("<script>" +
            "select count(*) from orders " +
            startWhere + OrderStatusOne + OrderType + SellerStatusNotEqualsThree + endWhere +
            "</script>")
    public int countOneStatusMatchOrders(@Param("status")EnumOrder status,
                                         @Param("ordertype")EnumOrder ordertype,
                                         @Param("sellerstatus1")EnumOrder sellerstatus1,
                                         @Param("sellerstatus2")EnumOrder sellerstatus2,
                                         @Param("sellerstatus3")EnumOrder sellerstatus3);
    @Select("<script>" +
            "select * from orders " +
            startWhere + OrderStatusOne + OrderType + SellerStatusNotEqualsThree + endWhere + OrderByCreatetimeDesc + limitOffset +
            "</script>")
    public List<Order> listOneStatusMatchOrders(@Param("status")EnumOrder status,
                                                  @Param("ordertype")EnumOrder ordertype,
                                                  @Param("sellerstatus1")EnumOrder sellerstatus1,
                                                  @Param("sellerstatus2")EnumOrder sellerstatus2,
                                                  @Param("sellerstatus3")EnumOrder sellerstatus3,
                                                  @Param("limit") int limit,
                                                  @Param("offset") int offset);
    public default Pager<Order> getOneStatusMatchOrders(EnumOrder status, EnumOrder ordertype, EnumOrder sellerstatus1, EnumOrder sellerstatus2, EnumOrder sellerstatus3, int page, int pagesize){
        return Pager.config(this.countOneStatusMatchOrders(status, ordertype, sellerstatus1, sellerstatus2, sellerstatus3), (int limit, int offset) -> this.listOneStatusMatchOrders(status, ordertype, sellerstatus1, sellerstatus2, sellerstatus3, limit, offset))
                .page(page, pagesize);
    }

    //订单列表-两种状态
    @Select("<script>" +
            "select count(*) from orders " +
            startWhere + OrderStatusTwo + OrderType + SellerStatusNotEquals + endWhere +
            "</script>")
    public int countTwoStatusOrders(@Param("status1")EnumOrder status1,
                                    @Param("status2")EnumOrder status2,
                                    @Param("ordertype")EnumOrder ordertype,
                                    @Param("sellerstatus")EnumOrder sellerstatus);
    @Select("<script>" +
            "select * from orders " +
            startWhere + OrderStatusTwo + OrderType + SellerStatusNotEquals + endWhere + OrderByCreatetimeDesc + limitOffset +
            "</script>")
    public List<Order> listTwoStatusOrders(@Param("status1")EnumOrder status1,
                                           @Param("status2")EnumOrder status2,
                                           @Param("ordertype")EnumOrder ordertype,
                                           @Param("sellerstatus")EnumOrder sellerstatus,
                                           @Param("limit") int limit,
                                           @Param("offset") int offset);
    public default Pager<Order> getTwoStatusOrders(EnumOrder status1, EnumOrder status2, EnumOrder ordertype, EnumOrder sellerstatus, int page, int pagesize){
        return Pager.config(this.countTwoStatusOrders(status1, status2, ordertype, sellerstatus), (int limit, int offset) -> this.listTwoStatusOrders(status1, status2, ordertype, sellerstatus, limit, offset))
                .page(page, pagesize);
    }

    //订单列表-三种状态
    @Select("<script>" +
            "select count(*) from orders " +
            startWhere + OrderStatusThree + OrderType + SellerStatusNotEquals + endWhere +
            "</script>")
    public int countThreeStatusOrders(@Param("status1")EnumOrder status1,
                                      @Param("status2")EnumOrder status2,
                                      @Param("status3")EnumOrder status3,
                                      @Param("ordertype")EnumOrder ordertype,
                                      @Param("sellerstatus")EnumOrder sellerstatus);
    @Select("<script>" +
            "select * from orders " +
            startWhere + OrderStatusThree + OrderType + SellerStatusNotEquals + endWhere + OrderByCreatetimeDesc + limitOffset +
            "</script>")
    public List<Order> listThreeStatusOrders(@Param("status1")EnumOrder status1,
                                             @Param("status2")EnumOrder status2,
                                             @Param("status3")EnumOrder status3,
                                             @Param("ordertype")EnumOrder ordertype,
                                             @Param("sellerstatus")EnumOrder sellerstatus,
                                             @Param("limit") int limit,
                                             @Param("offset") int offset);
    public default Pager<Order> getThreeStatusOrders(EnumOrder status1, EnumOrder status2, EnumOrder status3, EnumOrder ordertype, EnumOrder sellerstatus, int page, int pagesize){
        return Pager.config(this.countThreeStatusOrders(status1, status2, status3, ordertype, sellerstatus), (int limit, int offset) -> this.listThreeStatusOrders(status1, status2, status3, ordertype, sellerstatus, limit, offset))
                .page(page, pagesize);
    }

    //查询订单-进行中-7种状态
    @Select("<script> " +
            "select count(*) from orders " +
            startWhere + OrderSelectSQL1 +
            "and " + OrderStatusSeven + OrderType + SellerStatusNotEquals + endWhere +
            "</script>")
    public int countSevenStatusOrdersBySelect(@Param("deliveryregion")String deliveryregion,
                                              @Param("deliveryprovince")String deliveryprovince,
                                              @Param("deliveryplace")String deliveryplace,
                                              @Param("status1")EnumOrder status1,
                                              @Param("status2")EnumOrder status2,
                                              @Param("status3")EnumOrder status3,
                                              @Param("status4")EnumOrder status4,
                                              @Param("status5")EnumOrder status5,
                                              @Param("status6")EnumOrder status6,
                                              @Param("status7")EnumOrder status7,
                                              @Param("ordertype")EnumOrder ordertype,
                                              @Param("sellerstatus")EnumOrder sellerstatus);
    @Select("<script>" +
            "select * from orders" +
            startWhere + OrderSelectSQL1 +
            "and " + OrderStatusSeven + OrderType + SellerStatusNotEquals + endWhere + OrderByCreatetimeDesc + limitOffset +
            "</script>")
    public List<Order> listSevenStatusOrdersBySelect(@Param("deliveryregion")String deliveryregion,
                                                     @Param("deliveryprovince")String deliveryprovince,
                                                     @Param("deliveryplace")String deliveryplace,
                                                     @Param("status1")EnumOrder status1,
                                                     @Param("status2")EnumOrder status2,
                                                     @Param("status3")EnumOrder status3,
                                                     @Param("status4")EnumOrder status4,
                                                     @Param("status5")EnumOrder status5,
                                                     @Param("status6")EnumOrder status6,
                                                     @Param("status7")EnumOrder status7,
                                                     @Param("ordertype")EnumOrder ordertype,
                                                     @Param("sellerstatus")EnumOrder sellerstatus,
                                                     @Param("limit")int limit,
                                                     @Param("offset")int offset);
    public default Pager<Order> getSevenStatusOrdersBySelect(String deliveryregion, String deliveryprovince, String deliveryplace, EnumOrder status1, EnumOrder status2, EnumOrder status3, EnumOrder status4, EnumOrder status5, EnumOrder status6, EnumOrder status7, EnumOrder ordertype, EnumOrder sellerstatus, int page, int pagesize){
        return Pager.config(this.countSevenStatusOrdersBySelect(deliveryregion, deliveryprovince, deliveryplace, status1, status2, status3, status4, status5, status6, status7, ordertype, sellerstatus), (int limit, int offset) -> this.listSevenStatusOrdersBySelect(deliveryregion, deliveryprovince, deliveryplace, status1, status2, status3, status4, status5, status6, status7, ordertype, sellerstatus, limit, offset))
                .page(page, pagesize);
    }

    //查询订单-进行中-6种状态
    @Select("<script> " +
            "select count(*) from orders " +
            startWhere + OrderSelectSQL1 +
            "and " + OrderStatusSix + OrderType + SellerStatusNotEqualsTwo + endWhere +
            "</script>")
    public int countSixStatusOrdersBySelect(@Param("deliveryregion")String deliveryregion,
                                            @Param("deliveryprovince")String deliveryprovince,
                                            @Param("deliveryplace")String deliveryplace,
                                            @Param("status1")EnumOrder status1,
                                            @Param("status2")EnumOrder status2,
                                            @Param("status3")EnumOrder status3,
                                            @Param("status4")EnumOrder status4,
                                            @Param("status5")EnumOrder status5,
                                            @Param("status6")EnumOrder status6,
                                            @Param("ordertype")EnumOrder ordertype,
                                            @Param("sellerstatus1")EnumOrder sellerstatus1,
                                            @Param("sellerstatus2")EnumOrder sellerstatus2);
    @Select("<script>" +
            "select * from orders" +
            startWhere + OrderSelectSQL1 +
            "and " + OrderStatusSix + OrderType + SellerStatusNotEqualsTwo + endWhere + OrderByCreatetimeDesc + limitOffset +
            "</script>")
    public List<Order> listSixStatusOrdersBySelect(@Param("deliveryregion")String deliveryregion,
                                                   @Param("deliveryprovince")String deliveryprovince,
                                                   @Param("deliveryplace")String deliveryplace,
                                                   @Param("status1")EnumOrder status1,
                                                   @Param("status2")EnumOrder status2,
                                                   @Param("status3")EnumOrder status3,
                                                   @Param("status4")EnumOrder status4,
                                                   @Param("status5")EnumOrder status5,
                                                   @Param("status6")EnumOrder status6,
                                                   @Param("ordertype")EnumOrder ordertype,
                                                   @Param("sellerstatus1")EnumOrder sellerstatus1,
                                                   @Param("sellerstatus2")EnumOrder sellerstatus2,
                                                   @Param("limit")int limit,
                                                   @Param("offset")int offset);
    public default Pager<Order> getSixStatusOrdersBySelect(String deliveryregion, String deliveryprovince, String deliveryplace, EnumOrder status1, EnumOrder status2, EnumOrder status3, EnumOrder status4, EnumOrder status5, EnumOrder status6, EnumOrder ordertype, EnumOrder sellerstatus1, EnumOrder sellerstatus2, int page, int pagesize){
        return Pager.config(this.countSixStatusOrdersBySelect(deliveryregion, deliveryprovince, deliveryplace, status1, status2, status3, status4, status5, status6, ordertype, sellerstatus1, sellerstatus2), (int limit, int offset) -> this.listSixStatusOrdersBySelect(deliveryregion, deliveryprovince, deliveryplace, status1, status2, status3, status4, status5, status6, ordertype, sellerstatus1, sellerstatus2, limit, offset))
                .page(page, pagesize);
    }



    //查询订单  一种状态
    @Select("<script> " +
            "select count(*) from orders " +
            startWhere + "<if test='deliveryregion!=0'>and regionId=#{deliveryregion} </if>" +
            "<if test='deliveryprovince!=0'> and provinceId=#{deliveryprovince} </if>" +
            "<if test='deliveryplace!=0'> and portId=#{deliveryplace} </if>"  +
            "and " + OrderStatusOne + OrderType + SellerStatusNotEqualsTwo + endWhere +
            "</script>")
    public int countOneStatusOrdersBySelect(@Param("deliveryregion")Integer deliveryregion,
                                            @Param("deliveryprovince")Integer deliveryprovince,
                                            @Param("deliveryplace")Integer deliveryplace,
                                            @Param("status")EnumOrder status,
                                            @Param("ordertype")EnumOrder ordertype,
                                            @Param("sellerstatus1")EnumOrder sellerstatus1,
                                            @Param("sellerstatus2")EnumOrder sellerstatus2);
    @Select("<script>" +
            "select * from orders" +
            startWhere + "<if test='deliveryregion!=0'> and regionId=#{deliveryregion} </if>" +
            "<if test='deliveryprovince!=0'> and provinceId=#{deliveryprovince} </if>" +
            "<if test='deliveryplace!=0'> and portId=#{deliveryplace} </if>" +
            "and " + OrderStatusOne + OrderType + SellerStatusNotEqualsTwo + endWhere + OrderByCreatetimeDesc + limitOffset +
            "</script>")
    public List<Order> listOneStatusOrderBySelect(@Param("deliveryregion")int deliveryregion,
                                                  @Param("deliveryprovince")int deliveryprovince,
                                                  @Param("deliveryplace")int deliveryplace,
                                                  @Param("status")EnumOrder status,
                                                  @Param("ordertype")EnumOrder ordertype,
                                                  @Param("sellerstatus1")EnumOrder sellerstatus1,
                                                  @Param("sellerstatus2")EnumOrder sellerstatus2,
                                                  @Param("limit")int limit,
                                                  @Param("offset")int offset);
    public default Pager<Order> getOneStatusOrdersBySelect(int deliveryregion, int deliveryprovince, int deliveryplace, EnumOrder status, EnumOrder ordertype, EnumOrder sellerstatus1, EnumOrder sellerstatus2, int page, int pagesize){
        return Pager.config(this.countOneStatusOrdersBySelect(deliveryregion, deliveryprovince, deliveryplace, status, ordertype, sellerstatus1, sellerstatus2), (int limit, int offset) -> this.listOneStatusOrderBySelect(deliveryregion, deliveryprovince, deliveryplace, status, ordertype, sellerstatus1, sellerstatus2, limit, offset))
                .page(page, pagesize);
    }

    //撮合查询订单 1 种状态
    @Select("<script> " +
            "select count(*) from orders " +
            startWhere + OrderSelectSQL1  +
            "and " + OrderStatusOne + OrderType + SellerStatusNotEqualsThree + endWhere +
            "</script>")
    public int countOneStatusMatchOrdersBySelect(@Param("deliveryregion")String deliveryregion,
                                                 @Param("deliveryprovince")String deliveryprovince,
                                                 @Param("deliveryplace")String deliveryplace,
                                                 @Param("status")EnumOrder status,
                                                 @Param("ordertype")EnumOrder ordertype,
                                                 @Param("sellerstatus1")EnumOrder sellerstatus1,
                                                 @Param("sellerstatus2")EnumOrder sellerstatus2,
                                                 @Param("sellerstatus3")EnumOrder sellerstatus3);
    @Select("<script>" +
            "select * from orders" +
            startWhere + OrderSelectSQL1 +
            "and " + OrderStatusOne + OrderType + SellerStatusNotEqualsThree + endWhere + OrderByCreatetimeDesc + limitOffset +
            "</script>")
    public List<Order> listOneStatusMatchOrderBySelect(@Param("deliveryregion")String deliveryregion,
                                                       @Param("deliveryprovince")String deliveryprovince,
                                                       @Param("deliveryplace")String deliveryplace,
                                                       @Param("status")EnumOrder status,
                                                       @Param("ordertype")EnumOrder ordertype,
                                                       @Param("sellerstatus1")EnumOrder sellerstatus1,
                                                       @Param("sellerstatus2")EnumOrder sellerstatus2,
                                                       @Param("sellerstatus3")EnumOrder sellerstatus3,
                                                       @Param("limit")int limit,
                                                       @Param("offset")int offset);
    public default Pager<Order> getOneStatusMatchOrdersBySelect(String deliveryregion, String deliveryprovince, String deliveryplace, EnumOrder status, EnumOrder ordertype, EnumOrder sellerstatus1, EnumOrder sellerstatus2, EnumOrder sellerstatus3, int page, int pagesize){
        return Pager.config(this.countOneStatusMatchOrdersBySelect(deliveryregion, deliveryprovince, deliveryplace, status, ordertype, sellerstatus1, sellerstatus2, sellerstatus3), (int limit, int offset) -> this.listOneStatusMatchOrderBySelect(deliveryregion, deliveryprovince, deliveryplace, status, ordertype, sellerstatus1, sellerstatus2, sellerstatus3, limit, offset))
                .page(page, pagesize);
    }

    //查询订单  两种状态
    @Select("<script> " +
            "select count(*) from orders " +
            startWhere + OrderSelectSQL1  +
            "and " + OrderStatusTwo + OrderType + SellerStatusNotEquals + endWhere +
            "</script>")
    public int countTwoStatusOrdersBySelect(@Param("deliveryregion")String deliveryregion,
                                            @Param("deliveryprovince")String deliveryprovince,
                                            @Param("deliveryplace")String deliveryplace,
                                            @Param("status1")EnumOrder status1,
                                            @Param("status2")EnumOrder status2,
                                            @Param("ordertype")EnumOrder ordertype,
                                            @Param("sellerstatus")EnumOrder sellerstatus);
    @Select("<script>" +
            "select * from orders" +
            startWhere + OrderSelectSQL1 +
            "and " + OrderStatusTwo + OrderType + SellerStatusNotEquals + endWhere + OrderByCreatetimeDesc + limitOffset +
            "</script>")
    public List<Order> listTwoStatusOrderBySelect(@Param("deliveryregion")String deliveryregion,
                                                  @Param("deliveryprovince")String deliveryprovince,
                                                  @Param("deliveryplace")String deliveryplace,
                                                  @Param("status1")EnumOrder status1,
                                                  @Param("status2")EnumOrder status2,
                                                  @Param("ordertype")EnumOrder ordertype,
                                                  @Param("sellerstatus")EnumOrder sellerstatus,
                                                  @Param("limit")int limit,
                                                  @Param("offset")int offset);
    public default Pager<Order> getTwoStatusOrdersBySelect(String deliveryregion, String deliveryprovince, String deliveryplace, EnumOrder status1, EnumOrder status2, EnumOrder ordertype, EnumOrder sellerstatus, int page, int pagesize){
        return Pager.config(this.countTwoStatusOrdersBySelect(deliveryregion, deliveryprovince, deliveryplace, status1, status2, ordertype, sellerstatus), (int limit, int offset) -> this.listTwoStatusOrderBySelect(deliveryregion, deliveryprovince, deliveryplace, status1, status2, ordertype, sellerstatus, limit, offset))
                .page(page, pagesize);
    }

    //查询订单-三种状态
    @Select("<script> " +
            "select count(*) from orders " +
            startWhere + OrderSelectSQL1 +
            "and " + OrderStatusThree + OrderType +  SellerStatusNotEquals + endWhere +
            "</script>")
    public int countThreeStatusOrdersBySelect(@Param("deliveryregion")String deliveryregion,
                                              @Param("deliveryprovince")String deliveryprovince,
                                              @Param("deliveryplace")String deliveryplace,
                                              @Param("status1")EnumOrder status1,
                                              @Param("status2")EnumOrder status2,
                                              @Param("status3")EnumOrder status3,
                                              @Param("ordertype")EnumOrder ordertype,
                                              @Param("sellerstatus")EnumOrder sellerstatus);
    @Select("<script>" +
            "select * from orders" +
            startWhere + OrderSelectSQL1 +
            "and " + OrderStatusThree + OrderType + SellerStatusNotEquals + endWhere + OrderByCreatetimeDesc + limitOffset +
            "</script>")
    public List<Order> listThreeStatusOrdersBySelect(@Param("deliveryregion")String deliveryregion,
                                                     @Param("deliveryprovince")String deliveryprovince,
                                                     @Param("deliveryplace")String deliveryplace,
                                                     @Param("status1")EnumOrder status1,
                                                     @Param("status2")EnumOrder status2,
                                                     @Param("status3")EnumOrder status3,
                                                     @Param("ordertype")EnumOrder ordertype,
                                                     @Param("sellerstatus")EnumOrder sellerstatus,
                                                     @Param("limit")int limit,
                                                     @Param("offset")int offset);
    public default Pager<Order> getThreeStatusOrdersBySelect(String deliveryregion, String deliveryprovince, String deliveryplace, EnumOrder status1, EnumOrder status2, EnumOrder status3, EnumOrder ordertype, EnumOrder sellerstatus, int page, int pagesize){
        return Pager.config(this.countThreeStatusOrdersBySelect(deliveryregion, deliveryprovince, deliveryplace, status1, status2, status3, ordertype, sellerstatus), (int limit, int offset) -> this.listThreeStatusOrdersBySelect(deliveryregion, deliveryprovince, deliveryplace, status1, status2, status3, ordertype, sellerstatus, limit, offset))
                .page(page, pagesize);
    }


    //复审订单列表-2种状态
    @Select("<script>" +
            "select count(*) from orders " +
            startWhere + OrderStatusTwo + "and" + SellerStatusTwo + OrderType + endWhere +
            "</script>")
    public int countTwoSellerStatusOrders(@Param("status1")EnumOrder status1,
                                          @Param("status2")EnumOrder status2,
                                          @Param("sellerstatus1")EnumOrder sellerstatus1,
                                          @Param("sellerstatus2")EnumOrder sellerstatus2,
                                          @Param("ordertype")EnumOrder ordertype);
    @Select("<script>" +
            "select * from orders " +
            startWhere + OrderStatusTwo + "and" + SellerStatusTwo + OrderType + endWhere + OrderByCreatetimeDesc + limitOffset +
            "</script>")
    public List<Order> listTwoSellerStatusOrders(@Param("status1")EnumOrder status1,
                                                 @Param("status2")EnumOrder status2,
                                                 @Param("sellerstatus1")EnumOrder sellerstatus1,
                                                 @Param("sellerstatus2")EnumOrder sellerstatus2,
                                                 @Param("ordertype")EnumOrder ordertype,
                                                 @Param("limit") int limit,
                                                 @Param("offset") int offset);
    public default Pager<Order> getTwoSellerStatusOrders(EnumOrder status1, EnumOrder status2, EnumOrder sellerstatus1, EnumOrder sellerstatus2, EnumOrder ordertype, int page, int pagesize){
        return Pager.config(this.countTwoSellerStatusOrders(status1, status2, sellerstatus1, sellerstatus2, ordertype), (int limit, int offset) -> this.listTwoSellerStatusOrders(status1, status2, sellerstatus1, sellerstatus2, ordertype, limit, offset))
                .page(page, pagesize);
    }

    //查询复审订单-2种状态
    @Select("<script> " +
            "select count(*) from orders " +
            startWhere + OrderSelectSQL1 +
            "and " + OrderStatusTwo + "and" + SellerStatusTwo + OrderType + endWhere +
            "</script>")
    public int countTwoSellerStatusOrdersBySelect(@Param("deliveryregion")String deliveryregion,
                                                  @Param("deliveryprovince")String deliveryprovince,
                                                  @Param("deliveryplace")String deliveryplace,
                                                  @Param("status1")EnumOrder status1,
                                                  @Param("status2")EnumOrder status2,
                                                  @Param("sellerstatus1")EnumOrder sellerstatus1,
                                                  @Param("sellerstatus2")EnumOrder sellerstatus2,
                                                  @Param("ordertype")EnumOrder ordertype);
    @Select("<script>" +
            "select * from orders" +
            startWhere + OrderSelectSQL1 +
            "and " + OrderStatusTwo + "and" + SellerStatusTwo + OrderType + endWhere + OrderByCreatetimeDesc + limitOffset +
            "</script>")
    public List<Order> listTwoSellerStatusOrdersBySelect(@Param("deliveryregion")String deliveryregion,
                                                         @Param("deliveryprovince")String deliveryprovince,
                                                         @Param("deliveryplace")String deliveryplace,
                                                         @Param("status1")EnumOrder status1,
                                                         @Param("status2")EnumOrder status2,
                                                         @Param("sellerstatus1")EnumOrder sellerstatus1,
                                                         @Param("sellerstatus2")EnumOrder sellerstatus2,
                                                         @Param("ordertype")EnumOrder ordertype,
                                                         @Param("limit")int limit,
                                                         @Param("offset")int offset);
    public default Pager<Order> getTwoSellerStatusOrdersBySelect(String deliveryregion, String deliveryprovince, String deliveryplace, EnumOrder status1, EnumOrder status2, EnumOrder sellerstatus1, EnumOrder sellerstatus2, EnumOrder ordertype, int page, int pagesize){
        return Pager.config(this.countTwoSellerStatusOrdersBySelect(deliveryregion, deliveryprovince, deliveryplace, status1, status2, sellerstatus1, sellerstatus2, ordertype), (int limit, int offset) -> this.listTwoSellerStatusOrdersBySelect(deliveryregion, deliveryprovince, deliveryplace, status1, status2, sellerstatus1, sellerstatus2, ordertype, limit, offset))
                .page(page, pagesize);
    }



    //复审订单列表-1种状态
    @Select("<script>" +
            "select count(*) from orders " +
            startWhere + OrderStatusOne + "and" + SellerStatusTwo + OrderType + endWhere +
            "</script>")
    public int countOneSellerStatusOrders(@Param("status")EnumOrder status,
                                          @Param("sellerstatus1")EnumOrder sellerstatus1,
                                          @Param("sellerstatus2")EnumOrder sellerstatus2,
                                          @Param("ordertype")EnumOrder ordertype);
    @Select("<script>" +
            "select * from orders " +
            startWhere + OrderStatusOne + "and" + SellerStatusTwo + OrderType + endWhere + OrderByCreatetimeDesc + limitOffset +
            "</script>")
    public List<Order> listOneSellerStatusOrders(@Param("status")EnumOrder status,
                                                 @Param("sellerstatus1")EnumOrder sellerstatus1,
                                                 @Param("sellerstatus2")EnumOrder sellerstatus2,
                                                 @Param("ordertype")EnumOrder ordertype,
                                                 @Param("limit") int limit,
                                                 @Param("offset") int offset);
    public default Pager<Order> getOneSellerStatusOrders(EnumOrder status, EnumOrder sellerstatus1, EnumOrder sellerstatus2, EnumOrder ordertype, int page, int pagesize){
        return Pager.config(this.countOneSellerStatusOrders(status, sellerstatus1, sellerstatus2, ordertype), (int limit, int offset) -> this.listOneSellerStatusOrders(status, sellerstatus1, sellerstatus2, ordertype, limit, offset))
                .page(page, pagesize);
    }

    //查询复审订单-1种状态
    @Select("<script> " +
            "select count(*) from orders " +
            startWhere + "<if test='deliveryregion!=0'> and regionId=#{deliveryregion} </if>" +
            "<if test='deliveryprovince!=0'> and provinceId=#{deliveryprovince} </if>" +
            "<if test='deliveryplace!=0'> and portId=#{deliveryplace} </if>" +
            "and " + OrderStatusOne + "and" + SellerStatusTwo + OrderType + endWhere +
            "</script>")
    public int countOneSellerStatusOrdersBySelect(@Param("deliveryregion")int deliveryregion,
                                                  @Param("deliveryprovince")int deliveryprovince,
                                                  @Param("deliveryplace")int deliveryplace,
                                                  @Param("status")EnumOrder status,
                                                  @Param("sellerstatus1")EnumOrder sellerstatus1,
                                                  @Param("sellerstatus2")EnumOrder sellerstatus2,
                                                  @Param("ordertype")EnumOrder ordertype);
    @Select("<script>" +
            "select * from orders" +
            startWhere + "<if test='deliveryregion!=0'> and regionId=#{deliveryregion} </if>" +
            "<if test='deliveryprovince!=0'> and provinceId=#{deliveryprovince} </if>" +
            "<if test='deliveryplace!=0'> and portId=#{deliveryplace} </if>" +
            "and " + OrderStatusOne + "and" + SellerStatusTwo + OrderType + endWhere + OrderByCreatetimeDesc + limitOffset +
            "</script>")
    public List<Order> listOneSellerStatusOrdersBySelect(@Param("deliveryregion")int deliveryregion,
                                                         @Param("deliveryprovince")int deliveryprovince,
                                                         @Param("deliveryplace")int deliveryplace,
                                                         @Param("status")EnumOrder status,
                                                         @Param("sellerstatus1")EnumOrder sellerstatus1,
                                                         @Param("sellerstatus2")EnumOrder sellerstatus2,
                                                         @Param("ordertype")EnumOrder ordertype,
                                                         @Param("limit")int limit,
                                                         @Param("offset")int offset);
    public default Pager<Order> getOneSellerStatusOrdersBySelect(int deliveryregion, int deliveryprovince, int deliveryplace, EnumOrder status, EnumOrder sellerstatus1, EnumOrder sellerstatus2, EnumOrder ordertype, int page, int pagesize){
        return Pager.config(this.countOneSellerStatusOrdersBySelect(deliveryregion, deliveryprovince, deliveryplace, status, sellerstatus1, sellerstatus2, ordertype), (int limit, int offset) -> this.listOneSellerStatusOrdersBySelect(deliveryregion, deliveryprovince, deliveryplace, status, sellerstatus1, sellerstatus2, ordertype, limit, offset))
                .page(page, pagesize);
    }

}
