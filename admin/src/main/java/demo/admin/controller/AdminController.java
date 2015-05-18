package demo.admin.controller;

import demo.admin.annotation.VerifyAuthentication;
import demo.admin.service.Session;
import demo.core.domain.*;
import demo.core.persistence.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.jetty.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by xiangyang on 15-3-5.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private OperateMapper operateMapper;

    @Autowired
    private Session session;

    @RequestMapping(value = "/loadAllRoles",method = RequestMethod.POST)
    public Object loadAllRoles(){
      return  roleMapper.list();
    }

    @RequestMapping(value = "/findRoleByUserId",method = RequestMethod.POST)
    public Object loadAllRoles(@RequestParam("userId")int userId){
        return  roleMapper.findByUserId(userId);
    }

    //添加用户
    @RequestMapping(value = "/addAdmin",method = RequestMethod.POST)
    @VerifyAuthentication(Admin =true)
    public boolean addAdmin(Admin admin, @RequestParam(value = "roleIds",required = false)Integer[] roleIds){
        //添加用户
        admin.setPassword(DigestUtils.md5Hex("123456"));
        adminMapper.addAdmin(admin);
        admin.setJobnum("YMW"+admin.getId());
        adminMapper.updateJobNumByid(admin);
        //添加用户关联角色
        if(roleIds!=null){
            for(Integer roleId:roleIds){
                userRoleMapper.insertUserRole(new UserRole(roleId,admin.getId()));
            }
        }

        return true;
    }
    //修改用户的角色
    @RequestMapping(value = "/updateRole",method = RequestMethod.POST)
    @VerifyAuthentication(Admin =true)
    public boolean updateRole(@RequestParam("userId")int userId,@RequestParam(value = "userRoles",required = false)Integer[] roleIds){

           List<Integer> oldRoleIds = roleMapper.findByUserId(userId);
           List<Integer> newRoleIds=roleIds==null?new ArrayList<Integer>():Arrays.asList(roleIds);
           //删除角色信息
           List<Integer> deleteIds=getSubSet(oldRoleIds,newRoleIds);
           //添加角色信息
           List<Integer> addIds=getSubSet(newRoleIds,oldRoleIds);
           if(deleteIds.size()>0){
               roleMapper.deleteUserRoles(userId,deleteIds);
           }
           if(addIds.size()>0){
               roleMapper.addUserRoles(userId,addIds);
           }

        return true;
    }
    @RequestMapping("/checkUsernameExist")
    public boolean checkuserNameIsExist(@RequestParam("username")String username){
        if(adminMapper.getByUsername(username)!=null) {
            return false;
        }
        return true;
    }

    @RequestMapping("/checkphoneExist")
    public boolean checkphoneIsExist(@RequestParam("phone")String phone){
        if(adminMapper.countByPhone(phone)>=1) {
            return false;
        }
        return true;
    }

    @RequestMapping("/checkRoleExist")
    public boolean checkuserNameIsExist(Role role){
        if(roleMapper.isExists(role)>0) {
            return false;
        }
        return true;
    }

    @RequestMapping("/showRoles")
    public Object showRoles(@RequestParam(value = "page", defaultValue = "1", required = false) Integer page){
        return roleMapper.pageAllRoles(page, 10);
    }
    @RequestMapping("/addRole")
    @VerifyAuthentication(Admin =true)
    public Object addRole(Role role){
        if(roleMapper.save(role)==1){
            return true;
        }
        return false;
    }

    @RequestMapping("/load/{id}")
    public Object load(@PathVariable("id")int id){
        return roleMapper.queryById(id);
    }
    @RequestMapping("/updateRole")
    @VerifyAuthentication(Admin =true)
    public Object updateRole(Role role) {
        if(roleMapper.update(role)==1){
            return true;
        }
        return false;
    }

    @RequestMapping("/deleteRole/{id}")
    @VerifyAuthentication(Admin =true)
    public Object deleteRole(@PathVariable("id") int id) {
        if(roleMapper.delete(id)==1){
            return true;
        }
        return false;
    }

    //获取菜单和相关的权限集合
    @RequestMapping("/getMenusAuth")
    public Object getMenusAuth(int roleid) {
        //获取所有选项
        List<Menu> parentMenusList = menuMapper.getAllParentMenus();
        List<Menu> childMenusList = menuMapper.getAllChildMenus();
        List<Operateauth> operateauthList = operateMapper.getAllOperateauth();

        //获取用户的数据
        List<RoleMenu> userMenusList = menuMapper.getRoleMenuListByRoleid(roleid);
        List<RoleOperate> userOperateList = operateMapper.getRoleOperateByRoleid(roleid);
        Map map = new HashMap<>();
        map.put("parentMenusList",parentMenusList);
        map.put("childMenusList",childMenusList);
        map.put("operateauthList",operateauthList);
        map.put("userMenusList",userMenusList);
        map.put("userOperateList",userOperateList);
        return map;
    }

    //保存菜单权限配置
    @RequestMapping("/saveRoleMenuAuth")
    public Object saveRoleMenuAuth(int roleid,String menuidArr,String operatecodeArr) {
        //先删除用户已有的菜单和权限,然后再添加
        menuMapper.deleteRoleMenuByRoleid(roleid);
        operateMapper.deleteRoleOperateByRoleid(roleid);

        if(menuidArr != null && menuidArr != "" && !menuidArr.equals(",")) {
            String menuidList[] = menuidArr.split(",");

            for (int i = 0; i < menuidList.length; i++) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleid(roleid);
                roleMenu.setMenuid(Integer.parseInt(menuidList[i]));
                menuMapper.addRoleMenu(roleMenu);
            }
        }

        if(operatecodeArr != null && operatecodeArr != "" && !operatecodeArr.equals(",")) {
            String operatecodeList[] = operatecodeArr.split(",");
            for (int i = 0; i < operatecodeList.length; i++) {
                RoleOperate roleOperate = new RoleOperate();
                roleOperate.setRoleid(roleid);
                roleOperate.setOperatecode(operatecodeList[i]);
                operateMapper.addRoleOperate(roleOperate);
            }
        }

        boolean success = true;
        return JSON.toString(success);
    }

    //获取所有用户
    @RequestMapping("/adminList")
    public Object adminList(@RequestParam(value = "page",defaultValue ="1")int page) {
        Map map = new HashMap();
        map.put("admin", adminMapper.pageAllAdmins(page, 10));
        return map;
    }


    //禁用/启用用户
    @RequestMapping("/changeStatus")
    public Object changeStatus(int id,int isActive) {
        if(isActive == 0) {
            adminMapper.modifyIsactive(id,false);
        }else{
            adminMapper.modifyIsactive(id,true);
        }
        boolean success = true;
        return JSON.toString(success);
    }

    //重置密码
    @RequestMapping("/initPassword")
    public Object initPassword(int id) {
        adminMapper.initPassword(id,DigestUtils.md5Hex("000000"));
        boolean success = true;
        return JSON.toString(success);
    }

    //获取用户权限
    @RequestMapping("/getUserAuth")
    public Object getUserAuth() {
        Admin admin = session.getAdmin();
        List operatecodeList = new ArrayList<>();
        if(admin != null) {
            List<UserRole> userRoleList = userRoleMapper.getUserRolesByUserid(admin.getId());
            for (UserRole userRole : userRoleList) {
                List<RoleOperate> roleOperateList = operateMapper.getRoleOperateByRoleid(userRole.getRoleid());
                if(roleOperateList != null && roleOperateList.size() >0) {
                    for (RoleOperate roleOperate : roleOperateList) {
                        operatecodeList.add(roleOperate.getOperatecode());
                    }
                }
            }
        }
        Map map = new HashMap<>();
        map.put("operatecodeList",operatecodeList);
        return map;
    }

    //得到两个集合的差集
    private    List<Integer> getSubSet(List<Integer> c1,List<Integer> c2){
        List<Integer> result = new ArrayList<Integer>();
        result.addAll(c1);
        result.removeAll(c2);
        return result;
    }

}
