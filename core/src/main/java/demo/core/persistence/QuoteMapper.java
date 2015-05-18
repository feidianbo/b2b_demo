package demo.core.persistence;

import demo.core.domain.Admin;
import demo.core.domain.Quote;
import demo.core.util.Pager;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by fanjun on 14-11-26.
 */
public interface QuoteMapper {

    //增加报价记录
    @Insert("insert into quotes(userid,demandid,demandcode,supplyton,quote,lastupdatetime,status," +
            "deliverymode,deliverydate,deliverydatestart,deliverydateend,quoteenddate,companyname,traderphone) values(" +
            "#{userid},#{demandid},#{demandcode},#{supplyton},#{quote},#{lastupdatetime},#{status}," +
            "#{deliverymode},#{deliverydate},#{deliverydatestart},#{deliverydateend},#{quoteenddate},#{companyname},#{traderphone})")
    public void addQuote(Quote quote);

   /* //删除我的报价记录
    @Delete("delete from quotes where id=#{id}")
    public void deleteQuoteById(int id);*/

    //统计所有报价记录-进行中
    @Select("select count(*) from quotes where isdelete=0 and userid=#{userid} and (status='报价中' or status='比价中')")
    public int countAllMyQuoteUnderway(int userid);

    //我的报价-进行中
    @Select("select * from quotes where userid=#{userid} and isdelete=0 and (status='报价中' or status='比价中') order by status,lastupdatetime desc limit #{startNum},#{pageSize}")
    public List<Quote> getTurnpageUnderwayList(@Param("userid") int userid,@Param("startNum") int startNum,@Param("pageSize")int pageSize);

    //统计所有报价记录-已中标
    @Select("select count(*) from quotes where isdelete=0 and userid=#{userid} and status='已中标'")
    public int countAllMyQuoteBid(int userid);

    //我的报价-已中标
    @Select("select * from quotes where userid=#{userid} and isdelete=0 and status='已中标' order by lastupdatetime desc limit #{startNum},#{pageSize}")
    public List<Quote> getTurnpageBidList(@Param("userid") int userid,@Param("startNum") int startNum,@Param("pageSize")int pageSize);

    //统计所有报价记录-未中标
    @Select("select count(*) from quotes where isdelete=0 and userid=#{userid} and status='未中标'")
    public int countAllMyQuoteNotBid(int userid);

    //我的报价-未中标
    @Select("select * from quotes where userid=#{userid} and isdelete=0 and status='未中标' order by lastupdatetime desc limit #{startNum},#{pageSize}")
    public List<Quote> getTurnpageNotBidList(@Param("userid") int userid,@Param("startNum") int startNum,@Param("pageSize")int pageSize);

    //判断是否对此需求报价过,返回对象判断,利用对象
    @Select("select * from quotes where userid=#{userid} and demandid=#{demandid}")
    public Quote getQuoteByUserIdAndDemandid(@Param("userid") int userid,@Param("demandid")int demandid);

    //修改申供瓶数和报价
    @Update("update quotes set supplyton=#{supplyton},quote=#{quote},lastupdatetime=now() where id=#{quoteid}")
    public void modifyQuoteByQuoteid(@Param("supplyton") int supplyton,@Param("quote") int quote,@Param("quoteid") int quoteid);

    //通过id查询报价记录
    @Select("select * from quotes where id=#{id}")
    public Quote getQuoteById(int id);

    //判断某客户是否对某需求信息报价过
    @Select("select count(*) from quotes where userid=#{userid} and demandid=#{demandid}")
    public int countQuotedByUserIdAndDemandid(@Param("userid") int userid,@Param("demandid")int demandid);

  /*  //删除我的报价记录,通过需求编号
    @Delete("delete from quotes where demandcode=#{demandcode}")
    public void deleteQuoteByDemandcode(String demandcode);*/

    /*//将报价表到了报价截止日期的记录改为比价中
    @Update("update quotes set status='比价中' where date_add(quoteenddate,interval 1 day) = date_format(now(),'%Y-%m-%d')")
    public void modifyStatusToCompareQuoteTask();*/

  /*  //将报价表过了提货时间截止仍没有中标的改为未中标
    @Update("update quotes set status='未中标' where status='比价中' and (date_add(deliverydate,interval 1 day)=date_format(now(),'%Y-%m-%d') or date_add(deliverydateend,interval 1 day)=date_format(now(),'%Y-%m-%d'))")
    public void modifyStatusToNotBidTask();*/

    //取消我的报价,设置状态为删除
    @Update("update quotes set isdelete=1 where id=#{id} and userid=#{userid}")
    public void modifyIsdeleteById(@Param("id") int id,@Param("userid") int userid);

    //取消我的报价,设置状态为删除
    @Update("update quotes set isdelete=1 where demandcode=#{demandcode}")
    public void modifyIsdeleteByDemandcode(String demandcode);

    //根据需求编号获取所有对应的报价
    @Select("select * from quotes where demandcode=#{demandcode} and isdelete=0")
    public List<Quote> getQuoteByDemandcode(String demandcode);

    //根据报价id修改状态-已中标
    @Update("update quotes set status=#{status} where id=#{id}")
    public void modifyStatusByQuoteid(@Param("status") String status,@Param("id") int id);

    //我的客户---报价
    final String listAllSql="<where><if test='status!=null'> and q.status=#{status} </if>" +
            "<if test='currentUser.isAdmin!=true'> and q.traderphone=#{currentUser.phone} </if></where>";

    @Select("<script>select distinct(q.id),q.demandcode,q.lastupdatetime,q.supplyton,q.quote,q.status,d.dealername tradername from quotes q left join dealers d on q.traderphone=d.dealerphone " +listAllSql+" order by q.id limit #{pageSize} offset #{offset}</script>")
    public List<Quote> showAllList(@Param("currentUser") Admin admin,@Param("status") String status,@Param("pageSize")int pageSize,@Param("offset")int offset);

    @Select("<script>select count(distinct(q.id)) from quotes q left join dealers d on q.traderphone=d.dealerphone" +listAllSql+"</script>")
    public int showAllListCount(@Param("currentUser") Admin admin,@Param("status") String status);

    public default Pager<Quote> getAllQuotesList(Admin admin, String status,int page, int pagesize){
        return Pager.config(this.showAllListCount(admin,status), (int limit, int offset) -> this.showAllList(admin,status,limit,offset))
                .page(page, pagesize);
    }


    @Select("<script> select q.id,u.securephone username,q.lastupdatetime,q.supplyton, q.companyname, q.quote,q.status from quotes q left join  users u on q.userid=u.id where q.demandcode =#{demandcode} <if test='status!=null'> and q.status=#{status}</if>  order by q.id limit #{pageSize} offset #{offset}</script>")
    public List<Quote> findByDemandCode(@Param("demandcode") String demandcode,@Param("status") String status,@Param("pageSize")int pageSize,@Param("offset")int offset);

    @Select("<script> select count(1) from quotes q  where q.demandcode = #{demandcode} <if test='status!=null'> and q.status = #{status} </if> </script>")
    public int countByDemandCode(@Param("demandcode") String demandcode,@Param("status")String status);

    public default Pager<Quote> getQuoteByDemandCode(String demandcode,String status, int page, int pagesize){
        return Pager.config(this.countByDemandCode(demandcode,status), (int limit, int offset) -> this.findByDemandCode(demandcode,status,limit, offset))
                .page(page, pagesize);
    }





}
