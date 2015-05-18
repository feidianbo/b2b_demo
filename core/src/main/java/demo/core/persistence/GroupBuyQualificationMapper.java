package demo.core.persistence;

import demo.core.domain.GroupBuyQualification;
import demo.core.util.Pager;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zhangbolun on 15/1/23.
 */
public interface GroupBuyQualificationMapper {
    @Select("select * from groupbuyqualification where id=#{id} and isdelete=false")
    public GroupBuyQualification getGroupBuyQualifyById(@Param("id")int id);

    @Select("select * from groupbuyqualification where qualificationcode=#{qualificationcode} and isdelete=false")
    public GroupBuyQualification getGroupBuyQualifyByCode(@Param("qualificationcode")String qualificationcode);

    @Select("select * from groupbuyqualification where userid=#{userid} and isdelete=false")
    public List<GroupBuyQualification> getGroupBuyQualifyByUserid(@Param("userid")int userid);

    @Select("select * from groupbuyqualification where userid =#{userid} and status=#{status} and isdelete=false ")
    public List<GroupBuyQualification> getGroupBuyQualifyByStatusId(@Param("userid")int userid,@Param("status")String status);

    @Select("select * from groupbuyqualification where userid =#{userid} and isdelete=false and status!='QUALIFY_START' ORDER BY status limit #{limit} offset #{offset}")
    public List<GroupBuyQualification> getGroupBuyQualifyByStatusIdPage(@Param("userid")int userid, @Param("limit")int limit, @Param("offset")int offset);

    @Select("select count(id) from groupbuyqualification where userid =#{userid} and isdelete=false")
    public int countGroupBuyQualifyBuStatusId(@Param("userid")int userid);

    @Insert("insert into groupbuyqualification(comment,userid, qualificationcode, status, groupbuyordercode,createtime,isdelete, marginscode) values(#{comment},#{userid}, dateseq_next_value('TGZZ'), #{status}, #{groupbuyordercode},now(),false, dateseq_next_value('TGHT'))")
    @Options(useGeneratedKeys=true)
    public void addGroupBuyQualify(GroupBuyQualification groupBuyQualification);

    @Update("update groupbuyqualification set margins=#{margins}, marginscode=#{marginscode}, photopath=#{photopath}, comment=#{comment}, qualificationcode=#{qualificationcode}, status=#{status}, createtime=#{createtime}, groupbuyordercode=#{groupbuyordercode} where id=#{id}")
    public void updateGroupBuyQualifyById(GroupBuyQualification groupBuyQualification);

    @Update("update groupbuyqualification set isdelete=true where qualificationcode=#{qualificationcode} and isdelete=false")
    public void deleteByCode(@Param("qualificationcode")String qualificationcode);

    @Update("update groupbuyqualification set status=#{status} where qualificationcode=#{qualificationcode} and isdelete=false")
    public void updateStatusByCode(@Param("qualificationcode")String qualificationcode,@Param("status")String status);

    @Update("update groupbuyqualification set status=#{status}, groupbuyordercode=#{groupbuyordercode} where id=#{id} and isdelete=false")
    public void bindOrderForQualify(@Param("id")int id,@Param("status")String status,@Param("groupbuyordercode")String groupbuyordercode);

    @Update("update groupbuyqualification set groupbuyordercode=#{groupbuyordercode} where qualificationcode=#{qualificationcode} and isdelete=false")
    public void addOrderByCode(@Param("qualificationcode")String qualificationcode,@Param("groupbuyordercode")String groupbuyordercode);

    @Update("update groupbuyqualification set groupbuyordercode='null' where qualificationcode=#{qualificationcode} and isdelete=false")
    public void deleteOrderByCode(@Param("qualificationcode")String qualificationcode);

    //删除团购资质凭证
    @Update("update groupbuyqualification set photopath=null where id=#{id}")
    public void deletePictureById(int id);

    @Select("<script> select * from groupbuyqualification <where><choose><when test='qualification.status!=null'> and status=#{qualification.status}</when><otherwise>and status!='QUALIFY_START'</otherwise></choose></where> limit #{limit} offset #{offset} </script>")
    public List<GroupBuyQualification> showAllGroupBuyQualification(@Param("qualification")GroupBuyQualification groupBuyQualification,@Param("limit") int limit,@Param("offset") int offset);

    @Select("<script> select count(1) from groupbuyqualification <where><choose><when test='qualification.status!=null'> and status=#{qualification.status}</when><otherwise>and status!='QUALIFY_START'</otherwise></choose></where> </script>")
    public int countQualification(@Param("qualification")GroupBuyQualification groupBuyQualification);

    public default Pager<GroupBuyQualification> listAllQualification(GroupBuyQualification groupBuyQualification,int page,int pageSize) {
            return Pager.config(this.countQualification(groupBuyQualification),(int limit,int offset)->this.showAllGroupBuyQualification(groupBuyQualification,limit,offset)).page(page,pageSize);
    }

    //
    @Select("select count(*) from groupbuyqualification where status=#{status} and isdelete=false")
         public int countGroupBuyQualify(@Param("status")String status);

    @Select("select * from groupbuyqualification where status=#{status} and isdelete=0 limit #{limit} offset #{offset}")
    public List<GroupBuyQualification> listGroupBuyQualifyByStatus(@Param("status")String status,@Param("limit") int limit, @Param("offset") int offset);

    public default Pager<GroupBuyQualification> pageGroupBuyQualifyByStatus(String status,int page, int pagesize){
        return Pager.config(this.countGroupBuyQualify(status), (int limit, int offset) -> this.listGroupBuyQualifyByStatus(status, limit, offset)).page(page, pagesize);
    }

    @Select("select count(*) from groupbuyqualification where status='QUALIFY_ACTIVE' or status='QUALIFY_INPROCESS' and isdelete=false")
    public int countGroupBuyQualifyPass();

    @Select("select * from groupbuyqualification where status='QUALIFY_ACTIVE' or status='QUALIFY_INPROCESS' and isdelete=0 limit #{limit} offset #{offset}")
    public List<GroupBuyQualification> listGroupBuyQualifyPass(@Param("limit") int limit, @Param("offset") int offset);

    public default Pager<GroupBuyQualification> pageGroupBuyQualifyPass(int page, int pagesize){
        return Pager.config(this.countGroupBuyQualifyPass(), (int limit, int offset) -> this.listGroupBuyQualifyPass(limit, offset)).page(page, pagesize);
    }

    @Select("select count(*) from groupbuyqualification where userid=#{userid} and status=#{status} and isdelete=false")
    public int countQualifyStatusId(@Param("status")String status,@Param("userid")int userid);

    @Select("select * from groupbuyqualification where userid=#{userid} and status=#{status} and isdelete=0 limit #{limit} offset #{offset}")
    public List<GroupBuyQualification> listGroupBuyQualifyByStatusId(@Param("status")String status,@Param("userid")int userid, @Param("limit") int limit, @Param("offset") int offset);

    public default Pager<GroupBuyQualification> pageGroupBuyQualifyByStatusId(String status,int userid,int page, int pagesize){
        return Pager.config(this.countQualifyStatusId(status, userid), (int limit, int offset) -> this.listGroupBuyQualifyByStatusId(status,userid,limit, offset)).page(page, pagesize);
    }

    //修改团购资质状态
    @Update("update groupbuyqualification set status=#{status} where id=#{id}")
    public void setGroupBuyQualificationStatus(@Param("status")String status,
                                               @Param("id")int id);


}
