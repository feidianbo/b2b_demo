package demo.core.persistence;

import demo.core.domain.Dealer;
import demo.core.domain.DealerBackup;
import demo.core.util.Pager;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by jack on 15/1/9.
 */
public interface DealerMapper{
    String dealerSelectSQL1 =
                    "<if test='deliveryregion!=null'> deliveryregion=#{deliveryregion}</if>" +
                    "<if test='deliveryprovince!=null'> and deliveryprovince=#{deliveryprovince}</if>" +
                    "<if test='deliveryplace!=null'> and deliveryplace=#{deliveryplace}</if>";
    String startWhere = " <where>";
    String endWhere = " </where>";
    String statusNotDelete = " and status != '已删除'";

    final String limitOffset = " limit #{limit} offset #{offset}";

    //交易员列表
    @Select("select count(*) from dealers where status != '已删除'")
    public int countAllDealers();

    @Select("select * from dealers where status != '已删除' order by status" + limitOffset)
    public List<Dealer> listAllDealers(@Param("limit") int limit, @Param("offset") int offset);

    public default Pager<Dealer> getAllDealersList(int page, int pagesize){
        return Pager.config(this.countAllDealers(), (int limit, int offset) -> this.listAllDealers(limit, offset))
                .page(page, pagesize);
    }

    //查询交易员-
    @Select("<script>" +
            "select count(*) from dealers " +
            startWhere +  dealerSelectSQL1 + statusNotDelete + endWhere +
            "</script>")
    public int countSelectDealers(@Param("deliveryregion")String deliveryregion,
                                  @Param("deliveryprovince")String deliveryprovince,
                                  @Param("deliveryplace")String deliveryplace);
    @Select("<script>" +
            "select * from dealers " +
            startWhere + dealerSelectSQL1 + statusNotDelete + endWhere + limitOffset +
            "</script>")

    public List<Dealer> listSelectDealers(@Param("deliveryregion")String deliveryregion,
                                          @Param("deliveryprovince")String deliveryprovince,
                                          @Param("deliveryplace")String deliveryplace,
                                          @Param("limit")int limit,
                                          @Param("offset")int offset);

    public default Pager<Dealer> getDealersBySelect(String deliveryregion, String deliveryprovince, String deliveryplace, int page, int pagesize){
        return Pager.config(this.countSelectDealers(deliveryregion, deliveryprovince, deliveryplace), (int limit, int offset) -> this.listSelectDealers(deliveryregion, deliveryprovince, deliveryplace, limit, offset))
                .page(page, pagesize);
    }

    @Select("select * from dealers where id=#{id}")
    public Dealer getDealerById(int id);

    @Select("select * from dealers where dealerid=#{dealerid} and status='在职'")
    public List<Dealer> getDealerByDealerId(String dealerid);

    @Select("select * from dealers where deliveryplace=#{deliveryplace} and status='在职'")
    public List<Dealer> getDealerByDeliveryplace(String deliveryplace);

    @Select("select * from dealers where dealername='XX网' ")
    public List<Dealer> getYMWKFDealer();

    @Select("select * from dealers where deliveryprovince=#{province} and deliveryplace=#{place} and status='在职'")
    public List<Dealer> getDealerByProvincePlace(@Param("province")String deliveryprovince,
                                                 @Param("place")String deliveryplace);

    @Select("select * from dealers where deliveryprovince=#{deliveryprovince} and status='在职'")
    public List<Dealer> getDealerByDeliveryprovince(String deliveryprovince);

    @Select("select * from dealers where deliveryregion=#{deliveryregion} and status='在职'")
    public List<Dealer> getDealerByDeliveryregion(String deliveryregion);

    //添加交易员
    @Insert("insert into dealers(dealerid, dealername, dealerphone, deliveryregion, deliveryprovince, " +
            "deliveryplace, lastupdatemanid, lastupdatemanname, createtime) values(#{dealerid}, " +
            "#{dealername}, #{dealerphone}, #{deliveryregion}, #{deliveryprovince}, #{deliveryplace}," +
            " #{lastupdatemanid}, #{lastupdatemanname}, now())")
    @Options(useGeneratedKeys=true)
    public void addDealerPart(Dealer dealer);
    @Update("update dealers set dealerid=#{dealerid} where id=#{id}")
    public void setDealeridById(@Param("dealerid")String dealerid, @Param("id")int id);

