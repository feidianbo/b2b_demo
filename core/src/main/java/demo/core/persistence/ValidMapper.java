package demo.core.persistence;

import demo.core.domain.Phonevalidator;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by fanjun on 14-11-7.
 */
public interface ValidMapper {
    //插入验证码
    @Insert("insert into phonevalidators (expiretime, phone,code,userid,contextjson,validated,validatetype) values (#{expiretime}, #{phone}," +
            "#{code},#{userid},#{contextjson},#{validated},#{validatetype})")
    public int addValid(Phonevalidator phonevalidator);

    //获取验证码对象
    @Select("select * from phonevalidators where phone=#{securephone} and code=#{code} limit 1")
    public Phonevalidator getValidcodeObj(@Param("securephone") String securephone, @Param("code") String code);

    //更改验证状态
    @Update("update phonevalidators set validated =1, validatetime = now() where phone=#{securephone} and code=#{code}")
    public void modifyValidatedAndTime(@Param("securephone") String securephone, @Param("code") String code);
}
