package demo.core.persistence;

import demo.core.domain.Admin;
import demo.core.util.Pager;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by joe on 11/4/14.
 */
public interface AdminMapper {
    @Select("select * from admins where username=#{username}")
    public Admin getByUsername(String username);

    @Select("select * from admins where name=#{name}")
    public List<Admin> getByName(String name);

    @Select("select * from admins where phone=#{phone}")
    public Admin getByPhone(String phone);

    @Select("select count(1) from admins where phone=#{value}")
    public  int countByPhone(String phone);
    @Select("select * from admins where name=#{name} and phone=#{phone}")
    public Admin getAdminByNameAndPhone(@Param("name")String name,
                                        @Param("phone")String phone);

    //添加用户
    @Insert("insert into admins(username, password, isactive, phone, jobnum, name) " +
            "values(#{username}, #{password}, 1, #{phone}, #{jobnum}, #{name})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public int addAdmin(Admin admin);

    //设置用户的JobNum
    @Update("update admins set jobNum=#{jobnum} where id=#{id}")
    public int updateJobNumByid(Admin admin);

    //根据角色，查询
    @Select("select * from admins where role=#{role} and isactive=1")
    List<Admin> getManListByRole(String role);


    //根据工号查询
    @Select("select * from admins where jobnum=#{jobnum}")
    public Admin getAdminByJobNum(String jobnum);

    //根据姓名，电话查询dealerid
    @Select("select jobnum from admins where name=#{name} and phone=#{phone}")
    public String getJobnumByNamePhone(@Param("name")String name,
                                       @Param("phone")String phone);

    //改变admin， 是否启用，禁用
    @Select("update admins set isactive=#{isactive} where jobnum=#{jobnum}")
    public void setIsActiveByJobnum(@Param("isactive")boolean isactive, @Param("jobnum")String jobnum);


    //page分页
    @Select("select count(*) from admins")
    public int countAllAdmins();

    @Select("select admin.id,admin.username,admin.isactive,admin.name,admin.phone,admin.jobnum from admins admin order by admin.id limit #{limit} offset #{offset}")
    @Results(value= {@Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "name", column = "name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "jobnum", column = "jobnum"),
            @Result(property = "isactive",column = "isactive"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "kitt.core.persistence.RoleMapper.findRolesByUserId"))
            })
    public List<Admin> listAllAdmins(@Param("limit") int limit, @Param("offset") int offset);

    public default Pager<Admin> pageAllAdmins(int page, int pagesize){
        return Pager.config(this.countAllAdmins(), (int limit, int offset) -> this.listAllAdmins(limit, offset))
                .page(page, pagesize);
    }

    //修改用户的激活状态
    @Update("update admins set isactive=#{isactive} where id=#{id}")
    public void modifyIsactive(@Param("id") int id,@Param("isactive") boolean isactive);

    //重置密码
    @Update("update admins set password=#{password} where id=#{id}")
    public void initPassword(@Param("id") int id,@Param("password") String password);

    @Update("update admins set phone=#{phone} where id=#{id}")
    public void updatePhoneById(@Param("phone")String phone,
                         @Param("id")int id);

    @Update("update admins set password=#{password} where id=#{id}")
    public void resetPasswordById(@Param("password")String password,
                                  @Param("id")int id);

    @Select("select * from admins where id=#{id}")
    public Admin getAdminById(int id);

}