    public default void addDealer(Dealer dealer){
        this.addDealerPart(dealer);
        dealer.setDealerid("YMW"+dealer.getId());
        this.setDealeridById(dealer.getDealerid(), dealer.getId());
    }

    //更新交易员
    @Update("update dealers set dealerid=#{dealerid}, dealername=#{dealername}, dealerphone=#{dealerphone}," +
            "lastupdatemanid=#{lastupdatemanid}, lastupdatemanname=#{lastupdatemanname}" +
            "where deliveryregion=#{deliveryregion} and deliveryprovince=#{deliveryprovince}" +
            "and deliveryplace=#{deliveryplace}")
    public void updateDealer(Dealer dealer);

    //交易员备份信息
    @Insert("insert into dealerbackup(dealerid, dealername, dealerphone, deliveryregion, deliveryprovince, " +
            "deliveryplace, lastupdatemanid, lastupdatemanname, iswork, createtime) values(#{dealerid}, " +
            "#{dealername}, #{dealerphone}, #{deliveryregion}, #{deliveryprovince}, #{deliveryplace}, " +
            "#{lastupdatemanid}, #{lastupdatemanname}, #{iswork}, now())")
    public void addDealerBackup(DealerBackup dealerBackup);


    //通过区域，省市，港口，姓名，电话判断 此港口是否存在此交易员
    @Select("select * from dealers where deliveryprovince=#{deliveryprovince} and deliveryplace=#{deliveryplace} and dealerid=#{dealerid}")
    public Dealer getDealerByProvincePlaceDealerid(@Param("deliveryprovince")String deliveryprovince,
                                                   @Param("deliveryplace")String deliveryplace,
                                                   @Param("dealerid")String dealerid);

    //禁用交易员
    @Update("update dealers set status=#{status} where id=#{id}")
    public void setDealerStatusById(@Param("status")String status, @Param("id")int id);

    //更改交易员手机号
    @Update("update dealers set dealerphone=#{dealerphone} where id=#{id}")
    public void updateDealerPhoneById(@Param("dealerphone")String dealerphone,
                                      @Param("id")int id);

    //按照区域，省市，港口 查找交易员
    @Select("select * from dealers where deliveryregion=#{deliveryregion} and " +
            "deliveryprovince=#{deliveryprovince} and deliveryplace=#{deliveryplace}")
    public List<Dealer> getDealerByRegionProvincePlace(@Param("deliveryregion")String deliveryregion,
                                                       @Param("deliveryprovince")String deliveryprovince,
                                                       @Param("deliveryplace")String deliveryplace);



    /*****************************author zxy*****************************/

    @Insert("insert into dealers (dealername,createtime,dealerphone) values (#{dealername},#{createtime},#{dealerphone})")
    @Options(useGeneratedKeys=true,keyProperty = "id")
    public void saveDealer(Dealer dealer);

    @Select("select count(1) from dealers where dealerphone=#{value}")
    public int isExistPhone(String phone);

    @Select("<script>select count(1) from dealers d  <if test='portId!=0'>where id in (select dealerid from dealerport where portid=#{portId})</if></script>")
    public int countDealer(@Param("portId")int portId);

    @Select("<script>select d.id,d.dealername,d.createtime,d.dealerphone,d.status from dealers d <if test='portId!=0'>where id in (select dealerid from dealerport where portid=#{portId})</if> order by d.createtime  desc limit #{limit} offset #{offset}</script>")
    @Results(value= {@Result(property = "id", column = "id"),
            @Result(property = "dealername", column = "dealername"),
            @Result(property = "createtime", column = "createtime"),
            @Result(property = "dealerphone", column = "dealerphone"),
            @Result(property = "status", column = "status"),
            @Result(property = "ports",column = "id",javaType = List.class,many = @Many(select = "kitt.core.persistence.AreaportMapper.findPortByDealerId"))
    })
    public List<Dealer> findDealers(@Param("portId")int portId,@Param("limit") int limit, @Param("offset") int offset);


