package demo.core.persistence;

import demo.core.domain.Finance;
import demo.core.util.Pager;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by fanjun on 15-1-21.
 */
public interface FinanceMapper {

    @Insert("insert into finance(type,companyname,address,businessarea,amountnum,contact,phone,createtime) values(" +
            "#{type},#{companyname},#{address},#{businessarea},#{amountnum},#{contact},#{phone},#{createtime})")
    public int addFinanceContact(Finance finance);

    //后台page
    @Select("select count(*) from finance")
    public int countAllFinanceCustomer();

    @Select("select * from finance  order by createtime desc limit #{limit} offset #{offset}")
    public List<Finance> listAllFinanceCustomer(@Param("limit") int limit,
                                                @Param("offset") int offset);

    public default Pager<Finance> pageAllFinanceCustomer(int page, int pagesize){
        return Pager.config(this.countAllFinanceCustomer(), (int limit, int offset) -> this.listAllFinanceCustomer(limit, offset))
                .page(page, pagesize);
    }

    //获取今天金融客户集合
    @Select("select * from finance where date_format(createtime,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d')")
    public List<Finance> getTodayFinaceCustomers();

    //获取所有金融客户
    @Select("select * from finance")
    public List<Finance> getAllFinanceCustomers();

}
