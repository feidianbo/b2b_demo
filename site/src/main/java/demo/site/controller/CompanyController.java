package demo.site.controller;

import demo.core.domain.Company;
import demo.core.domain.CompanyVerify;
import demo.core.persistence.*;
import demo.site.basic.JsonController;
import demo.site.basic.annotation.LoginRequired;
import demo.site.service.Auth;
import demo.site.service.FileService;
import org.eclipse.jetty.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fanjun on 14-11-12.
 */
@LoginRequired
@Controller
public class CompanyController extends JsonController {
    @Autowired
    protected CompanyMapper companyMapper;
    @Autowired
    protected BuyMapper buyMapper;
    @Autowired
    protected Auth auth;
    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected OrderMapper orderMapper;
    @Autowired
    protected PaymentMapper paymentMapper;
    @Autowired
    protected FileService fileService;


    //保存或修改公司信息
    @RequestMapping(value = "/account/saveCompany", method = RequestMethod.POST)
    @ResponseBody
    public String saveCompany(@RequestParam("name") String name,@RequestParam("address") String address,
                              @RequestParam("phone") String phone,@RequestParam("fax") String fax,
                              @RequestParam("businesslicense") String businesslicense,
                              @RequestParam("identificationnumber") String identificationnumber,
                              @RequestParam("organizationcode") String organizationcode,
                              @RequestParam("operatinglicense") String operatinglicense,
                              @RequestParam("legalpersonname") String legalpersonname,
                              @RequestParam("account") String account,
                              @RequestParam("openingbank") String openingbank,
                              @RequestParam("identificationnumword") String identificationnumword,
                              @RequestParam("zipcode") String zipcode,
                              @RequestParam("openinglicense")String openinglicense,
                              @RequestParam("invoicinginformation")String invoicinginformation){
        Company company = new Company();
        company.setName(name);
        company.setAddress(address);
        company.setPhone(phone);
        company.setFax(fax);
        company.setBusinesslicense(businesslicense);
        company.setIdentificationnumber(identificationnumber);
        company.setOrganizationcode(organizationcode);
        company.setOperatinglicense(operatinglicense);
        company.setLegalpersonname(legalpersonname);
        company.setAccount(account);
        company.setOpeningbank(openingbank);
        company.setIdentificationnumword(identificationnumword);
        company.setZipcode(zipcode);
        company.setOpeninglicense(openinglicense);
        company.setInvoicinginformation(invoicinginformation);
        company.setUserid(session.getUser().getId());

        if (companyMapper.countCompany(session.getUser().getId()) == 0){
            companyMapper.addCompany(company);
        } else{
            companyMapper.modifyCompany(company);
        }
        companyMapper.addCompVerify(new CompanyVerify("待审核", LocalDateTime.now(), companyMapper.getIdByUserid(session.getUser().getId()), session.getUser().getId()));
        companyMapper.setCompanyStatus("待审核", null, companyMapper.getIdByUserid(session.getUser().getId()));
        userMapper.setUserVerifyStatus("待审核", session.getUser().getId());
        return JSON.toString("success");
    }

    //返回公司对象信息
    @RequestMapping(value = "/account/getCompany", method = RequestMethod.POST)
    @ResponseBody
    public Company getCompany(){
        return companyMapper.getCompanyByUserid(session.getUser().getId());
    }

    //保存公司图片
    @RequestMapping(value = "/account/saveCompanyPic", method = RequestMethod.POST)
    @ResponseBody
    public Object saveCompanyPic(@RequestParam("file") MultipartFile file,
                                 HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<>();
        boolean success = false;
        if(file.getSize() / 1000 / 1000 <= 10) {
            response.setContentType("text/html");
            map.put("filePath", fileService.uploadPicture(file));
            success = true;
        }
        map.put("success", success);
        return map;
    }

    //验证公司名称是否已存在
    @RequestMapping(value = "/account/checkCompanyname", method = RequestMethod.POST)
    @ResponseBody
    public String checkCompanyname(@RequestParam("name") String name){
        String pass = null;
        int i = companyMapper.countCompanyIsExist(name,session.getUser().getId());
        if(i == 0){
            pass = "true";
        }else{
            pass = "false";
        }
        return JSON.toString(pass);
    }
}
