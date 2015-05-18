package demo.admin.controller;

import com.itextpdf.text.DocumentException;
import freemarker.template.TemplateException;
import demo.admin.annotation.VerifyAuthentication;
import demo.admin.basic.exception.NotFoundException;
import demo.admin.service.Freemarker;
import demo.admin.service.Session;
import demo.core.domain.*;
import demo.core.persistence.*;
import demo.core.service.PDF;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiangyang on 15-1-14.
 */

@Controller
public class CustomerController {

    @Autowired
    private MyCustomerMapper myCustomerMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private DemandMapper demandMapper;
    @Autowired
    private BuyMapper buyMapper;
    @Autowired
    private QuoteMapper quoteMapper;
    @Autowired
    private ManualSellMapper manualSellMapper;
    @Autowired
    private AreaportMapper areaportMapper;
    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Autowired
    private GroupBuyQualificationMapper groupBuyQualificationMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private GroupBuyOrderMapper groupBuyOrderMapper;
    @Autowired
    private GroupBuySupplyMapper groupBuySupplyMapper;
    @Autowired
    private Freemarker freemarker;
    @Autowired
    private Session session;

    private String contractContent;

    private String constractNo;


    private static final int pageSize= 10;
    @RequestMapping("/customer/list")
    @ResponseBody
    @VerifyAuthentication(Trader = true,Admin = true,Operation=true)
    public Object listCustomer(@RequestParam(value = "page",defaultValue = "1")Integer page) {
        return  myCustomerMapper.getAllCustomerList(session.getAdmin(),page, pageSize);

    }

    @RequestMapping("/customer/showCustomerDetail")
    @ResponseBody
    @VerifyAuthentication(Trader = true,Admin = true,Operation=true)
    public Customer showCustomerDetail(@RequestParam("id") Integer id) {
        return myCustomerMapper.load(id);
    }

    @RequestMapping("/customer/searchCustomer")
    @ResponseBody
    @VerifyAuthentication(Trader = true,TraderAssistant = true,Finance = true,Operation = true,Admin = true)
    public Object showCustomerList(@RequestParam("keyword") String keyword) {
        Customer customer = myCustomerMapper.customerSearch(keyword);
        return customer!=null?customer:Boolean.FALSE;
    }


    @RequestMapping("/customer/orderlist")
    @ResponseBody
    @VerifyAuthentication(Trader = true,Admin = true,Operation=true)
    public Object showCustomerOrders(@RequestParam(value = "status", required = false) EnumOrder status, @RequestParam(value = "paytype", required = false) EnumOrder paytype,@RequestParam(value = "page", defaultValue = "1")Integer page) {
        return orderMapper.getAllOrderList(page,pageSize,status,paytype,session.getAdmin());
    }

    @RequestMapping("/customer/demandlist")
    @ResponseBody
    @VerifyAuthentication(Trader = true,Admin = true,Operation=true)
    public Object showCustomerDemands(Demand demand,@RequestParam(value = "page", defaultValue = "1")Integer page) {
        return demandMapper.getAllDemandByDealerId(session.getAdmin(),demand, page, pageSize);
    }

    @RequestMapping("/customer/supplylist")
    @ResponseBody
    @VerifyAuthentication(Trader = true,Admin = true,Operation=true)
    public Object showCustomerSupply(@RequestParam(value = "page",defaultValue = "1")Integer page) {
        SellInfo supply = new SellInfo();
        return buyMapper.getAllSupplyList(session.getAdmin(),supply,page, pageSize);
    }
    @RequestMapping("/customer/quotelist")
    @ResponseBody
    @VerifyAuthentication(Trader = true,Admin = true,Operation=true)
    public Object showCustomerQuote(@RequestParam(value = "status", required = false) String status,@RequestParam(value = "page", required = false) Integer page) {
        return quoteMapper.getAllQuotesList(session.getAdmin(),status, page, pageSize);
    }
    @RequestMapping("/customer/manualsell")
    @ResponseBody
    @VerifyAuthentication(Trader = true,Admin = true,Operation=true)
    public Object showCustomerManualLookup(ManualSell manualSell,@RequestParam(value = "page", required = false) Integer page){
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("areas",areaportMapper.getAllArea());
        model.put("manualselllist", manualSellMapper.getAllManualSellList(manualSell, page, pageSize));
        return model;
    }
    //需求所有报价列表
    @RequestMapping("/customer/quoteresult")
    @ResponseBody
    @VerifyAuthentication(Trader = true,Admin = true,Operation=true)
    public Object showQuoteByDemandCode(@RequestParam("demandcode") String demandCode,@RequestParam(value = "page", required = false) Integer page){
        return quoteMapper.getQuoteByDemandCode(demandCode, null, page, pageSize);
    }
    //已中标的报价
    @RequestMapping("/customer/inbidquote")
    @ResponseBody
    @VerifyAuthentication(Trader = true,Admin = true,Operation=true)
    public Object showBidQuoteByDemandCode(@RequestParam("demandcode") String demandCode,@RequestParam(value = "page", required = false) Integer page){
        final String status = "已中标";
        return quoteMapper.getQuoteByDemandCode(demandCode,status, page, pageSize);
    }
    @RequestMapping(value = "/customer/manualselldetail/{id}")
    @ResponseBody
    @VerifyAuthentication(Trader = true,Admin = true,Operation=true)
    public Object showManualsellDetail(@PathVariable String id) {
        ManualSell manualSell=manualSellMapper.loadByManualId(id);
        if(manualSell==null){
            throw new NotFoundException();
        }
       return  manualSell;
    }

