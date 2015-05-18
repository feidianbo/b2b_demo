package demo.core.persistence;

import demo.core.domain.Admin;
import demo.core.domain.Customer;
import demo.core.util.Pager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by xiangyang on 15-1-14.
 */

public interface MyCustomerMapper {
    //查询客户列表
    @Select("<script>select u.id id,u.securephone nickname,c.name companyName,c.legalpersonname contact ,c.phone phone from users u  left join companies c on u.id = c.userid " +
            "where u.id in (select  userid from (" +
            "select  userid from demands <if test=\'admin.isAdmin!=true\'>where tradercode = #{admin.jobnum}</if>" +
            " union " +
            "select  sellerid from sellinfo <if test='admin.isAdmin!=true'>where dealerid =#{admin.jobnum}</if>" +
            ") t)  limit #{limit} offset #{offset}</script>")
    public List<Customer> showAllCustomer(@Param("admin") Admin admin,@Param("limit")int limit,@Param("offset")int offset);


    @Select("<script>select count(1) from users u  left join companies c on u.id = c.userid " +
            "where u.id in(select  sellerid from (select  sellerid from sellinfo <if test='admin.isAdmin!=true'>where dealerid =#{admin.jobnum}</if>" +
            "   union  " +
            "select  userid from demands <if test='admin.isAdmin!=true'>where tradercode = #{admin.jobnum}</if>" +
            ") t) </script>")
    public int countAllCustomer(@Param("admin") Admin admin);

    @Select("select * from (\t" +
            "(select sum(amount) sellAmount, sum(case when seller='自营' then amount else null end) shopOrderCount, sum(case when seller!='自营' then amount else null end) unShopOrderCount from orders where userId=#{id}) orders,\n" +
            "(select  count(1) releaseDemandCount,sum(purchasednum) demandMatchCount from demands where userId =#{id} ) demand,\n" +
            "(select count(1)  quoteCount, count( case when status = '已中标' then 1 else null end) validQuoteCount  from quotes where userId = #{id}) quote,\n" +
            "(select count(1) supplyCount,sum(soldquantity) matchSupplyCount  from sellinfo where sellerid = #{id})  sellinfo\n" +
            ") ")
    public Customer load(int id);

    @Select("select u.id from users u left join companies c  on u.id = c.userid  where  u.nickname=#{keyword}  or u.securephone=#{keyword} or c.name = #{keyword}")
    public Integer searchCustomerIdByCondition(@Param("keyword")String keyword);

    public default Pager<Customer> getAllCustomerList(Admin admin,int page, int pagesize){
        return  Pager.config(this.countAllCustomer(admin), (int limit, int offset) -> this.showAllCustomer(admin,limit, offset))
                .page(page, pagesize);
    }

    public default  Customer customerSearch(String keyword){
        if(null!=searchCustomerIdByCondition(keyword)){
            return   this.load(searchCustomerIdByCondition(keyword));
        }
        return null;
    }


}
