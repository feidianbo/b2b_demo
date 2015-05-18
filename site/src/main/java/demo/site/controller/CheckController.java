package demo.site.controller;

import demo.core.domain.SellInfo;
import demo.core.domain.User;
import demo.core.persistence.BuyMapper;
import demo.core.persistence.UserMapper;
import demo.site.basic.JsonController;
import demo.site.basic.annotation.LoginRequired;
import demo.site.basic.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jack on 15/1/17.
 */
@Controller
public class CheckController extends JsonController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BuyMapper buyMapper;

    //检查公司信息状态
    @RequestMapping("/doCheckCompany")
    @ResponseBody
    @LoginRequired
    public Object doCheckCompany() {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean success = false;
        String error = "";
        User user = userMapper.getUserByPhone(session.getUser().getSecurephone());
        if(user == null) throw new NotFoundException();
        switch (user.getVerifystatus()) {
            case "待完善信息":
                error = "lackinfo";
                break;
            case "待审核":
                error = "verifying";
                break;
            case "审核未通过":
                error = "notpass";
                break;
            case "审核通过":
                success = true;
                break;
            default:
                break;
        }
        map.put("error", error);
        map.put("success", success);
        return map;
    }

    //检查用户是否登录
    @RequestMapping("/doCheckLogin")
    @ResponseBody
    public Object doCheckLogin(){
        if(session.getUser() == null){
            return false;
        } else{
            return true;
        }
    }

    //检测卖家和买家是否是同一公司
    @RequestMapping("/doCheckSeller")
    @ResponseBody
    @LoginRequired
    public Object doCheckSeller(@RequestParam(value = "id", required = true)int id){
        SellInfo sellInfo = buyMapper.getSellInfoById(id);
        if(sellInfo == null) throw new NotFoundException();
        if(!sellInfo.getSeller().equals("自营") && sellInfo.getSellerid() == session.getUser().getId()){
            return false;
        } else{
            return true;
        }
    }


}
