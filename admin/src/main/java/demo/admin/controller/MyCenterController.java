package demo.admin.controller;

import demo.admin.basic.exception.NotFoundException;
import demo.admin.service.Session;
import demo.core.domain.*;
import demo.core.persistence.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jack on 15/3/13.
 */
@RestController
public class MyCenterController {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private Session session;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private DealerMapper dealerMapper;
    @Autowired
    private DemandMapper demandMapper;
    @Autowired
    private BuyMapper buyMapper;

    @RequestMapping("/center/view")
    public Object showMyInfo(){
        Map<String, Object> map = new HashMap<>();
        Admin admin = session.getAdmin();
        if(admin == null) throw new NotFoundException();
        map.put("admin", adminMapper.getAdminById(session.getAdmin().getId()));
        List<UserRole> userRoleList = userRoleMapper.getUserRolesByUserid(admin.getId());
        String roles = "";
        for(UserRole userRole : userRoleList){
            Role role = roleMapper.getRoleById(userRole.getRoleid());
            if(role != null && role.getRolecode() != null) {
                roles += role.getRolename() + " ";
            }
        }
        map.put("roles", roles);
        return map;
    }

    @RequestMapping("/center/editphone")
    public Object doEditPhone(@RequestParam(value = "phone", required = true)String phone){
        if(session.getAdmin() == null) throw new NotFoundException();
        Map<String, Object> map = new HashMap<>();
        boolean success = false;
        if(adminMapper.getByPhone(phone) == null) {
            adminMapper.updatePhoneById(phone, session.getAdmin().getId());
            List<Dealer> dealerList = dealerMapper.getDealerByDealerId(session.getAdmin().getJobnum());
            if (dealerList != null && dealerList.size() != 0) {
                for (Dealer dealer : dealerList) {
                    dealerMapper.updateDealerPhoneById(phone, dealer.getId());
                }
                List<SellInfo> sellInfoList = buyMapper.getSellinfoByDealerId(session.getAdmin().getJobnum());
                for (SellInfo sellInfo : sellInfoList) {
                    buyMapper.updateSellinfoDealerPhoneById(phone, sellInfo.getId());
                }
                List<Demand> demandList = demandMapper.getDemandListByDealerid(session.getAdmin().getJobnum());
                for (Demand demand : demandList) {
                    demandMapper.updateDemandDealerPhone(phone, demand.getId());
                }
            }
            success = true;
        } else{
            map.put("error", "phoneexist");
        }
        map.put("success", success);
        return map;
    }

    @RequestMapping("/center/resetpassword")
    public Object doResetPassword(@RequestParam("formerpassword")String formerPassword,
                                  @RequestParam("password")String password,
                                  @RequestParam("repeatpassword")String repeatPassword){
        if(session.getAdmin() == null) throw new NotFoundException();
        Map<String, Object> map = new HashMap<>();
        boolean success = false;
        String error = "";
        Admin admin = adminMapper.getAdminById(session.getAdmin().getId());
        if(admin.getPassword().equals(DigestUtils.md5Hex(formerPassword))) {
            if (password.equals(repeatPassword)) {
                adminMapper.resetPasswordById(DigestUtils.md5Hex(password), session.getAdmin().getId());
                success = true;
            } else{
                error = "notequal";
            }
        } else{
            error = "incorrect";
        }
        map.put("error", error);
        map.put("success", success);
        return map;
    }

}








