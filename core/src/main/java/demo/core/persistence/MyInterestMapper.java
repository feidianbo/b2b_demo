package demo.core.persistence;

import demo.core.domain.MyInterest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by jack on 15/1/4.
 */
public interface MyInterestMapper {
    //我的关注
    @Select("select * from myinterest where type=#{type} and userid=#{userid} and  isdelete=0 order by lastupdatetime desc limit #{limit} offset #{offset}")
    public List<MyInterest> getMyInterestList(@Param("type") String type,
                                              @Param("userid") int userid,
                                              @Param("limit") int limit,
                                              @Param("offset") int offset);

    @Select("select count(id) from myinterest where type=#{type} and userid=#{userid} and isdelete=0")
    public int getMyInterestCount(@Param("type")String type, @Param("userid")int userid);

    //添加我的关注
    @Insert("insert into myinterest(pid, sid, pname, seller, price, amount, NCV, userid, type, createtime) values(#{pid}, #{sid}, #{pname}, #{seller}, #{price}, #{amount}, #{NCV}, #{userid}, #{type}, now())")
    public void addMyInterest(MyInterest myInterest);

    //查询是否已经关注sid
    @Select("select * from myinterest where sid=#{sid} and userid=#{userid} and type=#{type}")
    public MyInterest getMyInterestBySid(@Param("sid")int sid, @Param("userid")int userid, @Param("type")String type);

    //取消关注
    @Update("update myinterest set isdelete=1 where id=#{id}")
    public void cancelMyInterest(int id);

    //我的关注-已关注，修改状态
    @Update("update myinterest set isdelete=0 where sid=#{sid} and userid=#{userid} and type=#{type}")
    public void setMyInterestStatusBySid(@Param("sid")int sid, @Param("userid")int userid, @Param("type")String type);

    //根据id获取MyInterest
    @Select("select * from myinterest where id=#{id}")
    public MyInterest getMyInterestById(int id);


}
