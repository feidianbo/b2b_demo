package demo.core.persistence;

import demo.core.domain.Admin;
import demo.core.domain.Demand;
import demo.core.util.Pager;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by fanjun on 14-11-25.
 */
public interface DemandMapper {

    //根据省份,港口,热值,硫份组合查询的结果集. 条件:已审核通过,未删除.顺序:发布时间倒序
    final String checkWhereSql =
            "<where> "+
                    "<if test='coalWord!=null'> coaltype = #{coalWord}</if>" +
                    "<if test='area!=0'> and regionId = #{area}</if>" +
                    "<if test='province!=0'> and provinceId = #{province}</if>" +
                    "<if test='port!=0'> and portId = #{port}</if>" +
                    "<if test='otherDeliveryPlace ==999'> and otherplace is not null </if>" +
                    "<if test='lowHotValue!=null'> and NCV &gt;= #{lowHotValue}</if>" +
                    "<if test='highHotValue!=null'> and NCV &lt;= #{highHotValue}</if>" +
                    "<if test='lowSulfurContent!=null'> and RS &gt;= #{lowSulfurContent}</if>" +
                    "<if test='highSulfurContent!=null'> and RS &lt;= #{highSulfurContent}</if>" +
                    " and checkstatus='审核通过' "+
                    " and isdelete=0 "+       //报价结束后超过7天的记录状态改为删除
                    "</where> ";

    @Select("<script>" +
            "select * from demands " +
            checkWhereSql+
            " order by tradestatus,releasetime desc " +
            " limit #{limit} offset #{offset}"+
            "</script>")
    public List<Demand> getDemandsByCheck(@Param("coalWord") String coalWord,@Param("area") int area,@Param("province") int province,@Param("port") int port,@Param("otherDeliveryPlace") int otherDeliveryPlace,
                                          @Param("lowHotValue") Integer lowHotValue,@Param("highHotValue") Integer highHotValue,
                                          @Param("lowSulfurContent") BigDecimal lowSulfurContent,@Param("highSulfurContent") BigDecimal highSulfurContent,
                                          @Param("limit") int limit,@Param("offset") int offset);

    //组合查询的结果总数
    @Select("<script>" +
            "select count(*) from demands " +
            checkWhereSql+
            "</script>")
    public int countDemandsByCheck(@Param("coalWord") String coalWord,@Param("area") int area,@Param("province") int province,@Param("port") int port,@Param("otherDeliveryPlace") int otherDeliveryPlace,
                                   @Param("lowHotValue") Integer lowHotValue,@Param("highHotValue") Integer highHotValue,
                                   @Param("lowSulfurContent") BigDecimal lowSulfurContent,@Param("highSulfurContent") BigDecimal highSulfurContent);

    //根据需求编号查询需求对象
    @Select("select * from demands where demandcode=#{demandcode}")
    public Demand getDemandByDemandcode(String demandcode);

    //根据需求编号查询需求对象
    @Select("select * from demands where demandcode=#{demandcode} and userid=#{userid}")
    public Demand getDemandByDemandcodeAndUserid(@Param("demandcode") String demandcode,@Param("userid") int userid);

    //根据需求id查询需求对象
    @Select("select * from demands where id=#{id} and isdelete=0")
    public Demand getDemandById(int id);

    //报价成功需求表报价人数加1
    @Update("update demands set quotenum=quotenum+1 where demandcode=#{demandcode}")
    public void plusQuotenum(String demandcode);

    //统计总需求数
    @Select("select count(*) from demands where releasestatus=1 and isdelete=0")
    public int countAllDemands();

    //个人中心-我的报价删除,报价人数减1
    @Update("update demands set quotenum=quotenum-1 where demandcode=#{demandcode}")
    public void minusQuotenum(String demandcode);

