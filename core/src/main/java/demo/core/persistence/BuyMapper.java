package demo.core.persistence;

import demo.core.domain.*;
import demo.core.util.Pager;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jack on 14/11/25.
 */
public interface BuyMapper {
    final String SellInfo1=
            "<if test='NCV01!=null'> NCV &gt;= #{NCV01}</if>" +
                    "<if test='NCV02!=null'> and NCV &lt;= #{NCV02}</if>" +
                    "<if test='RS01!=null'> and RS &gt;= #{RS01}</if>" +
                    "<if test='RS02!=null'> and RS &lt;= #{RS02}</if>" +
                    "<if test='regionId!=0'> and regionId=#{regionId}</if>" +
                    "<if test='provinceId!=0'> and provinceId=#{provinceId}</if>" +
                    "<if test='portId!=0'> and portId=#{portId}</if>" +
                    "<if test='portId==999'> and otherharbour is not null</if>" +
                    "<if test='seller!=null'> and seller=#{seller}</if>" +
                    "<if test='coaltype!=null'> and pname=#{coaltype}</if>";
    final String SellInfoOneStatus = "<if test='status!=null'> and status=#{status}</if>";
    final String SellInfoTwoStatus = "<if test='status1!=null'> and (status=#{status1}</if>" +
            "<if test='status2!=null'> or status=#{status2}</if>)";
    final String ourSeller = " and seller='自营'";
    final String otherSeller = " and seller!='自营'";
    final String unionAll = " union ";
    final String limitOffset = " limit #{limit} offset #{offset}";
    final String startWhere = "<where>";
    final String endWhere = "</where>";
    final String OrderBy = "order by " +
            "<if test='sorttype==0'> createdate desc ,</if>" +
            "<if test='sorttype==2'> " +
            "<if test='sequence==0'>ykj+jtjlast desc ,</if>" +
            "<if test='sequence==1'>ykj+jtjlast asc ,</if>" +
            "</if>" +
            "<if test='sorttype==1'>" +
            "<if test='sequence==0'> soldquantity desc ,</if>" +
            "<if test='sequence==1'> soldquantity asc ,</if>" +
            "</if>" +
            "seller desc ";
    //查询供应信息
    String SellInfoSelectSQL1 =
            "<if test='regionId!=0'> regionId=#{regionId} </if>" +
                    "<if test='provinceId!=0'> and provinceId=#{provinceId} </if>" +
                    "<if test='portId!=0'>  and portId=#{portId} </if>";
    String productNOSQL = "<if test='productNo!=null'> and pid like #{productNo} </if>";
    String SellInfoStatus =
            "<if test='status!=null'>  and status=#{status}</if>";
    String SellerControl = "<if test='sellertype == 0'> and seller='自营'</if>" +
            "<if test='sellertype != 0'> and seller!='自营'</if>";

    //1，供应信息,我要买列表
    @Select("<script> select * from sellinfo " +
            startWhere + SellInfo1 + SellInfoOneStatus + endWhere +
            OrderBy +
            limitOffset +
            "</script>")
    public List<SellInfo> getSellInfoList(@Param("NCV01") Integer NCV01,
                                          @Param("NCV02") Integer NCV02,
                                          @Param("RS01") BigDecimal RS01,
                                          @Param("RS02") BigDecimal RS02,
                                          @Param("regionId") int regionId,
                                          @Param("provinceId") int provinceId,
                                          @Param("portId") int portId,
                                          @Param("seller") String seller,
                                          @Param("coaltype")String coaltype,
                                          @Param("status") EnumSellInfo status,
                                          @Param("sorttype")int sorttype,
                                          @Param("sequence")int sequence,
                                          @Param("limit")int limit,
                                          @Param("offset")int offset);

    @Select("<script>select count(*) from sellinfo " + startWhere + SellInfo1 + SellInfoOneStatus + endWhere + " </script>")
    public int getSellInfoCount(@Param("NCV01") Integer NCV01,
                                @Param("NCV02") Integer NCV02,
                                @Param("RS01") BigDecimal RS01,
                                @Param("RS02") BigDecimal RS02,
                                @Param("regionId") int regionId,
                                @Param("provinceId") int provinceId,
                                @Param("portId") int portId,
                                @Param("seller") String seller,
                                @Param("coaltype")String coaltype,
                                @Param("status") EnumSellInfo status);

    //查询所有状态审核通过的供求信息
    @Select("select * from sellinfo where status=#{status} order by seller desc")
    public List<SellInfo> getAllSellInfo(EnumSellInfo status);

