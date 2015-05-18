package demo.core.persistence;

import demo.core.domain.PriceLadder;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by jack on 15/1/4.
 */
public interface PriceLadderMapper {
    //阶梯价
    //添加阶梯价
    @Insert("insert into priceladder(sellinfoid, squence, price, amount1, amount2, createtime, " +
            "userid, username) values(#{sellinfoid}, #{squence}, #{price}, #{amount1}, #{amount2}," +
            " #{createtime}, #{userid}, #{username})")
    public void addPriceLadder(PriceLadder priceLadder);

    //更改阶梯价
    @Update("update priceladder set price=#{price}, amount1=#{amount1}, amount2=#{amount2} where sellinfoid=#{sellinfoid} and squence=#{squence}")
    public void updatePriceLadder(@Param("price")int price, @Param("amount1")int amount1, @Param("amount2")int amount2, @Param("sellinfoid")int sellinfoid, @Param("squence")int squence);

    //删除阶梯价
    @Delete("delete from priceladder where sellinfoid=#{sellinfoid} and squence=#{squence}")
    public int deletePriceLadder(@Param("sellinfoid")int sellinfoid, @Param("squence")int squence);

    //删除阶梯价-多个
    @Delete("delete from priceladder where sellinfoid=#{sellinfoid} and squence>=#{squence}")
    public int deletePriceLadderList(@Param("sellinfoid")int sellinfoid, @Param("squence")int squence);

    //查询阶梯价
    @Select("select * from priceladder where sellinfoid=#{sellinfoid} order by squence")
    public List<PriceLadder> getPriceLadderListBySellinfoId(int sellinfoid);

    //查询阶梯价-条件
    @Select("select count(*) from priceladder where sellinfoid=#{sellinfoid} and squence>=#{squence}")
    public int countPriceLadderBySquence(@Param("sellinfoid")int sellinfoid, @Param("squence")int squence);

    //查询阶梯价
    @Select("select * from priceladder where sellinfoid=#{sellinfoid} and squence=#{squence}")
    public PriceLadder getPriceLadderBySellinfoIdSquence(@Param("sellinfoid")int sellinfoid, @Param("squence")int squence);

}
