package demo.core.persistence;

import demo.core.domain.GroupBuyOrder;
import demo.core.util.Pager;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zhangbolun on 15/1/19.
 */
public interface GroupBuyOrderMapper {
    @Select("select * from groupbuyorder where id=#{id} and isdelete=false")
    public GroupBuyOrder getOrderById(@Param("id")int id);

    @Select("select * from groupbuyorder where qualificationcode=#{qualificationcode} and isdelete=false")
    public GroupBuyOrder getOrderByQualifyCode(@Param("qualificationcode")String qualificationcode);

    @Select("select * from groupbuyorder where groupbuyordercode=#{groupbuyordercode} and isdelete=false")
    public GroupBuyOrder getOrderByCode(@Param("groupbuyordercode")String groupbuyordercode);

    @Select("select * from groupbuyorder where isdelete=false and status='ORDER_ACTIVE' GROUP BY createtime DESC limit 15")
    public List<GroupBuyOrder> getTransactionOrders();

    @Select("select * from groupbuyorder where groupbuysupplyid=#{groupbuysupplyid} and isdelete=false")
    public List<GroupBuyOrder> getOrdersBySupplyId(@Param("groupbuysupplyid")int groupbuysupplyid);

    @Select("select * from groupbuyorder where userid=#{userid} and isdelete=false and status=#{status} limit #{limit} offset #{offset}")
    public List<GroupBuyOrder> getOrdersByUserId(@Param("userid")int userid,
                                                 @Param("status")String status,
                                                 @Param("limit")int limit,
                                                 @Param("offset")int offset);

    @Select("select * from groupbuyorder where (status=#{status1} or status=#{status2}) and userid=#{userid} and isdelete=false limit #{limit} offset #{offset}")
    public List<GroupBuyOrder> getOrdersByStatuses(@Param("userid")int userid,
                                                   @Param("status1")String status1,
                                                   @Param("status2")String status2,
                                                   @Param("limit")int limit,
                                                   @Param("offset")int offset);

    @Select("select count(id) from groupbuyorder where userid=#{userid} and isdelete=false and status=#{status}")
    public int countOrdersByUserId(@Param("userid")int userid,
                                   @Param("status")String status);

    @Select("select count(id) from groupbuyorder where userid=#{userid} and isdelete=false and ( status=#{status1} or status=#{status2} )")
    public int countOrdersByStatuses(@Param("userid")int userid,
                                     @Param("status1")String status1,
                                     @Param("status2")String status2
                                    );

    @Update("update groupbuyorder set status=#{status} where id=#{id} and isdelete=false")
    public void updateStatusById(@Param("id")int id,@Param("status")String status);

    @Update("update groupbuyorder set status=#{status} where groupbuyordercode=#{groupbuyordercode} and isdelete=false")
    public void updateStatusByOrderCode(@Param("groupbuyordercode")String groupbuyordercode,@Param("status")String status);

    @Update("update groupbuyorder set status=#{status} where qualificationcode=#{qualificationcode} and isdelete=false")
    public void updateStatusByQualifyCode(@Param("qualificationcode")String qualificationcode,@Param("status")String status);

    @Update("update groupbuyorder set isdelete=true where groupbuyordercode=#{groupbuyordercode} and isdelete=false")
    public void deleteOrderByCode(@Param("groupbuyordercode")String groupbuyordercode);

    @Update("update groupbuyorder set isdelete=true where groupbuysupplycode=#{groupbuysupplycode} and qualificationcode=#{qualificationcode} and status='ORDER_CREATE' and isdelete=false")
    public void deleteOrder(@Param("groupbuysupplycode")String groupbuysupplycode,@Param("qualificationcode")String qualificationcode);

    @Update("update groupbuyorder set contractverify=true where groupbuyordercode=#{groupbuyordercode} and isdelete=false")
    public void verifyContract(@Param("groupbuyordercode")String groupbuyordercode);

    @Insert("insert into groupbuyorder(groupbuysupplyid, userid, qualificationcode,groupbuyordercode,status," +
            "volume,createtime,isdelete,contractverify,performancecode, groupbuysupplycode) values(#{groupbuysupplyid}," +
            "#{userid}, #{qualificationcode}, dateseq_next_value('TGDD'),#{status},#{volume},#{createtime},false,#{contractverify}," +
            "dateseq_next_value('LYS'), #{groupbuysupplycode})")
    @Options(useGeneratedKeys=true)
    public void addGroupBuyOrder(GroupBuyOrder groupBuyOrder);

    //后台page
    @Select("select count(*) from groupbuyorder where (status='ORDER_ACTIVE' or status='ORDER_FINISH' or status='ORDER_FAIL') and groupbuysupplyid=#{groupbuyid} and isdelete=0")
    public int countAllGroupBuyOrder(int groupbuyid);

    @Select("select * from groupbuyorder where (status='ORDER_ACTIVE' or status='ORDER_FINISH' or status='ORDER_FAIL') and groupbuysupplyid=#{groupbuyid} and isdelete=0 ORDER BY createtime limit #{limit} offset #{offset}")
    public List<GroupBuyOrder> listAllGroupBuyOrder(@Param("groupbuyid") int groupbuyid,@Param("limit") int limit, @Param("offset") int offset);

    public default Pager<GroupBuyOrder> pageAllGroupBuyOrder(int groupbuyid,int page, int pagesize){
        return Pager.config(this.countAllGroupBuyOrder(groupbuyid), (int limit, int offset) -> this.listAllGroupBuyOrder(groupbuyid, limit, offset))
                .page(page, pagesize);
    }


    //我的客户团购
    @Select("<script> select count(o.id) from groupbuyorder o left join groupbuysupply s on o.groupbuysupplyid= s.id  left join  companies c on  c.userid=o.userid <where> <if test='groupbuyOrder.status!=null'> and s.status=#{groupbuyOrder.status} </if></where> </script>")
    public int countOrderBydealerId(@Param("groupbuyOrder")GroupBuyOrder  groupbuyOrder);

    @Select("<script> select s.groupbuysupplycode,s.groupbuyprice,o.volume,o.userid,s.groupbuyenddate,o.qualificationcode ,o.performancecode,c.name companyname " +
            "from groupbuyorder o left join groupbuysupply s on o.groupbuysupplyid= s.id  left join  companies c on  c.userid=o.userid <where> <if test='groupbuyOrder.status!=null'> and s.status=#{groupbuyOrder.status} </if></where> limit #{limit} offset #{offset} </script>")
    public List<GroupBuyOrder> listOrderBydealerId(@Param("groupbuyOrder") GroupBuyOrder groupBuyOrder,@Param("limit") int limit, @Param("offset") int offset);

    public default Pager<GroupBuyOrder> pageAllGroupBuyOrder(GroupBuyOrder  groupbuyOrder,int page, int pagesize){
        return Pager.config(this.countOrderBydealerId(groupbuyOrder), (int limit, int offset) -> this.listOrderBydealerId(groupbuyOrder, limit, offset))
                .page(page, pagesize);
    }



}
