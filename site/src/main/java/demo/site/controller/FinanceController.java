package demo.site.controller;

import demo.core.domain.Company;
import demo.core.domain.Finance;
import demo.core.persistence.CompanyMapper;
import demo.core.persistence.FinanceMapper;
import demo.site.basic.JsonController;
import org.eclipse.jetty.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fanjun on 15-1-21.
 */
@Controller
public class FinanceController extends JsonController {
    @Autowired
    protected FinanceMapper financeMapper;
    @Autowired
    protected CompanyMapper companyMapper;

    //跳转到煤易贷页面
    @RequestMapping("/finance/loan")
    public String gotoLoan() {
        return "loan";
    }

    //跳转到快融通页面
    @RequestMapping("/finance/financing")
    public String gotoFinancing() {
        return "financing";
    }

    //保存联系方式
    @RequestMapping(value = "/finance/saveContact", method = RequestMethod.POST)
    @ResponseBody
    public String saveContact(String type, String companyname, String address,
                              String businessarea, Integer amountnum, String contact,
                              String phone){
        Finance finance = new Finance(type, companyname, address, businessarea, amountnum, contact, phone, LocalDateTime.now());
        financeMapper.addFinanceContact(finance);
        return JSON.toString("success");
    }

    //获取用户信息
    @RequestMapping(value = "/finance/getUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object getUserInfo(){
        Company company = companyMapper.getCompanyByUserid(session.getUser().getId());
        Map map  = new HashMap<>();
        map.put("company", company);
        map.put("phone", session.getUser().getSecurephone());
        return map;
    }

}
