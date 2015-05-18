package demo.core.persistence;


import com.mysql.jdbc.StringUtils;
import demo.core.domain.User;
import demo.core.domain.Userlogin;
import demo.core.util.Pager;
import demo.ext.mybatis.Where;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;


public interface UserMapper {
	//新增用户 lxj
    @Insert("insert into users (securephone, nickname, password, registertime, isactive) values (#{securephone}, " +
		            "#{nickname}, #{password}, #{registertime}, #{isactive})")
    @Options(useGeneratedKeys=true)
    public void addUser(User user);

	//禁用，启用账户 add by lxj
	@Update("update users set isactive=#{isactive} where securephone=#{securephone}")
	public void editUserAccount(@Param("isactive")boolean isactive, @Param("securephone")String securephone);

    //设置用户公司信息审核状态
    @Update("update users set verifystatus=#{verifystatus} where id=#{id}")
    public void setUserVerifyStatus(@Param("verifystatus")String verifystatus, @Param("id")int id);

    //登陆
    @Select("select * from users where securephone=#{securephone}")
    public User getUserByPhone(String securephone);

    //判断用户修改的手机是否存在
    @Select("select count(*) from users where securephone=#{securephone}")
    public int getUserByNewSecurephone(String securephone);

    final String UserSelect1 = "<if test='content!= null'> securephone like #{content} or id in(select userid from companies where name like #{content} or legalpersonname like #{content})</if>";
	@Select("<script>" +
            "select distinct count(*) from users" +
            "<where>" + UserSelect1 + "</where>" +
            "</script>")
	public int countUserBySelect(@Param("content")String content);

    @Select("<script>" +
            "select distinct * from users" +
            "<where>" + UserSelect1 + "</where> limit #{limit} offset #{offset}"+
            "</script>")
    public List<User> getUserBySelect(@Param("content")String content,
                                      @Param("limit")int limit,
                                      @Param("offset")int offset);

    public default Pager<User> pageSelectUser(String content, int page, int pagesize){
        return Pager.config(this.countUserBySelect(content), (int limit, int offset) -> this.getUserBySelect(content, limit, offset))
                .page(page, pagesize);
    }

    public default Pager<User> pageAllSelectUser(String content, int page, int pagesize){
        if(!StringUtils.isNullOrEmpty(content)){
            content = "%" + content + "%";
        }
        return pageSelectUser(content, page, pagesize);
    }



    //修改密码
    @Update("update users set password=#{password} where securephone=#{securephone}")
    public void modifyPasswd(@Param("password") String password, @Param("securephone") String securephone);

    //插入用户登陆记录
    @Insert("insert into userlogins (userid,logintime,loginip,loginby,useragent,acceptlanguage) values (#{userid},#{logintime},#{loginip},#{loginby},#{useragent},#{acceptlanguage})")
    public void addUserlogin(Userlogin userlogin);

    @Select("<script>select count(u.id) from users u left join companies c on u.id=c.userid <where>" +
            " <if test='securePhone==null or securePhone==\"\" '> and u.verifystatus=#{status}</if> " +
            "<if test='securePhone!=null and securePhone!=\"\" '>and (c.name like #{securePhone}  or u.securephone like #{securePhone}  or c.legalpersonname like #{securePhone})</if>" +
            "<if test='startDate!=null and  endDate!=null'> and DATE_FORMAT(u.registertime,'%Y-%m-%d') between  #{startDate} and  #{endDate}</if> " +
            "<if test='startDate!=null and endDate==null '> and DATE_FORMAT(u.registertime,'%Y-%m-%d') between  #{startDate} and  DATE_FORMAT(now(),'%Y-%m-%d')</if></where></script>")
    public int countAllUser(@Param("status")String status,@Param("securePhone")String securePhone,@Param("startDate") LocalDate date1,@Param("endDate") LocalDate date2);

    @Select("<script>select u.id,u.securephone,u.nickname,u.registertime,u.isactive,u.verifystatus from users u left join companies c on u.id=c.userid" +
            " <where> <if test='securePhone==null or securePhone==\"\" '>u.verifystatus=#{status}</if> " +
            "<if test='securePhone!=null and securePhone!=\"\" '>and (c.name like #{securePhone}  or u.securephone like #{securePhone}  or c.legalpersonname like #{securePhone})</if>" +
            "<if test='startDate!=null and  endDate!=null'> and DATE_FORMAT(u.registertime,'%Y-%m-%d') between  #{startDate} and  #{endDate}</if> " +
            "<if test='startDate!=null and endDate==null '> and DATE_FORMAT(u.registertime,'%Y-%m-%d') between  #{startDate} and  DATE_FORMAT(now(),'%Y-%m-%d')</if></where> limit #{limit} offset #{offset}</script>")
    public List<User> listAllUser(@Param("status")String status,@Param("securePhone")String securePhone,@Param("startDate") LocalDate date1,@Param("endDate") LocalDate date2, @Param("limit") int limit, @Param("offset") int offset);


    public default Pager<User> pageAllUser(String status,String securepPhone,LocalDate date1,LocalDate date2, int page, int pagesize){
        String name= org.springframework.util.StringUtils.isEmpty(securepPhone)==true?null: Where.$like$(securepPhone);
       return Pager.config(this.countAllUser(status,name,date1,date2), (int limit, int offset) -> this.listAllUser(status,name,date1, date2,limit, offset))
                .page(page, pagesize);
    }
    @Select("<script>select c.name,c.address,u.registertime,u.securephone from users u left join companies c on u.id=c.userid where u.verifystatus=#{status} " +
            "<if test='securePhone!=null and securePhone!=\"\" '>and (c.name like #{securePhone}  or u.securephone like #{securePhone}  or c.legalpersonname like #{securePhone})</if>" +
            "<if test='startDate!=null and  endDate!=null'> and DATE_FORMAT(u.registertime,'%Y-%m-%d') between  #{startDate} and  #{endDate}</if> " +
            "<if test='startDate!=null and endDate==null '> and DATE_FORMAT(u.registertime,'%Y-%m-%d') between  #{startDate} and  DATE_FORMAT(now(),'%Y-%m-%d')</if></script>")
    public List<Map<String,Object>> userExport(@Param("status")String status,@Param("securePhone")String securePhone,@Param("startDate") LocalDate date1,@Param("endDate") LocalDate date2);


    //修改昵称
    @Update("update users set nickname=#{nickname} where securephone=#{securephone}")
    public void modifyNickname(@Param("nickname") String nickname, @Param("securephone") String securephone);

    //修改手机号
    @Update("update users set securephone=#{securephone} where id=#{userid}")
    public void modifyPhone(@Param("securephone") String securephone, @Param("userid") int userid);

    //修改账户密码
    @Update("update users set password=#{newpassword} where id=#{userid}")
    public void modifyAccountPasswd(@Param("newpassword") String newpassword,@Param("userid") int userid);

    //修改用户信息
    @Update("update users set password=#{password}, nickname=#{nickname} where securephone=#{securephone}")
    public void updateUser(@Param("securephone") String securephone,@Param("password") String password,@Param("nickname") String nickname);

    //保存我的账户的固定电话和QQ
    @Update("update users set telephone=#{telephone},qq=#{qq} where id=#{id}")
    public void modifyPhoneAndQQ(@Param("telephone") String telephone, @Param("qq") String qq,@Param("id") int id);

    //查询user by id
    @Select("select * from users where id = #{id}")
    public User getUserById(int id);

    @Select("select * from users where verifystatus='待完善信息'")
    List<User> getAllInfoUserList();

}