    //查询供应信息 userid
    @Select("select * from sellinfo where sellerid=#{sellerid} and status='WaitVerify' and seller != '自营'" +
            unionAll +
            "select * from sellinfo where sellerid=#{sellerid} and status='VerifyNotPass' and seller != '自营'" +
            unionAll +
            "select * from sellinfo where sellerid=#{sellerid} and status='VerifyPass' and seller != '自营'" +
            unionAll +
            "select * from sellinfo where sellerid=#{sellerid} and status='Canceled' and seller != '自营'" +
            limitOffset)
    public List<SellInfo> getSellinfoByUserid(@Param("sellerid")int sellerid, @Param("limit")int limit, @Param("offset")int offset);

    //查询供应信息的总条数 userid
    @Select("select count(*) from sellinfo where sellerid=#{sellerid} and (status='VerifyPass' or status='WaitVerify' or status='VerifyNotPass') and seller!='自营'")
    public int getSellinfoCountByUserid(int sellerid);

    //通过id查询供需信息
    @Select("select * from sellinfo where id=#{id}")
    public SellInfo getSellInfoById(int id);

    //供求信息  修改可用瓶数
    @Update("update sellinfo set availquantity=#{availquantity}, soldquantity=#{soldquantity} where id=#{id}")
    public void setAvailSoldAmount(@Param("availquantity") int availquantity,
                                   @Param("soldquantity") int soldquantity,
                                   @Param("id") int id);

    //改变供应信息状态
    @Update("update sellinfo set status=#{status}, remarks=#{remarks} where id=#{id}")
    public int updateSellInfoStatus(@Param("status")EnumSellInfo status,
                                    @Param("remarks")String remarks,
                                    @Param("id")int id);

    //设置sellinfo表中的阶梯价，最低价
    @Update("update sellinfo set jtjlast=#{jtjlast} where id=#{id}")
    public void setJtjLastById(@Param("jtjlast")int jtjlast, @Param("id")int id);

    //取消供应信息
    @Update("update sellinfo set status='Canceled' where id=#{id} and status !='SellOut'")
    public void cancelSellInfoById(int id);

    //发布供应信息
    @Insert("insert into sellinfo(pid,status, pname, NCV, ADS, RS, TM, IM, ADV, " +
            "RV, ASH, AFT, HGI, ykj, seller, deliveryregion, deliveryprovince, deliveryplace, " +
            "otherharbour, deliverymode, deliverytime1, deliverytime2, supplyquantity, availquantity, " +
            "inspectorg, otherinspectorg, createtime, sellerid," +
            "producttype, createdate,regionId,provinceId,portId,originplace,paymode," +
            "payperiod,releaseremarks,parentid,editnum,traderid," +
            "dealername,dealerphone, linktype, linkmanname, linkmanphone) values(dateseq_next_value('CP'), " +
            "#{status}, #{pname}, #{NCV}, #{ADS}, #{RS}, #{TM}, #{IM}, #{ADV}, " +
            "#{RV}, #{ASH}, #{AFT}, #{HGI}, #{ykj}, #{seller}, #{deliveryregion}, #{deliveryprovince}," +
            " #{deliveryplace}, #{otherharbour}, #{deliverymode}, #{deliverytime1}, #{deliverytime2}, " +
            "#{supplyquantity}, #{availquantity}, #{inspectorg}, #{otherinspectorg}, #{createtime}, " +
            "#{sellerid}, #{producttype}, curdate(),#{regionId},#{provinceId},#{portId}," +
            "#{originplace},#{paymode},#{payperiod},#{releaseremarks},#{parentid}," +
            "#{editnum},#{traderid},#{dealername},#{dealerphone}," +
            "#{linktype}, #{linkmanname}, #{linkmanphone})")
    @Options(useGeneratedKeys=true)
    public void addSellinfo(SellInfo sellInfo);