    public default Pager<Dealer> pageAllDealer(int portId,int page, int pagesize){
        return Pager.config(this.countDealer(portId), (int limit, int offset) -> this.findDealers(portId,limit, offset))
                .page(page, pagesize);
    }

    //增加交易员所管理的港口
    @Insert("<script>insert into dealerport (dealerid,portId) <foreach collection=\'ports\' index=\'i\' item=\'port\' separator=\'union all\'>select #{id},#{port.id} from dual</foreach></script>")
    public void addDealerPort(Dealer dealer);


    @Insert("<script>insert into dealerport (dealerid,portId) <foreach collection=\'dealerIds\' index=\'i\' item=\'dealerId\' separator=\'union all\'>select #{dealerId},#{portId} from dual</foreach></script>")
    public void addPortInDealer(@Param("portId")int portId,@Param("dealerIds")List<Integer>dealerIds);


    @Insert("<script>insert into dealerport (dealerid,portId) <foreach collection=\'portIds\' index=\'i\' item=\'portId\' separator=\'union all\'>select #{dealerId},#{portId} from dual</foreach></script>")
    public void addDealerInPort(@Param("dealerId")int dealerId,@Param("portIds")List<Integer>portIds);

    //删除港口下的交易员
    @Delete("<script>delete from dealerport where portId=#{portId} and dealerId in  <foreach collection='dealerIds' index='i' open=\'(\' separator=\',\' close=\')\' item='dealerId'>#{dealerId}</foreach></script>")
    public void deleteDealerPort(@Param("portId")int portId,@Param("dealerIds")List<Integer>dealerIds);


    //删除交易员下的港口
    @Delete("<script>delete from dealerport where dealerId=#{dealerId} and portid in  <foreach collection='portIds' index='i' open=\'(\' separator=\',\' close=\')\' item='portId'>#{portId}</foreach></script>")
    public void deleteDealerInPort(@Param("dealerId")int dealerId,@Param("portIds")List<Integer>dealerIds);

    //查看该港口的所有交易员
    @Select("select d.id,d.dealername,d.dealerphone from dealers d where exists (select dealerid from dealerport dp  where d.id=dp.dealerid and dp.portid=#{value}) and d.status='在职'")
    public List<Dealer> findAllDealerByPortId(int portId);

    //查看该港口的所有交易员id
    @Select("select d.id from dealers d, dealerport dp  where d.id=dp.dealerid and dp.portid=#{value}")
    public List<Integer> findAllDealerIdByPortId(int portId);

    //查看不是该港口的所有交易员
    @Select("select id,dealername,dealerphone from dealers d  where not exists (select dealerid from dealerport dp  where d.id=dp.dealerid and dp.portid=#{value}) and d.status='在职'")
    public List<Dealer> findAllDealer(int portId);

    @Select("select id,dealername,dealerphone from dealers where id=#{value}")
    public Dealer findDealerById(int id);

    //根据省份id找交易员
    @Select("select d.id,d.dealername,d.dealerphone from dealers d, dealerport dp where d.id=dp.dealerid and  dp.portid in (select id from areaports  where parentid = #{value}) group by d.id")
    public List<Dealer> findDealerByProvinceId(int provinceId);

    //查找区域下的所有交易员
    @Select("select d.id,d.dealername,d.dealerphone from dealers d, dealerport dp where d.id=dp.dealerid and  dp.portid in " +
            "(" +
            "select id from areaports  where parentid in(" +
            "select id from areaports where  parentid=(select parentid from areaports where id=111)" +
            ")" +
            ") group by d.id")
    public List<Dealer> findRegionAllDealer(int provinceId);
    //找系统内所有的交易员
    @Select("select id,dealername,dealerphone from dealers")
    public  List<Dealer> findyiMeiAllDealer();

    @Select("select dp.portId from dealers d ,dealerport dp where d.id=dp.dealerid  and dp.dealerid=#{value}  group by dp.portId")
    public List<Integer> findPortIdByDealerId(int dealerId);



}