    //添加需求
    @Insert("insert into demands(userid,demandcode,demandcustomer,coaltype,NCV,ADS," +
            "RS,TM,IM,ADV,RV,AFT,ASH,HGI,deliverydistrict,deliveryprovince,deliveryplace,otherplace,demandamount," +
            "deliverydate,deliverydatestart,deliverydateend,deliverymode,residualdemand,inspectionagency," +
            "otherorg,quoteenddate,noshowdate,releasetime,checkstatus,tradestatus,releasestatus,regionId,provinceId,portId,releasecomment) values(" +
            "#{userid},dateseq_next_value('XQ'),#{demandcustomer},#{coaltype},#{NCV},#{ADS},#{RS},#{TM},#{IM},#{ADV},#{RV}," +
            "#{AFT},#{ASH},#{HGI},#{deliverydistrict},#{deliveryprovince},#{deliveryplace},#{otherplace},#{demandamount}," +
            "#{deliverydate},#{deliverydatestart},#{deliverydateend},#{deliverymode},#{residualdemand},#{inspectionagency},#{otherorg}," +
            "#{quoteenddate},#{noshowdate},#{releasetime},#{checkstatus},#{tradestatus},#{releasestatus},#{regionId},#{provinceId},#{portId},#{releasecomment})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public int addDemand(Demand demand);

    //更新需求编号
    @Update("update demands set demandcode=#{code} where id=#{id}")
    public void modifyDemandcodeWithCode(@Param("code") String code,@Param("id") int id);

    //更新发布状态
    @Update("update demands set releasestatus=1 where demandcode=#{demandcode}")
    public void modifyReleaseStatusByDemandcode(String demandcode);

    //更改需求
    @Update("update demands set coaltype=#{coaltype},NCV=#{NCV},ADS=#{ADS},RS=#{RS},TM=#{TM}," +
            "IM=#{IM},ADV=#{ADV},RV=#{RV},AFT=#{AFT},ASH=#{ASH},HGI=#{HGI},deliverydistrict=#{deliverydistrict}," +
            "deliveryprovince=#{deliveryprovince},deliveryplace=#{deliveryplace},otherplace=#{otherplace},demandamount=#{demandamount}," +
            "deliverydate=#{deliverydate},deliverydatestart=#{deliverydatestart},deliverydateend=#{deliverydateend}," +
            "deliverymode=#{deliverymode},residualdemand=#{residualdemand},inspectionagency=#{inspectionagency}," +
            "otherorg=#{otherorg},quoteenddate=#{quoteenddate},noshowdate=#{noshowdate},releasetime=#{releasetime}," +
            "comment=#{comment}," +
            "regionId=#{regionId},provinceId=#{provinceId},portId=#{portId},releasecomment=#{releasecomment} where demandcode=#{demandcode}")
    public void modifyDemand(Demand demand);


    //更改需求审核的审核状态和备注
    @Update("update demands set checkstatus=#{checkstatus},comment=#{comment},checktime=now() where demandcode=#{demandcode} and checkstatus='待审核'")
    public void modifyCheckstatusAndComment(@Param("checkstatus") String checkstatus,@Param("comment") String comment,@Param("demandcode") String demandcode);

    //获取我的发布的需求
    @Select("select * from demands where releasestatus=1 and userid=#{userid} order by releasetime desc limit #{startNum},#{pageSize}")
    public List<Demand> getTurnpageListWithUserid(@Param("userid") int userid,@Param("startNum") int startNum,@Param("pageSize") int pageSize);

/*    //我的发布-删除需求
    @Delete("delete from demands where demandcode=#{demandcode}")
    public void deleteMyDemandByDemandcode(String demandcode);*/

    //更改交易状态
    @Update("update demands set tradestatus=#{tradestatus},tradername=#{dealername},traderphone=#{dealerphone},traderId=#{traderId} where demandcode=#{demandcode}")
    public void modifyTradestatusByDemandcode(@Param("dealername")String tradername,@Param("dealerphone")String traderphone,@Param("tradestatus") String tradestatus,@Param("demandcode") String demandcode,@Param("traderId") int traderId);

    //导航栏上方模糊搜索

    //后台需求列表模糊搜索
    @Select("select d.* from(select * from demands where " +
            " demandcustomer like concat('%',#{content},'%') or "+
            " demandcode like concat('%',#{content},'%') or "+
            " deliverydistrict like concat('%',#{content},'%') or "+
            " deliveryprovince like concat('%',#{content},'%') or "+
            " deliveryplace like concat('%',#{content},'%') or "+
            " where d.releasestatus=1")
    public List<Demand> getDemandByContent(String content);

   /* //每天0点检查修改今天报价截止的需求记录改为报价结束
    @Update("update demands set tradestatus='报价结束' where date_add(quoteenddate,interval 1 day) = date_format(now(),'%Y-%m-%d')")
    public void modifyTradestatusTask();*/

/*    //将超过7天的记录状态改为删除
    @Update("update demands set isdelete=1 where noshowdate = date_format(now(),'%Y-%m-%d')")
    public void modifyIsdeleteTask();*/

    //设置需求状态为已删除
    @Update("update demands set isdelete=1 where demandcode=#{demandcode}")
    public void modifyIsdeleteByDemandcode(String demandcode);

    //设置需求状态为已删除
    @Update("update demands set isdelete=1 where demandcode=#{demandcode} and userid=#{userid}")
    public void modifyIsdeleteByDemandcodeAndUserid(@Param("demandcode") String demandcode,@Param("userid") int userid);

    //根据需求id和用户id查询,不能对自己发布的需求报价
    @Select("select count(*) from demands where id=#{demandid} and userid=#{userid}")
    public int countMydemandByDemandidAndUserid(@Param("demandid") int demandid,@Param("userid") int userid);


    final String searchCondition =
            "<if test='deliveryregion!=0'> and regionId=#{deliveryregion}</if>" +
                    "<if test='deliveryprovince!=0'> and provinceId=#{deliveryprovince}</if>" +
                    "<if test='deliveryplace!=0'> and portId=#{deliveryplace}</if>"+
                    "<if test='content!= null'> and demandcode like #{content}</if>";
    final String demandStatus =
            "<if test='checkstatus!=null'> and checkstatus=#{checkstatus}</if>";
     //后台page分页
    //待审核
    @Select("<script>select count(*) from demands where releasestatus=1 and isdelete=0"
            + searchCondition + demandStatus +"</script>")
    public int countAllDemandsBySelect(@Param("deliveryregion")int deliveryregion,
                                       @Param("deliveryprovince")int deliveryprovince,
                                       @Param("deliveryplace")int deliveryplace,
                                       @Param("content")String content,
                                       @Param("checkstatus")String checkstatus);

    @Select("<script>select * from demands where releasestatus=1 and isdelete=0 "
            + searchCondition + demandStatus + " order by releasetime desc " +
            "limit #{limit} offset #{offset}</script>")
    public List<Demand> listAllDemandsBySelect(@Param("deliveryregion")int deliveryregion,
                                               @Param("deliveryprovince")int deliveryprovince,
                                               @Param("deliveryplace")int deliveryplace,
                                               @Param("content")String content,
                                               @Param("checkstatus")String checkstatus,
                                               @Param("limit") int limit,
                                               @Param("offset") int offset);

    public default Pager<Demand> pageAllDemandsBySelect(int deliveryregion, int deliveryprovince, int deliveryplace, String content, String checkstatus, int page, int pagesize){
        String searchcontent = "%" + content + "%";
        return Pager.config(this.countAllDemandsBySelect(deliveryregion, deliveryprovince, deliveryplace, searchcontent, checkstatus),
                (int limit, int offset) -> this.listAllDemandsBySelect(deliveryregion, deliveryprovince, deliveryplace, searchcontent, checkstatus, limit, offset))
                .page(page, pagesize);
    }

    //后台--我的客户--需求列表显示
    static final  String showAllDynamicSql =
            "<where>"+
            "<if test='demand!=null'>" +
                    "<if test='demand.checkstatus!=null'> and d.checkstatus=#{demand.checkstatus}</if>"+
                    "<if test='demand.demandcode!=null and demand.demandcode!=\"\"'> and d.demandcode=#{demand.demandcode}</if>"+
                    "<if test='demand.coaltype!=null and demand.coaltype!=\"\"'> and d.coaltype=#{demand.coaltype}</if>" +
                    "<if test='demand.deliverymode!=null and demand.deliverymode!=\"\"'> and d.deliverymode=#{demand.deliverymode}</if>" +
                    "<if test='demand.deliverydistrict!=null and demand.deliverydistrict!=\"\"'> and d.deliverydistrict=#{demand.deliverydistrict}</if>" +
                    "<if test='demand.deliveryprovince!=null and demand.deliveryprovince!=\"\" ' > and d.deliveryprovince=#{demand.deliveryprovince}</if>" +
                    "<if test='demand.deliveryplace!=null and demand.deliveryplace!=\"\"'> and d.deliveryplace=#{demand.deliveryplace}</if>" +
                    "<if test='demand.NCV!=null'> and d.NCV=#{demand.NCV}</if>" +
                    "<if test='demand.RS!=null'> and d.RS=#{demand.RS}</if>" +
                    "<if test='demand.ADV!=null'> and d.ADV=#{demand.ADV}</if>" +
                    "<if test='demand.TM!=null'> and d.TM=#{demand.TM}</if>"+
                    "</if>" +
                    "<if test='currentUser.isAdmin!=true'> and d.tradercode=#{currentUser.jobnum}</if>" +
                    "</where>";

    @Select("<script>select distinct(d.id),d.*,dealers.dealername tradername from demands d left join dealers dealers on d.tradercode = dealers.dealerid "+showAllDynamicSql+" order by d.id limit #{limit} offset #{offset} </script>")
    public List<Demand> showAllByDealerId(@Param("currentUser") Admin admin,@Param("demand") Demand demand, @Param("limit")int limit,@Param("offset")int offset);


    @Select("<script>select count(distinct(d.id)) from demands d left join dealers on d.tradercode = dealers.dealerid "+showAllDynamicSql+"</script>")
    public int countAllByDealerId(@Param("currentUser") Admin admin,@Param("demand") Demand demand);

    public default Pager<Demand> getAllDemandByDealerId(Admin currentUser,Demand demand,int page, int pagesize){
        return Pager.config(this.countAllByDealerId(currentUser,demand), (int limit, int offset) -> this.showAllByDealerId(currentUser,demand,limit, offset))
                .page(page, pagesize);
    }

//    @Select("<script>select * from demands d "+showAllDynamicSql+" order by demandcode limit #{limit} offset #{offset} </script>")
//    public List<Demand> showAllList(@Param("demand") Demand demand, @Param("limit")int limit,@Param("offset")int offset);
//
//
//    @Select("<script>select count(1) from demands d "+showAllDynamicSql+"</script>")
//    public int showAllListCount(@Param("demand") Demand demand);
//
//    public default Pager<Demand> getAllDemandList(Demand demand,int page, int pagesize){
//        return Pager.config(this.showAllListCount(demand), (int limit, int offset) -> this.showAllList( demand,limit,offset))
//                .page(page, pagesize);
//    }

    //需求表匹配量累加
    @Update("update demands set purchasednum=#{supplyton}+purchasednum where demandcode=#{demandcode}")
    public void modifyPurchaseNumByDemandcode(@Param("supplyton") int supplyton,@Param("demandcode") String demandcode);

    //首页数据统计，查询所有审核通过的需求
    @Select("select * from demands where checkstatus='审核通过' and isdelete=0")
    public List<Demand> getAllPassDemandList();

    //统计审核通过的需求
    @Select("select IFNULL(SUM(demandamount),0) from demands where checkstatus='审核通过' and isdelete=0 and coaltype='动力煤' and tradestatus='开始报价' ")
    public long getSumPassDemand();

    //统计审核通过的需求
    @Select("select IFNULL(SUM(demandamount),0) from demands where checkstatus='审核通过' and isdelete=0 and coaltype='无烟煤' and tradestatus='开始报价' ")
    public long getAnthraciteDemand();

    //通过dealerid查询需求信息
    @Select("select * from demands where tradercode=#{tradercode}")
    public List<Demand> getDemandListByDealerid(String tradercode);

    //通过region， province, harbour
    @Select("select * from demands where deliverydistrict=#{deliverydistrict} " +
            "and deliveryprovince=#{deliveryprovince} and deliveryplace=#{deliveryplace}")
    public List<Demand> getDemandListByRegionProvinceHarbour(@Param("deliverydistrict")String deliverydistrict,
                                                             @Param("deliveryprovince")String deliveryprovince,
                                                             @Param("deliveryplace")String deliveryplace);

    @Update("update demands set tradercode=#{tradercode}, tradername=#{tradername}, traderphone=#{traderphone} where id=#{id}")
    public void updateDemandDealer(@Param("tradercode")String tradercode,
                                   @Param("tradername")String tradername,
                                   @Param("traderphone")String traderphone,
                                   @Param("id")int id);

    @Update("update demands set traderphone=#{traderphone} where id=#{id}")
    public void updateDemandDealerPhone(@Param("traderphone")String traderphone,
                                        @Param("id")int id);

    @Update("update demands set viewtimes=#{viewtimes} where id=#{id}")
    public void setPageViewTimesById(@Param("viewtimes")int viewtimes,
                                     @Param("id")Integer id);

    @Update("update demands set traderphone=#{traderphone} where traderid=#{traderid}")
    public void updateDealerPhone(@Param("traderphone")String traderphone,
                                        @Param("traderid")int dealerid);
}




