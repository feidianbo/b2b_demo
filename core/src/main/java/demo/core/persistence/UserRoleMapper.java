package demo.core.persistence;

import demo.core.domain.Role;
import demo.core.domain.UserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jack on 15/3/5.
 */
public interface UserRoleMapper {

    //通过id查询Role
    @Select("select * from roles where id=#{id}")
    public Role getRoleById(int id);

    //根据userid查询 拥有的角色
    @Select("select * from usersroles where userid=#{userid}")
    public List<UserRole> getUserRolesByUserid(int userid);

    public default List<Role> getRoleListByUserid(int id){
        List<Role> roleList = new ArrayList<>();
        for(UserRole userRole : getUserRolesByUserid(id)){
            roleList.add(getRoleById(userRole.getRoleid()));
        }
        return roleList;
    }

    @Insert("insert into usersroles (userid,roleid) values (#{userid},#{roleid})")
    public  void  insertUserRole(UserRole userRoles);

}
