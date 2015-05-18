package demo.core.persistence;

import demo.core.domain.Menu;
import demo.core.domain.Role;
import demo.core.domain.RoleMenu;
import demo.core.domain.UserRole;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jack on 15/3/5.
 */
public interface MenuMapper {
    //根据userid查询 拥有的角色
    @Select("select * from usersroles where userid=#{userid}")
    public List<UserRole> getUserRolesByUserid(int userid);

    @Select("select * from rolesmenus where roleid=#{roleid}")
    public List<RoleMenu> getRoleMenuListByRoleid(@Param("roleid")int roleid);

    @Select("select * from role where id=#{id}")
    public Role getRoleById(int id);

    @Select("select * from rolesmenus where roleid=#{roleid} and menuid=#{menuid}")
    public RoleMenu getRoleMenuByRoleidMenuid(@Param("roleid")int roleid, @Param("menuid")int menuid);

    @Select("select * from menus where parentid=#{parentid} order by sequence")
    public List<Menu> getMenusByParentId(int parentid);

    public default List<Menu> getMenuByUseridParentid(int userid, int parentid){
        List<UserRole> userRoleList = getUserRolesByUserid(userid);
        if(userRoleList != null && userRoleList.size() != 0) {
            List<Menu> menuList = new ArrayList<>();
            List<Integer> roleidList = new ArrayList<>();
            for (UserRole userRole : userRoleList) {
                roleidList.add(userRole.getRoleid());
            }
            if(roleidList != null && roleidList.size() != 0) {
                List<Menu> menus = getMenuListByRoleidListParentid(roleidList, parentid);
                if(menus != null && menus.size() != 0){
                    menuList.addAll(menus);
                    Set<Menu> allMenuList = new HashSet<Menu>(menuList);
                    return new ArrayList<>(allMenuList);
                }
            }
        }
        return null;
    }

    public default List<Menu> getMenuListByRoleidListParentid(List<Integer> roleidlist, int parentid){
        List<Integer> menuidList = new ArrayList<>();
        for(Integer i : roleidlist){
            List<RoleMenu> roleMenuList = getRoleMenuListByRoleid(i);
            if(roleMenuList != null && roleMenuList.size() != 0) {
                for (RoleMenu roleMenu : roleMenuList) {
                    menuidList.add(roleMenu.getMenuid());
                }
            }
        }
        if(menuidList != null && menuidList.size() != 0){
            List<Menu> allMenuList = getMenuListByParentid(parentid);
            if(allMenuList != null && allMenuList.size() != 0){
                List<Menu> menuList = new ArrayList<>();
                for(Menu menu : allMenuList){
                    if(menuidList.contains(menu.getId())){
                        menuList.add(menu);
                    }
                }
                return menuList;
            }
        }
        return null;
    }

    public default void saveMenu(Menu menu){
        if(menu.getSequence() == null){
            menu.setSequence(100);
        }
        if(menu.getParentid() != 0){
            if(getMenuById(menu.getParentid()) == null) throw new RuntimeException("parentid: " + menu.getParentid() + " is wrong!");
        }
        if(menu.getId() != null){
            updateMenu(menu);
        } else {
            addMenu(menu);
        }
    }

    //根据id获取菜单
    @Select("select * from menus where id=#{id} and parentid=#{parentid}")
    public Menu getMenuByIdParentid(@Param("id")int id, @Param("parentid")int parentid);

    @Select("select * from menus where parentid=#{parentid}")
    public List<Menu> getMenuListByParentid(@Param("parentid")int parentid);

    //查询所有菜单
    @Select("select * from menus")
    public List<Menu> getAllMenus();

    //根据Id获取菜单
    @Select("select * from menus where id=#{id}")
    public Menu getMenuById(int id);

    //新增菜单
    @Insert("insert into menus(menuname, url, parentid, sequence) values(#{menuname}," +
            "#{url}, #{parentid}, #{sequence})")
    public int addMenu(Menu menu);

    //修改菜单
    @Update("update menus set menuname=#{menuname}, url=#{url}, parentid=#{parentid}, " +
            "sequence=#{sequence} where id=#{id}")
    public int updateMenu(Menu menu);

    //删除菜单
    @Delete("delete from menus where id=#{id}")
    public void deleteMenuById(int id);

    public default void doDeleteMenuAndSonMenusById(int id){
        Menu menu = getMenuById(id);
        if(menu != null){
            List<Menu> menuList = getMenuListByParentid(menu.getId());
            deleteMenuById(id);
            if(menuList != null && menuList.size() != 0){
                for(Menu menu1 : menuList){
                    doDeleteMenuAndSonMenusById(menu1.getId());
                }
            }
        }
    }




    //--- fanjun ---
    //获取所有一级菜单 fj
    @Select("select * from menus where parentid=0")
    public List<Menu> getAllParentMenus();

    //获取所有二级菜单
    @Select("select * from menus where parentid<>0")
    public List<Menu> getAllChildMenus();

    //删除用户之前所拥有的菜单
    @Delete("delete from rolesmenus where roleid=#{roleid}")
    public void deleteRoleMenuByRoleid(int roleid);

    //添加用户菜单
    @Insert("insert into rolesmenus(roleid,menuid) values(#{roleid},#{menuid})")
    public void addRoleMenu(RoleMenu roleMenu);

    //更改菜单顺序
    @Update("update menus set sequence=#{sequence} where id=#{id}")
    public int changeSequenceById(@Param("sequence")int sequence,
                                  @Param("id")int id);

    //根据父菜单id获取子菜单
    @Select("select * from menus where parentid=#{id}")
    public List<Menu> getChildMenusByParentid(int id);

}
