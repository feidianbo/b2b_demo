package demo.core.persistence;

import demo.core.domain.Operateauth;
import demo.core.domain.RoleOperate;
import demo.core.util.Pager;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by fanjun on 15-3-5.
 */
public interface OperateMapper {

    //page分页
    @Select("select count(*) from operateauth")
    public int countAllOperateauth();

    @Select("select * from operateauth limit #{limit} offset #{offset}")
    public List<Operateauth> listAllOperateauth(@Param("limit") int limit, @Param("offset") int offset);

    public default Pager<Operateauth> pageAllOperateauth(int page, int pagesize){
        return Pager.config(this.countAllOperateauth(), (int limit, int offset) -> this.listAllOperateauth(limit, offset))
                .page(page, pagesize);
    }

    //删除操作权限
    @Delete("delete from operateauth where id=#{id}")
    public void deleteOperateauth(int id);

    //验证操作权限唯一性
    @Select("select count(*) from operateauth where operatecode=#{operatecode}")
    public int countOperatecode(String operatecode);

    //保存操作权限
    @Insert("insert into operateauth(operatename,operatecode,menuid,menuname) values(#{operatename},#{operatecode},#{menuid},#{menuname})")
    public void addOperateauth(Operateauth operateauth);

    //获取所有操作权限
    @Select("select * from operateauth")
    public List<Operateauth> getAllOperateauth();

    //删除用户之前所拥有的操作权限
    @Delete("delete from rolesoperate where roleid=#{roleid}")
    public void deleteRoleOperateByRoleid(int roleid);

    //添加用户的操作权限
    @Insert("insert into rolesoperate(roleid,operatecode) values(#{roleid},#{operatecode})")
    public void addRoleOperate(RoleOperate roleOperate);

    //根据角色id获取所有相关操作
    @Select("select * from rolesoperate where roleid=#{roleid}")
    public List<RoleOperate> getRoleOperateByRoleid(int roleid);
}
