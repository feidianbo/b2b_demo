package demo.core.persistence;

import demo.core.domain.ManualSell;
import demo.core.util.Pager;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by xiangyang on 14-12-17.
 */
public interface ManualSellMapper {

    @Insert("insert into manualsell (userId,lowcalorificvalue,deliverystartdate,deliveryenddate,demandamount,deliverydistrict,deliveryprovince,deliveryaddr,deliverymode,contactName,phone,companyName,type,deliveryotherplace,receivebasissulfur,airdrybasisvolatile,coalType,createdatetime,manualsellid)" +
            "values (#{userId},#{lowcalorificvalue},#{deliveryStartDate},#{deliveryEndDate},#{demandAmount},#{deliveryDistrict},#{deliveryProvince},#{deliveryAddr},#{deliveryMode},#{contactName},#{phone},#{companyName},#{type},#{deliveryOtherPlace},#{receivebasissulfur},#{airdrybasisvolatile},#{coalType},#{createDatetime},case when #{type} = 1 then dateseq_next_value('ZH') else dateseq_next_value('XS')end)")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public int save(ManualSell sell);

    final String sql = "<where>" +
            "<if test='userId!=null'> userId = #{userId}</if>" +
            "<if test='manualsellType!=null'> and type = #{manualsellType}</if>" +
            "<if test='port!=null'> and deliveryaddr = #{port}</if>"+
            "<if test='dateCondition!=null'> and createdatetime between  #{dateCondition} and  DATE_FORMAT(now(),'%Y-%m-%d')</if>" +
            "</where>";

    @Select("<script> select * from manualsell " + sql + "order by id limit #{pageSize} offset #{indexNum} </script>")
    public List<ManualSell> list(@Param("userId") Integer userId, @Param("manualsellType") Boolean manualsellType, @Param("dateCondition") LocalDate dateCondition, @Param("pageSize") int pageSize, @Param("indexNum") int indexNum, @Param("port") String port);

    @Select("<script>select count(1) from manualsell"+sql+"</script>")
    public int count(@Param("userId") Integer userId, @Param("manualsellType") boolean type, @Param("dateCondition") LocalDate dateCondition, @Param("port") String port);


    @Delete("delete from manualsell where id=#{id}")
    public void deleteById(int id);

    @Select("select * from manualsell where  userid=#{userId} and manualsellid =#{manualsellId}")
    public ManualSell loadByUserIdandManualId(@Param("userId") int userId, @Param("manualsellId") String manualsellId);
    @Select("select * from manualsell where  manualsellid =#{manualsellId}")
    public ManualSell loadByManualId(@Param("manualsellId") String manualsellId);

    /*********************admin后台*****************************/

    final String dynamicSql = "<where><if test='manual!=null'>" +
            "<if test='manual.userId!=null and manual.userId!=0'> userId = #{manual.userId}</if>" +
            "<if test='manual.type!=null'> and type = #{manual.type}</if>" +
            "<if test=\"manual.deliveryDistrict!=null and manual.deliveryDistrict!=\'\'\"> and deliverydistrict = #{manual.deliveryDistrict}</if>"+
            "<if test=\"manual.deliveryProvince!=null and  manual.deliveryProvince!=\'\'\"> and deliveryprovince = #{manual.deliveryProvince}</if>"+
            "<if test=\"manual.deliveryAddr!=null and manual.deliveryAddr!=\'\'\"> and deliveryaddr = #{manual.deliveryAddr}</if>"+
            "<if test='manual.deliveryStartDate!=null and manual.deliveryEndDate!=null'> and createdatetime between  #{manual.deliveryStartDate} and  #{manual.deliveryEndDate}</if>" +
            "<if test='manual.deliveryStartDate!=null and manual.deliveryEndDate==null'> and createdatetime between  #{manual.deliveryStartDate} and  DATE_FORMAT(now(),'%Y-%m-%d')</if>" +
            "</if></where>";
    @Select("<script> select * from manualsell " + dynamicSql + "order by id limit #{pageSize} offset #{indexNum} </script>")
    public List<ManualSell> listAllManualsell(@Param("manual") ManualSell manualSell, @Param("pageSize") int pageSize, @Param("indexNum") int indexNum);

    @Select("<script>select count(1) from manualsell"+dynamicSql+"</script>")
    public int countAllManualsell(@Param("manual") ManualSell manualSell);


    public default Pager<ManualSell> getAllManualSellList(ManualSell manualSell, int page, int pagesize){
        return Pager.config(this.countAllManualsell(manualSell), (int limit, int offset) -> this.listAllManualsell(manualSell,limit, offset))
                .page(page, pagesize);
    }
}