    //修改增加供应信息，产品编号不变
    @Insert("insert into sellinfo(pid,status, pname, NCV, ADS, RS, TM, IM, ADV, " +
            "RV, ASH, AFT, HGI, ykj, seller, deliveryregion, deliveryprovince, deliveryplace, " +
            "otherharbour, deliverymode, deliverytime1, deliverytime2, supplyquantity, " +
            "availquantity, soldquantity, inspectorg, otherinspectorg, createtime, sellerid, " +
            "producttype, createdate,regionId,provinceId,portId,originplace,paymode," +
            "payperiod,releaseremarks,parentid,editnum, linktype," +
            "linkmanname, linkmanphone, remarks, traderid, dealername, dealerphone," +
            "viewtimes) values(#{pid}, #{status}, #{pname}, #{NCV}, #{ADS}, #{RS}, " +
            "#{TM}, #{IM}, #{ADV},  #{RV}, #{ASH}, #{AFT}, #{HGI}, #{ykj}, #{seller}, " +
            "#{deliveryregion}, #{deliveryprovince}, #{deliveryplace}, #{otherharbour}, " +
            "#{deliverymode}, #{deliverytime1}, #{deliverytime2}, #{supplyquantity}, " +
            "#{availquantity}, #{soldquantity}, #{inspectorg}, #{otherinspectorg}, #{createtime}, " +
            "#{sellerid}, #{producttype}, curdate(),#{regionId},#{provinceId},#{portId}," +
            "#{originplace},#{paymode},#{payperiod},#{releaseremarks},#{parentid},#{editnum}, " +
            "#{linktype}, #{linkmanname}, #{linkmanphone}, #{remarks}, #{traderid}," +
            "#{dealername}, #{dealerphone}, #{viewtimes})")
    @Options(useGeneratedKeys=true)
    public void addSellinfoForUpdate(SellInfo sellInfo);

    //更新供应信息ykj
    @Update("update sellinfo set pname=#{pname}, status=#{status}, NCV=#{NCV}, RS=#{RS}, ADS=#{ADS}, " +
            "TM=#{TM},  IM=#{IM}, ADV=#{ADV}, RV=#{RV}, " +
            "ASH=#{ASH}, AFT=#{AFT}, HGI=#{HGI}, ykj=#{ykj}, seller=#{seller}, " +
            "deliveryregion=#{deliveryregion}, deliveryprovince=#{deliveryprovince}," +
            " deliveryplace=#{deliveryplace}, otherharbour=#{otherharbour}, deliverymode=#{deliverymode}," +
            "deliverytime1=#{deliverytime1}, deliverytime2=#{deliverytime2}, supplyquantity=#{supplyquantity}, " +
            "availquantity=#{availquantity}, inspectorg=#{inspectorg}, otherinspectorg=#{otherinspectorg}," +
            " createtime=#{createtime}, producttype=#{producttype}," +
            "originplace=#{originplace}, paymode=#{paymode},payperiod=#{payperiod},releaseremarks=#{releaseremarks}," +
            "linktype=#{linktype}, linkmanname=#{linkmanname}, linkmanphone=#{linkmanphone},version=version+1 where id=#{id} and version=#{version}")
    public int updateSellinfo(SellInfo sellInfo);

    @Update("update sellinfo set status=#{status}, version=version+1 where id=#{id} and version=#{version}")
    public int setSellinfoStatus(@Param("status")EnumSellInfo status,
                                 @Param("id")int id,
                                 @Param("version")int version);

    @Update("update sellinfo set status=#{status} where id=#{id} and status!='OutOfStack'")
    public int putOffMallProduct(@Param("status")EnumSellInfo status, @Param("id")int id);

    @Update("update sellinfo set producttype=#{producttype} where id=#{id}")
    public int setRecommentStatus(@Param("producttype")String producttype, @Param("id")int id);

    @Update("update sellinfo set status=#{status}, remarks=#{remarks}, " +
            "dealerphone=#{dealerphone},dealername=#{dealername}, " +
            "verifytime=#{verifytime},traderid=#{traderId}, version=version+1 " +
            "where id=#{id} and version=#{version}")
    public int verifySellinfoStatus(@Param("dealerphone") String phone,
                                    @Param("dealername")String dealername,
                                    @Param("status")EnumSellInfo status,
                                    @Param("remarks")String remarks,
                                    @Param("verifytime")LocalDateTime verifytime,
                                    @Param("id")int id,
                                    @Param("version")int version,
                                    @Param("traderId")Integer traderId);

    //删除供应信息
    @Delete("delete from sellinfo where id=#{id}")
    public void deleteSellinfo(int id);

    //admin供应信息审核列表
    @Select("select count(*) from sellinfo where status=#{status} and seller != '自营'")
    public int countAllSellInfo(@Param("status")EnumSellInfo status);

    @Select("select * from sellinfo where status=#{status} and seller != '自营'"+ limitOffset)
    public List<SellInfo> listAllSellInfo(@Param("status") EnumSellInfo status, @Param("limit") int limit, @Param("offset") int offset);

    public default Pager<SellInfo> pageAllSellinfo(EnumSellInfo status, int page, int pagesize){
        return Pager.config(this.countAllSellInfo(status), (int limit, int offset) -> this.listAllSellInfo(status, limit, offset))
                .page(page, pagesize);
    }

