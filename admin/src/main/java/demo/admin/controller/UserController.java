package demo.admin.controller;



import com.itextpdf.text.DocumentException;
import com.mysql.jdbc.StringUtils;
import demo.admin.annotation.VerifyAuthentication;
import demo.admin.basic.JsonController;
import demo.admin.basic.exception.NotFoundException;
import demo.admin.service.FileService;
import demo.core.domain.*;
import demo.core.persistence.CompanyMapper;
import demo.core.persistence.UserMapper;
import demo.core.service.CODE;
import demo.core.service.SMS;
import demo.ext.mybatis.Where;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joe on 11/9/14.
 */
@RestController
public class UserController extends JsonController{
    @Autowired
    private UserMapper userMapper;
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private SMS sms;
	@Autowired
	private CODE code;
    @Autowired
    protected FileService fileService;

	@RequestMapping("/user/userlist")
	public Object passList(String securephone,int page,String status,
						   @RequestParam(value = "startDate",required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate startDate,
						   @RequestParam(value = "endDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate endDate){
		Map<String, Object> map = new HashMap<>();
		map.put("userList", userMapper.pageAllUser(status,securephone,startDate,endDate, page, 10));
		map.put("securephone",securephone);
		map.put("status",status);
		map.put("startDate",startDate);
		map.put("endDate",endDate);
		return map;
	}

	@RequestMapping("/user/count")
	public Integer countData(String status,String securephone,
							 @RequestParam(value = "startDate",required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate startDate,
							 @RequestParam(value = "endDate",required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate endDate){
		if(securephone!=null&&securephone!=""){
			securephone= Where.$like$(securephone);
		}
		return userMapper.countAllUser(status,securephone,startDate,endDate);
	}

	@RequestMapping(value = "/user/downloadData")
	@VerifyAuthentication(Trader = true,Admin = true,Operation=true)
	public HttpEntity<byte[]> downloadUserData(String status,String securephone,
											   @RequestParam(value = "startDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate startDate,
											   @RequestParam(value = "endDate",required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate endDate) throws IOException, DocumentException {
		if(securephone!=null&&securephone!=""){
			securephone= Where.$like$(securephone);
		}
		List<Map<String,Object>> users= userMapper.userExport(status, securephone, startDate, endDate);
		String type = status+"用户数据";
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(type);
		HSSFRow row = sheet.createRow(0);
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		sheet.setVerticallyCenter(true);
		sheet.setHorizontallyCenter(true);
		String[] excelHeader = {"序号","公司名称","公司地址","注册时间","联系人电话"};
		for (int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(cellStyle);
			sheet.autoSizeColumn(i,true);
		}
		for(int i=0;i<users.size();i++){
			Map<String,Object> resultSet =  users.get(i);
			sheet.autoSizeColumn(i,true);
			row = sheet.createRow(i+1);
			row.setRowStyle(cellStyle);
			row.createCell(0).setCellValue(i+1);
			row.createCell(1).setCellValue(String.valueOf(resultSet.get("name")));
			row.createCell(2).setCellValue(String.valueOf(resultSet.get("address")));
			row.createCell(3).setCellValue(String.valueOf(resultSet.get("registertime")));
			row.createCell(4).setCellValue(String.valueOf(resultSet.get("securephone")));

		}
		File file = File.createTempFile(".xls",".xls");
		OutputStream out = new FileOutputStream(file);
		wb.write(out);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", URLEncoder.encode(type, "UTF-8")+LocalDate.now()+".xls");
		return new HttpEntity<byte[]>(FileUtils.readFileToByteArray(file), headers);
	}


	@RequestMapping("/user/edit")
	public Object editPwd(String securephone) throws Exception {
        User user = userMapper.getUserByPhone(securephone);
        if (user == null) throw new NotFoundException();
        String randompwd = code.CreateCode();
        sms.send(user.getSecurephone(), randompwd, "您好，您的随机密码是：", "【XX网】");
        userMapper.modifyPasswd(DigestUtils.md5Hex(randompwd), securephone);
		return true;
	}

	@RequestMapping("/user/account")
	public Object disableAccount(String securephone){
        User user = userMapper.getUserByPhone(securephone);
        if (user == null) throw new NotFoundException();
        if (user.isIsactive()) {
            userMapper.editUserAccount(false, securephone);
        } else {
            userMapper.editUserAccount(true, securephone);
        }
        return new Object(){
            public boolean isactive = !user.isIsactive();
            public boolean success = true;
        };
	}

	@RequestMapping("/user/view")
	public Object getUserInfo(String securephone){
        User user1 = userMapper.getUserByPhone(securephone);
        if(user1 == null) throw new NotFoundException();
        Company company1 = companyMapper.getCompanyByUserid(user1.getId());
        return new Object() {
            public boolean success = true;
            public User user = user1;
            public Company company = company1 == null ? null : company1;
            public List<CompanyVerify> compverifyList = company1 == null ? null : companyMapper.getVerifyList(company1.getId());
        };
	}

	@RequestMapping("/user/verifypass")
	public Object companyVerifyPass(int companyId, String remarks){
        Company company = companyMapper.getCompanyById(companyId);
        if (company == null) throw new NotFoundException();
        companyMapper.setCompVerify("审核通过", session.getAdmin().getUsername(), LocalDateTime.now(), remarks, companyId);
        companyMapper.setCompanyStatus("审核通过", remarks, companyId);
        userMapper.setUserVerifyStatus("审核通过", company.getUserid());
        companyMapper.addCompVerSus(new CompVerSus(company.getName(), company.getAddress(), company.getPhone(), company.getFax(), company.getLegalperson(), company.getBusinesslicense(), company.getIdentificationnumber(), company.getOrganizationcode(), company.getOperatinglicense(), company.getUserid(), company.getLegalpersonname(), company.getAccount(), company.getOpeningbank()));
		return true;
	}

	@RequestMapping("/user/verifyreject")
	public Object companyVerifyreject(int companyId, String remarks){
        Company company = companyMapper.getCompanyById(companyId);
        if (company == null) throw new NotFoundException();
        companyMapper.setCompVerify("审核未通过", session.getAdmin().getUsername(), LocalDateTime.now(), remarks, companyId);
        companyMapper.setCompanyStatus("审核未通过", remarks, companyId);
        userMapper.setUserVerifyStatus("审核未通过", company.getUserid());
		return true;
	}



	//保存或修改公司信息
	@RequestMapping(value = "/user/saveCompanyInfo", method = RequestMethod.POST)
	public Object saveCompany(@RequestParam("userid")int userid,
							  @RequestParam("name") String name,
							  @RequestParam("address") String address,
							  @RequestParam("invoicinginformation") String invoicinginformation,
							  @RequestParam("businesslicense") String businesslicense,
							  @RequestParam("identificationnumber") String identificationnumber,
							  @RequestParam("organizationcode") String organizationcode,
							  @RequestParam("operatinglicense") String operatinglicense,
                              @RequestParam("openinglicense") String openinglicense,
							  @RequestParam("legalpersonname") String legalpersonname,
							  @RequestParam("account") String account,
							  @RequestParam("openingbank") String openingbank,
							  @RequestParam("identificationnumword")String identificationnumword,
							  @RequestParam("zipcode")String zipcode){
		Company companyc = companyMapper.getCompanyByUserid(userid);
		Company company;
		User user = userMapper.getUserById(userid);
		if(companyc == null) {
			company = new Company(name, address, user.getSecurephone(), null, invoicinginformation, businesslicense, identificationnumber, organizationcode, operatinglicense, openinglicense, legalpersonname, account, openingbank, userid, identificationnumword, zipcode);
			companyMapper.addCompany(company);
		} else {
			String cname = (StringUtils.isNullOrEmpty(name) == true ? companyc.getName() : name);
			String caddress = (StringUtils.isNullOrEmpty(address) == true ? companyc.getAddress() : address);
			String cinvoicinginformation = (StringUtils.isNullOrEmpty(invoicinginformation) == true ? companyc.getInvoicinginformation() : invoicinginformation);
			String cbusinesslicense = (StringUtils.isNullOrEmpty(businesslicense) == true ? companyc.getBusinesslicense() : businesslicense);
			String cidentificationnumber = (StringUtils.isNullOrEmpty(identificationnumber) == true ? companyc.getIdentificationnumber() : identificationnumber);
			String corganizationcode = (StringUtils.isNullOrEmpty(organizationcode) == true ? companyc.getOrganizationcode() : organizationcode);
			String coperatinglicense = (StringUtils.isNullOrEmpty(operatinglicense) == true ? companyc.getOperatinglicense() : operatinglicense);
            String copeninglicense = (StringUtils.isNullOrEmpty(openinglicense) == true ? companyc.getOpeninglicense() : openinglicense);
			String clegalpersonname = (StringUtils.isNullOrEmpty(legalpersonname) == true ? companyc.getLegalpersonname() : legalpersonname);
			String caccount = (StringUtils.isNullOrEmpty(account) == true ? companyc.getAccount() : account);
			String copeningbank = (StringUtils.isNullOrEmpty(openingbank) == true ? companyc.getOpeningbank() : openingbank);
			String cidentificationnumword = (StringUtils.isNullOrEmpty(identificationnumword) == true ? companyc.getIdentificationnumword() : identificationnumword);
			String czipcode = (StringUtils.isNullOrEmpty(zipcode) == true ? companyc.getZipcode() : zipcode);
			company = new Company(cname, caddress, companyc.getPhone(), companyc.getFax(), cinvoicinginformation, cbusinesslicense, cidentificationnumber, corganizationcode, coperatinglicense, copeninglicense, clegalpersonname, caccount, copeningbank, userid, cidentificationnumword, czipcode);
			companyMapper.modifyCompany(company);
		}
		companyMapper.setCompanyStatus("待审核", null, companyMapper.getIdByUserid(userid));
        companyMapper.addCompVerify(new CompanyVerify("待审核", LocalDateTime.now(), companyMapper.getIdByUserid(userid), userid));
		userMapper.setUserVerifyStatus("待审核", userid);
		return true;
	}

	//保存公司图片
	@RequestMapping(value = "/saveCompanyPic", method = RequestMethod.POST)
	public Object saveCompanyPic(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<>();
        boolean success = false;
        if(file.getSize() / 1000 / 1000 <= 10) {
            response.setContentType("text/html");
            success = true;
            map.put("filePath", fileService.uploadPicture(file));
        }
        map.put("success", success);
        return map;
	}



}
