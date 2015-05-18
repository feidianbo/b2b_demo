package demo.core.persistence;

import demo.core.domain.Mydemand;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by fanjun on 14-12-24.
 */
public interface MydemandMapper {

    //添加我的需求
    @Insert("insert into mydemands(userid,demandcode,releasetime,quoteenddate,demandamount,status," +
            "deliverydate,deliverydatestart,deliverydateend) values(" +
            "#{userid},#{demandcode},#{releasetime},#{quoteenddate},#{demandamount},#{status}," +
            "#{deliverydate},#{deliverydatestart},#{deliverydateend})")
    public void addMydemand(Mydemand mydemand);

    //修改我的需求
    @Update("update mydemands set releasetime=#{releasetime},quoteenddate=#{quoteenddate},demandamount=#{demandamount},status=#{status}," +
            "deliverydate=#{deliverydate},deliverydatestart=#{deliverydatestart},deliverydateend=#{deliverydateend}" +
            " where demandcode=#{demandcode}")
    public void modifyMyDemand(Mydemand mydemand);

    //获取我的发布的需求
    @Select("select * from mydemands where userid=#{userid} order by releasetime desc limit #{startNum},#{pageSize}")
    public List<Mydemand> getTurnpageListWithUserid(@Param("userid") int userid,@Param("startNum") int startNum,@Param("pageSize") int pageSize);

    //统计我的需求总数
    @Select("select count(*) from mydemands where userid=#{userid}")
    public int countAllMydemands(int userid);

    //删除我的需求
    @Delete("delete from mydemands where demandcode=#{demandcode} and userid=#{userid}")
    public void deleteMyDemandByDemandcode(@Param("demandcode") String demandcode,@Param("userid") int userid);

    /*//修改我的需求状态-交易结束
    @Update("update mydemands set status='交易结束' where status='匹配中' and (date_add(deliverydate,interval 1 day)=date_format(now(),'%Y-%m-%d') or date_add(deliverydateend,interval 1 day)=date_format(now(),'%Y-%m-%d'))")
    public void modifyStatusToTradeOverTask();*/

    //将我的需求表的状态
    @Update("update mydemands set status=#{status} where demandcode=#{demandcode}")
    public void modifyStatusByDemandcode(@Param("status") String status,@Param("demandcode") String demandcode);

    //将我的需求表的状态
    @Update("update mydemands set status=#{status} where demandcode=#{demandcode} and userid=#{userid}")
    public void modifyStatusByDemandcodeAndUserid(@Param("status") String status,@Param("demandcode") String demandcode,@Param("userid") int userid);

    //报价成功需求表报价人数加1
    @Update("update mydemands set quotenum=quotenum+1 where demandcode=#{demandcode}")
    public void plusQuotenum(String demandcode);

    //删除报价,我的需求表的报价人数减1
    @Update("update mydemands set quotenum=quotenum-1 where demandcode=#{demandcode}")
    public void minusQuotenum(String demandcode);

    //根据需求编号获取我的需求
    @Select("select * from mydemands where demandcode=#{demandcode}")
    public Mydemand getMydemandByDemandcode(String demandcode);

    //修改已匹配瓶数
    @Update("update mydemands set purchasednum=#{purchasednum}+purchasednum where demandcode=#{demandcode}")
    public void modifyPurchaseNumByDemandcode(@Param("purchasednum") int purchasednum,@Param("demandcode") String demandcode);

/*    //过了报价截止时间后,交易状态改为匹配中
    @Update("update mydemands set status='匹配中' where date_add(quoteenddate,interval 1 day) = date_format(now(),'%Y-%m-%d')")
    public void modifyStatusToMatchingTask();*/

}