    //产品-查询列表
    @Select("<script>" +
            "select count(*) from sellinfo " +
            startWhere + SellInfoSelectSQL1 + productNOSQL + SellInfoTwoStatus + SellerControl + endWhere +
            "</script>")
    public int countSellInfoSelect(@Param("regionId")int regionId,
                                   @Param("provinceId")int provinceId,
                                   @Param("portId")int portId,
                                   @Param("productNo")String productNo,
                                   @Param("status1")EnumSellInfo status1,
                                   @Param("status2")EnumSellInfo status2,
                                   @Param("sellertype")int sellertype);
    @Select("<script>" +
            "select * from sellinfo" +
            startWhere + SellInfoSelectSQL1 + productNOSQL + SellInfoTwoStatus + SellerControl + endWhere + "order by createtime" + limitOffset +
            "</script>")
    public List<SellInfo> listSellInfoSelect(@Param("regionId")int regionId,
                                             @Param("provinceId")int provinceId,
                                             @Param("portId")int portId,
                                             @Param("productNo")String productNo,
                                             @Param("status1")EnumSellInfo status1,
                                             @Param("status2")EnumSellInfo status2,
                                             @Param("sellertype")int sellertype,
                                             @Param("limit")int limit,
                                             @Param("offset")int offset);
    public default Pager<SellInfo> getSellInfoBySelect(int regionId, int provinceId, int portId, String procuctNo, EnumSellInfo status1, EnumSellInfo status2, int sellertype, int page, int pagesize){
        final String procuctNumber = "%" + procuctNo + "%";
        return Pager.config(this.countSellInfoSelect(regionId, provinceId, portId, procuctNumber, status1, status2, sellertype),
                (int limit, int offset) -> this.listSellInfoSelect(regionId, provinceId, portId, procuctNumber, status1, status2, sellertype, limit, offset))
                .page(page, pagesize);
    }

    //添加审核信息
    @Insert("insert into supplyverify(status, applytime, sellinfoid, userid) values(#{status}, #{applytime}, #{sellinfoid}, #{userid})")
    public void addSupplyVerify(SupplyVerify supplyVerify);

    //查询该供应最新id by pid
    @Select("select max(id) from sellinfo where pid = #{pid}")
    public int getSupplyLatestId(String pid);

    //查询该供应最新editNum by pid
    @Select("select max(editnum) from sellinfo where pid = #{pid}")
    public int getSupplyLatestEditNum(String pid);

    //查询供应审核信息 by id
    @Select("select * from supplyverify where sellinfoid=#{sellinfoid} order by id desc limit 1")
    public SupplyVerify getSupplyVerifyBySellinfoId(int sellinfoid);


    //审核供应信息通过
    @Update("update supplyverify set status=#{status}, verifyman=#{verifyman}, remarks=#{remarks}, verifytime=#{verifytime} where status='WaitVerify' and id=#{id}")
    public int verifySupplyInfo(@Param("status")EnumSellInfo status, @Param("verifyman")String verifyman, @Param("remarks")String remarks, @Param("verifytime")LocalDateTime verifytime, @Param("id")int id);

    static final  String dynamicSql =
            "<where>" +
                    "<if test='sell!=null'>" +
                    "<if test='sell.pid!=null and sell.pid!=\"\"'>  and pid=#{sell.pid}</if>" +
                    "<if test='sell.seller!=null and sell.seller!=\"自营\"'> and seller!=\"自营\"</if>" +
                    "<if test='sell.seller!=null and sell.seller==\"自营\"'> and seller=\"自营\"</if>" +
                    "<if test='sell.ykj!=null'> and (ykj=#{sell.ykj}  or jtjlast=#{sell.ykj})</if>" +
                    "<if test='sell.pname!=null and sell.pname!=\"\"'> and pname=#{sell.pname}</if>" +
                    "<if test='sell.deliverymode!=null and sell.deliverymode!=\"\"'> and deliverymode=#{sell.deliverymode}</if>" +
                    "<if test='sell.deliveryregion!=null and sell.deliveryregion!=\"\"'> and deliveryregion=#{sell.deliveryregion}</if>" +
                    "<if test='sell.deliveryprovince!=null and sell.deliveryprovince!=\"\" ' > and deliveryprovince=#{sell.deliveryprovince}</if>" +
                    "<if test='sell.deliveryplace!=null and sell.deliveryplace!=\"\"'>  and deliveryplace=#{sell.deliveryplace}</if>" +
                    "<if test='sell.NCV!=null'> and NCV=#{sell.NCV}</if>" +
                    "<if test='sell.RS!=null'> and RS=#{sell.RS}</if>" +
                    "<if test='sell.ADV!=null'> and ADV=#{sell.ADV}</if>" +
                    "<if test='sell.TM!=null'> and TM=#{sell.TM}</if>"+
                    "</if>" +
                    "<if test='currentUser.isAdmin!=true'> and dealerid=#{currentUser.jobnum}</if>" +
                    "</where>";