    //撮合交易--供应信息
    @RequestMapping("/customer/bringdealsellinfo")
    @ResponseBody
    @VerifyAuthentication(Trader = true,TraderAssistant = true,Finance = true,Operation = true,Admin = true)
    public Object bringDealSellInfo(SellInfo sellInfo,@RequestParam(value = "page", defaultValue = "1", required = false) Integer page){
        Map<String,Object> model = new HashMap<String,Object>();
        List<Areaport> areas = areaportMapper.getAllArea();
        List<Dictionary> deliverymodeList = dictionaryMapper.getDeliverymodes();
        List<Dictionary> cocaltypeList = dictionaryMapper.getAllCoalTypes();
        model.put("area",areas);
        model.put("deliverymodeList",deliverymodeList);
        model.put("cocaltypeList", cocaltypeList);
        model.put("sellinfolist", buyMapper.getAllSupplyList(session.getAdmin(),sellInfo, page, pageSize));
       return model;
    }


    //撮合交易--需求信息
    @RequestMapping("/customer/bringdealdemandinfo")
    @ResponseBody
    @VerifyAuthentication(Trader = true,TraderAssistant = true,Finance = true,Operation = true,Admin = true)
    public Object bringDealDemandInfo(Demand demand,@RequestParam(value = "page", defaultValue = "1", required = false) Integer page){
        Map<String,Object> model = new HashMap<String,Object>();
        List<Areaport> areas = areaportMapper.getAllArea();
        List<Dictionary> deliverymodeList = dictionaryMapper.getDeliverymodes();
        List<Dictionary> cocaltypeList = dictionaryMapper.getAllCoalTypes();
        model.put("area",areas);
        model.put("deliverymodeList",deliverymodeList);
        model.put("cocaltypeList", cocaltypeList);
        model.put("demandlist", demandMapper.getAllDemandByDealerId(session.getAdmin(), demand, page, pageSize));
        return model;
    }

    //团购资质
    @RequestMapping("/customer/teamorderQualification")
    @ResponseBody
    @VerifyAuthentication(Trader = true,Admin = true,Operation=true)
    public Object lookteamOrderQualification(GroupBuyQualification groupBuyQualification,@RequestParam(value = "page", defaultValue = "1", required = false)Integer page){
        return groupBuyQualificationMapper.listAllQualification(groupBuyQualification,page,pageSize);
    }
    //团购订单
    @RequestMapping("/customer/teamorder")
    @ResponseBody
    @VerifyAuthentication(Trader = true,Admin = true,Operation=true)
    public Object lookteamOrder(GroupBuyOrder groupBuyOrder,@RequestParam(value = "page", defaultValue = "1", required = false)Integer page){
        return groupBuyOrderMapper.pageAllGroupBuyOrder(groupBuyOrder,page,pageSize);
    }

