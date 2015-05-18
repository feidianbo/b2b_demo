package demo.core.persistence;

import demo.core.domain.Payment;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jack on 14/12/31.
 */
public interface PaymentMapper {

    //添加支付凭证
    @Insert("insert into payment(createtime, oid, orderid, userid, username, pictureurl) values(now(), #{oid}, #{orderid}, #{userid}, #{username}, #{pictureurl})")
    @Options(useGeneratedKeys=true)
    public void addPayment(Payment payment);

    //查询待审核支付凭证
    @Select("select * from payment where orderid=#{orderid} and isverified=#{isverified}")
    public List<Payment> getPaymentList(@Param("orderid")int orderid, @Param("isverified")boolean isverified);

    //查询所有未审核的支付凭证
    @Select("select * from payment where isverified=0")
    public List<Payment> getAllUnCheckPayment();

    //查询支付凭证-id
    @Select("select * from payment where id=#{id}")
    public Payment getPaymentById(int id);

    //删除支付凭证
    @Delete("delete from payment where id=#{id}")
    public void deletePaymentById(int id);

    //审核支付凭证
    @Update("update payment set issuccess=#{issuccess}, money=#{money}, verifyman=#{verifyman}, verifymanid=#{verifymanid}, verifytime=now(), isverified=1 where id=#{id}")
    public void verifyPayment(@Param("issuccess")boolean issuccess, @Param("money")BigDecimal money, @Param("verifyman")String verifyman, @Param("verifymanid")int verifymanid, @Param("id")int id);

    //设置支付凭证作废
    @Update("update payment set isverified=1 where id=#{id}")
    public void setPaymentVerified(int id);

}
