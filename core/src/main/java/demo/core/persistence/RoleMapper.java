package demo.core.persistence;

import demo.core.domain.Role;
import demo.core.util.Pager;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by xiangyang on 15-3-5.
 */
public interface RoleMapper {
    @Select("select * from roles order by id")
    public List<Role> list();
    @Select("select * from roles where id=#{id}")
    public Role queryById(int id);
    @Delete("delete from roles where id = #{id}")
    public int delete(int id);
    @Select("select * from roles where rolename=#{rolename}")
    public Role getRoleByRolename(String rolename);

    @Update("update roles set rolename=#{rolename},rolecode=#{rolecode} where id = #{id}")
    public int update(Role role);

    @Insert("insert into roles (rolename,rolecode) values(#{rolename},#{rolecode})")
    public  int save(Role role);

    @Select("<script>select count(id) from roles <where> <if test='rolename!=null'>rolename=#{rolename}</if>" +
            "<if test='rolecode!=null'>and rolecode=#{rolecode}</if></where></script>")
    public int isExists(Role role);

    @Select("select count(*) from roles")
    public int countAllRoles();

    @Select("select * from roles limit #{limit} offset #{offset}")
    public List<Role> listAllRoles(@Param("limit") int limit, @Param("offset") int offset);

    public default Pager<Role> pageAllRoles(int page, int pagesize){
        return Pager.config(this.countAllRoles(), (int limit, int offset) -> this.listAllRoles(limit, offset))
                .page(page, pagesize);
    }
    @Select("select roleid from usersroles where userid = #{value}")
    public  List<Integer> findByUserId(int userId);

    @Select("select r.id,r.rolename,r.rolecode from roles r, usersroles ur  where r.id=ur.roleid and ur.userid=#{value}")
    public  List<Role> findRolesByUserId(int userId);


    @Insert("<script>insert into usersroles (userid,roleid) <foreach collection=\'list\' index=\'i\' item=\'roleId\' separator=\'union all\'>select #{userId},#{roleId} from dual</foreach></script>")
    public void addUserRoles(@Param("userId")int userId,@Param("list")List<Integer> roleIds);

    @Delete("<script>delete from usersroles where userid=#{userId} and roleid in  <foreach collection=\'list\' index=\'i\' item=\'roleId\' open=\'(\' separator=\',\' close=\')\'>#{roleId}</foreach></script>")
    public void deleteUserRoles(@Param("userId")int userId,@Param("list")List<Integer> roleIds);

    @Select("select * from roles where id=#{id}")
    public Role getRoleById(int id);

}