    //团购信息详细
    @RequestMapping("/customer/teamorderDetail")
    @ResponseBody
    @VerifyAuthentication(Trader = true,Admin = true,Operation=true)
    public Object lookteamOrderDetail(@RequestParam(value="supplyCode")String supplyCode){
        return groupBuySupplyMapper.loadBygroupBycode(supplyCode);
    }
    @RequestMapping("/customer/depositContract")
    @VerifyAuthentication(Trader = true,Admin = true,Operation=true)
    public String lookDepositContract(HttpServletRequest request,GroupBuyQualification groupBuyQualification,Model model) throws IOException, TemplateException {
            Company company = companyMapper.getCompanyByUserid(groupBuyQualification.getUserid());
            constractNo= groupBuyQualification.getQualificationcode();
            //合同内容
             contractContent= freemarker.render("/contract/depositTemplate",new HashMap<String,Object>(){{
                 put("qualificationNo",constractNo);
                 put("phone",company.getPhone());
                 put("fax",company.getFax());
                 put("createtime", LocalDate.now());
                 put("companyname",company.getName());
                 put("companyaddress",company.getAddress());
                 put("legalpersonname",company.getLegalpersonname());
                 put("companyaccount",company.getAccount());
                 put("companyzipcode", company.getZipcode());
                 put("companyopeningbank", company.getOpeningbank());
                 put("companyidentificationnumword", company.getIdentificationnumword());
                 put("year",LocalDate.now().getYear());
                 put("month",LocalDate.now().getMonthValue());
                 put("day",LocalDate.now().getDayOfMonth());
                 put("ctx", request.getScheme() + "://"+ request.getServerName()+":"+request.getServerPort());

            }});
                model.addAttribute("contract", contractContent);
                return "depositContract";
    }
    @RequestMapping("/customer/lookagreementContract")
    @VerifyAuthentication(Trader = true,Admin = true,Operation=true)
    public String lookagreementContract(HttpServletRequest request,GroupBuyOrder order,Model model) throws IOException, TemplateException {
        Company company = companyMapper.getCompanyByUserid(order.getUserid());
        constractNo= order.getPerformancecode();
        //合同内容
        contractContent= freemarker.render("/contract/agreementTemplate",new HashMap<String,Object>(){{
            put("qualificationNo",constractNo);
            put("phone",company.getPhone());
            put("fax",company.getFax());
            put("createtime", LocalDate.now());
            put("companyname",company.getName());
            put("companyaddress",company.getAddress());
            put("legalpersonname",company.getLegalpersonname());
            put("companyaccount",company.getAccount());
            put("companyzipcode", company.getZipcode());
            put("companyopeningbank", company.getOpeningbank());
            put("companyidentificationnumword", company.getIdentificationnumword());
            put("year",LocalDate.now().getYear());
            put("month",LocalDate.now().getMonthValue());
            put("day",LocalDate.now().getDayOfMonth());
            put("ctx", request.getScheme() + "://"+ request.getServerName()+":"+request.getServerPort());

        }});
        model.addAttribute("contract",contractContent);
        return "depositContract";
    }

    //下载电子合同
    @RequestMapping("/customer/download/constract")
    @VerifyAuthentication(Trader = true,Admin = true,Operation=true)
     public HttpEntity<byte[]> downloadContract() throws IOException, DocumentException {
        //controller是单例的，constract定义为全局变量，方便拿markdown的字符串.
        File file = PDF.create(contractContent);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", constractNo+".pdf");
        return new HttpEntity<byte[]>(FileUtils.readFileToByteArray(file), headers);
     }
    //下载委托人工excel
    @RequestMapping(value = "/customer/downloadmanualsell")
    @VerifyAuthentication(Trader = true,Admin = true,Operation=true)
    public HttpEntity<byte[]> downloadManualsell(ManualSell manualSell) throws IOException, DocumentException {
       List<ManualSell> manualSells= manualSellMapper.listAllManualsell(manualSell, 1000, 0);
        String type = manualSell.getType()==true?"委托人工找货":"委托人工销售";
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(type);
        HSSFRow row = sheet.createRow(0);
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        sheet.setVerticallyCenter(true);
        sheet.setHorizontallyCenter(true);
        String[] excelHeader = {"序号","酒类","酒精度数","含糖量","空干基挥发","提货地点","需求数量","提货方式","联系人","联系方式","公司名称"};
        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(cellStyle);
            sheet.autoSizeColumn(i,true);
        }
        for(int i=0;i<manualSells.size();i++){
            ManualSell manual =  manualSells.get(i);
            sheet.autoSizeColumn(i,true);
            row = sheet.createRow(i+1);
            row.setRowStyle(cellStyle);
            row.createCell(0).setCellValue(i+1);
            row.createCell(1).setCellValue(manual.getCoalType());
            row.createCell(2).setCellValue(manual.getLowcalorificvalue());
            row.createCell(3).setCellValue(String.valueOf(manual.getReceivebasissulfur()));
            row.createCell(4).setCellValue(String.valueOf(manual.getAirdrybasisvolatile()));
            row.createCell(5).setCellValue(manual.getDeliveryProvince() + manual.getDeliveryAddr());
            row.createCell(6).setCellValue(manual.getDemandAmount());
            row.createCell(7).setCellValue(manual.getDeliveryMode());
            row.createCell(8).setCellValue(manual.getContactName());
            row.createCell(9).setCellValue(manual.getPhone());
            row.createCell(10).setCellValue(manual.getCompanyName());
        }
        File file = File.createTempFile(".xls",".xls");
        OutputStream out = new FileOutputStream(file);
        wb.write(out);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(type, "UTF-8")+LocalDate.now()+".xls");
        return new HttpEntity<byte[]>(FileUtils.readFileToByteArray(file), headers);
    }
    @RequestMapping(value = "/customer/countmanualsell",method = RequestMethod.POST)
    @ResponseBody
    public Integer countManualsell(ManualSell manualSell){
            return manualSellMapper.countAllManualsell(manualSell);
    }

    /**
     * 设置文字在单元格里面的 对齐方式
     * @param cellStyle
     * @return
     */
    public static CellStyle alignmentDecorate(CellStyle cellStyle){
        //设置上下
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        //设置左右
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        return cellStyle;
    }
}