    @Select("<script> select * from sellinfo" +dynamicSql+" order by  id limit #{limit} offset #{offset} </script>")
    public List<SellInfo> showAllList(@Param("currentUser")Admin admin,@Param("sell") SellInfo sellInfo,@Param("limit") int limit, @Param("offset") int offset);

    @Select("<script> select count(1) from sellinfo"+dynamicSql+" </script>")
    public int showAllListCount(@Param("currentUser")Admin admin,@Param("sell") SellInfo sellInfo);


    public default Pager<SellInfo> getAllSupplyList(Admin admin,SellInfo sellInfo,int page, int pagesize) {
        return Pager.config(this.showAllListCount(admin,sellInfo), (int limit, int offset) -> this.showAllList(admin,sellInfo,limit, offset))
                .page(page, pagesize);
    }

    @Update("update sellinfo set createdate=#{createdate} where id=#{id}")
    public void setCreateDate(@Param("createdate")LocalDate createdate, @Param("id")int id);

    //查询所有待确认供应信息
    @Select("select * from sellinfo where status=#{status}")
    public List<SellInfo> getAllWaitSellInfo(EnumSellInfo status);

    //查询推荐产品列表
    @Select("select * from sellinfo where status='VerifyPass' and availquantity > 0 and seller='自营' order by producttype desc, lastupdatetime desc limit 5")
    public List<SellInfo> getRecommondSellinfoList();

    //查询所有审核通过sellinfo
    @Select("select * from sellinfo where status='VerifyPass'")
    public List<SellInfo> getAllPassSellinfoList();

    //统计审核通过的sellinfo
    @Select("select IFNULL(SUM(`availquantity`), 0) from sellinfo where status='VerifyPass' and pname='动力煤'")
    public long getSumPassSellInfo();

    @Select("select IFNULL(SUM(`availquantity`), 0) from sellinfo where status='VerifyPass' and pname='无烟煤'")
    public long getAnthraciteSellInfo();

    //通过DealerId查询供应信息
    @Select("select * from sellinfo where dealerid=#{dealerid}")
    public List<SellInfo> getSellinfoByDealerId(@Param("dealerid")String dealerid);

    //更改sellinfo交易员信息
    @Update("update sellinfo set dealerid=#{dealerid}, dealername=#{dealername}, " +
            "dealerphone=#{dealerphone} where id=#{id}")
    public void updateSellinfoDealer(@Param("dealerid")String dealerid,
                                     @Param("dealername")String dealername,
                                     @Param("dealerphone")String dealerphone,
                                     @Param("id")int id);

    //XX直通车
    @Select("select * from sellinfo where status='VerifyPass' and pname=#{value} and seller='自营'  and supplyquantity>0")
    public List<SellInfo> findByCoalType(String coalType);


    @Select("SELECT * from `sellinfo`  where status='VerifyPass' and pname=#{coal} and seller='自营'")
    public List<SellInfo> getByDeliveryregion(@Param("coal")String coal);


    @Select("select * from sellinfo where deliveryregion=#{deliveryregion} and " +
            "deliveryprovince=#{deliveryprovince} and deliveryplace=#{deliveryplace}")
    public List<SellInfo> getSellInfoByRegionProvinceHarbour(@Param("deliveryregion")String deliveryregion,
                                                             @Param("deliveryprovince")String deliveryprovince,
                                                             @Param("deliveryplace")String deliveryplace);

    @Update("update sellinfo set dealerphone=#{dealerphone} where id=#{id}")
    public void updateSellinfoDealerPhoneById(@Param("dealerphone")String dealerphone,
                                              @Param("id")int id);

    @Update("update sellinfo set viewtimes=#{viewtimes} where id=#{id}")
    public void setPageViewTimesById(@Param("viewtimes")int viewtimes,
                                     @Param("id")int id);


    @Update("update sellinfo set parentid=#{parentid} where id=#{id}")
    public void setParentIdById(@Param("parentid")int parentid,
                                @Param("id")int id);

    @Select("SELECT * from sellinfo  where (parentid = #{parentid}  or  id = #{parentid} ) and  (id!=#{id}) order by editnum desc limit 10 ")
    List<SellInfo> getSellInfoEditHist(@Param("id")int id,@Param("parentid")int parentid);

    @Update("update sellinfo set dealerphone=#{dealerphone} where traderid=#{dealerid}")
    public void updateDealerPhone(@Param("dealerphone")String dealerphone,
                                              @Param("dealerid")int dealerid);




}



