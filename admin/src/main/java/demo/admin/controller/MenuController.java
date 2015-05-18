package demo.admin.controller;

import demo.admin.annotation.VerifyAuthentication;
import demo.admin.basic.JsonController;
import demo.admin.basic.exception.NotFoundException;
import demo.core.domain.Frame;
import demo.core.domain.Menu;
import demo.core.persistence.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jack on 15/3/5.
 */
@Controller
public class MenuController extends JsonController {
    @Autowired
    private MenuMapper menuMapper;

    @RequestMapping("/menu/list")
    @ResponseBody
    @VerifyAuthentication(Admin = true, BackgroundSupporter = true)
    public Object getMenuList() {
        if (session.getAdmin() != null) {
            Map<String, Object> map = new HashMap<>();
            List<Menu> firstMenuList =  menuMapper.getMenusByParentId(0);
            if (firstMenuList != null) {
                List<Frame> frameList = new ArrayList<>();
                for (Menu menu : firstMenuList) {
                    List<Menu> menusList = menuMapper.getMenusByParentId(menu.getId());
                    if (menusList != null && menusList.size() != 0) frameList.add(new Frame(menu, menusList));
                    else frameList.add(new Frame(menu, null));
                }
                map.put("frameList", frameList);
            } else {
                map.put("frameList", null);
            }
            return map;
        }
        return null;
    }

    //获取父级菜单
    @RequestMapping("/menu/getParentMenus")
    @ResponseBody
    public Object doGetParentMenus(@RequestParam(value = "level", required = true) int level){
        return menuMapper.getMenuListByParentid(level-2);
    }

    //新增菜单 编辑菜单
    @RequestMapping("/menu/addUpdate")
    @ResponseBody
    public Object doAddUpdateMenu(Menu newmenu){
        List<Menu> menuList = menuMapper.getMenuListByParentid(newmenu.getParentid());
        for(Menu menu : menuList){
            if(menu.getMenuname().equals(newmenu.getMenuname()) && !menu.getId().equals(newmenu.getId())){
                return false;
            }
        }
        menuMapper.saveMenu(newmenu);
        return true;
    }

    @RequestMapping("/menu/getMenuById")
    @ResponseBody
    public Object doGetMenuById(@RequestParam(value = "id", required = true)int id){
        Menu menu = menuMapper.getMenuById(id);
        if(menu == null) throw new NotFoundException();
        return menu;
    }

    @RequestMapping("/menu/deleteMenu")
    @ResponseBody
    public Object doDeleteMenus(@RequestParam(value = "id", required = true)int id){
        Menu menu = menuMapper.getMenuById(id);
        if(menu == null) throw new NotFoundException();
        menuMapper.doDeleteMenuAndSonMenusById(id);
        return true;
    }

    @RequestMapping("/menu/changeSequence")
    @ResponseBody
    public Object doChangeSequence(@RequestParam(value = "id", required = true)int id,
                                   @RequestParam(value = "sequence", required = true)int sequence){
        Menu menu = menuMapper.getMenuById(id);
        if(menu == null) throw new NotFoundException();
        return menuMapper.changeSequenceById(sequence, id) == 1 ? true : false;
    }

}
