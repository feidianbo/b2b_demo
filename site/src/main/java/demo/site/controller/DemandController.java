package demo.site.controller;

import demo.core.domain.*;
import demo.core.persistence.*;
import demo.core.util.DropdownListUtil;
import demo.core.util.PageQueryParam;
import demo.site.basic.JsonController;
import demo.site.basic.annotation.LoginRequired;
import demo.site.basic.exception.NotFoundException;
import demo.site.service.BuyService;
import demo.site.service.Session;
import org.eclipse.jetty.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fanjun on 14-11-25.
 */
@Controller
public class DemandController extends JsonController {
    @Autowired
    protected DictionaryMapper dictionaryMapper;
    @Autowired
    protected DemandMapper demandMapper;
    @Autowired
    private Session session;
    @Autowired
    protected QuoteMapper quoteMapper;
    @Autowired
    protected CompanyMapper companyMapper;
    @Autowired
    protected AreaportMapper areaportMapper;
    @Autowired
    protected MydemandMapper mydemandMapper;
    @Autowired
    protected UserMapper userMapper;
    @Autowired
    private BuyService buyService;

    //没用实体类来接受参数,只取需要的参数Form接受
    public static class DemandForm {
        private String coaltype;                   //煤炭种类
        private String demandcode;                 //需求编号
        private int NCV;                        //低位热值
        @Null
        private BigDecimal ADS;                     //空干基硫份
        private BigDecimal RS;                     //含糖量
        private BigDecimal TM;                      //酒类指标4
        @Null
        private BigDecimal IM;                      //酒类指标5
        private BigDecimal ADV;                     //酒类指标2
        @Null
        private BigDecimal RV;                      //酒类指标3
        @Null
        private int AFT;                            //酒类指标7
        @Null
        private BigDecimal ASH;                     //酒类指标6
        @Null
        private int HGI;                            //酒类指标8
        private String deliveryplace;              //提货地点
        private String demandamount;               //需求数量
        @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
        private LocalDate deliverydate;               //提货时间
        @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
        private LocalDate deliverydatestart;          //提货时间开始
        @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
        private LocalDate deliverydateend;            //提货时间截至
        private String deliverymode;                    //提货方式
        private String inspectionagency;            //检验机构
        private String deliveryprovince;            //提货省份
        private String otherorg;                    //其它检验机构
        private String otherplace;                  //其它提货地点

        @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
        private LocalDate quoteenddate;             //报价截至日期
        private String releasecomment;              //发布备注

        public String getDemandcode() {
            return demandcode;
        }

        public void setDemandcode(String demandcode) {
            this.demandcode = demandcode;
        }

        public String getDeliveryplace() {
            return deliveryplace;
        }

        public void setDeliveryplace(String deliveryplace) {
            this.deliveryplace = deliveryplace;
        }

        public String getDemandamount() {
            return demandamount;
        }

        public void setDemandamount(String demandamount) {
            this.demandamount = demandamount;
        }

        public String getDeliverymode() {
            return deliverymode;
        }

        public void setDeliverymode(String deliverymode) {
            this.deliverymode = deliverymode;
        }

        public String getInspectionagency() {
            return inspectionagency;
        }

        public void setInspectionagency(String inspectionagency) {
            this.inspectionagency = inspectionagency;
        }

        public String getDeliveryprovince() {
            return deliveryprovince;
        }

        public void setDeliveryprovince(String deliveryprovince) {
            this.deliveryprovince = deliveryprovince;
        }

        public String getOtherorg() {
            return otherorg;
        }

        public void setOtherorg(String otherorg) {
            this.otherorg = otherorg;
        }

        public String getOtherplace() {
            return otherplace;
        }

        public void setOtherplace(String otherplace) {
            this.otherplace = otherplace;
        }

        public String getCoaltype() {
            return coaltype;
        }

        public void setCoaltype(String coaltype) {
            this.coaltype = coaltype;
        }

        public LocalDate getDeliverydate() {
            return deliverydate;
        }

        public void setDeliverydate(LocalDate deliverydate) {
            this.deliverydate = deliverydate;
        }

        public LocalDate getDeliverydatestart() {
            return deliverydatestart;
        }

        public void setDeliverydatestart(LocalDate deliverydatestart) {
            this.deliverydatestart = deliverydatestart;
        }

        public LocalDate getDeliverydateend() {
            return deliverydateend;
        }

        public void setDeliverydateend(LocalDate deliverydateend) {
            this.deliverydateend = deliverydateend;
        }

        public LocalDate getQuoteenddate() {
            return quoteenddate;
        }

        public void setQuoteenddate(LocalDate quoteenddate) {
            this.quoteenddate = quoteenddate;
        }

        public int getNCV() {
            return NCV;
        }

        public void setNCV(int NCV) {
            this.NCV = NCV;
        }

        public BigDecimal getADS() {
            return ADS;
        }

        public void setADS(BigDecimal ADS) {
            this.ADS = ADS;
        }

        public BigDecimal getRS() {
            return RS;
        }

        public void setRS(BigDecimal RS) {
            this.RS = RS;
        }

        public BigDecimal getTM() {
            return TM;
        }

        public void setTM(BigDecimal TM) {
            this.TM = TM;
        }

        public BigDecimal getIM() {
            return IM;
        }

        public void setIM(BigDecimal IM) {
            this.IM = IM;
        }

        public BigDecimal getADV() {
            return ADV;
        }

        public void setADV(BigDecimal ADV) {
            this.ADV = ADV;
        }

        public BigDecimal getRV() {
            return RV;
        }

        public void setRV(BigDecimal RV) {
            this.RV = RV;
        }

        public int getAFT() {
            return AFT;
        }

        public void setAFT(int AFT) {
            this.AFT = AFT;
        }

        public BigDecimal getASH() {
            return ASH;
        }

        public void setASH(BigDecimal ASH) {
            this.ASH = ASH;
        }

        public int getHGI() {
            return HGI;
        }

        public void setHGI(int HGI) {
            this.HGI = HGI;
        }

        public String getReleasecomment() {
            return releasecomment;
        }

        public void setReleasecomment(String releasecomment) {
            this.releasecomment = releasecomment;
        }
    }

    @RequestMapping(value = "/sell", method = RequestMethod.GET)
    public String sell(Integer coaltype,
                       @RequestParam(value="area", required = false, defaultValue = "0") Integer area,
                       @RequestParam(value="province", required = false, defaultValue = "0")Integer province,
                       @RequestParam(value="port", required = false, defaultValue = "0")Integer port,
                       Integer lowHotValue,Integer highHotValue, BigDecimal lowSulfurContent,BigDecimal highSulfurContent,
                      // @RequestParam(value="orderByColumn", required = false, defaultValue = "releasetime")String orderByColumn,
                       @RequestParam(value="pagesize", required = false, defaultValue = "10")int pagesize,
                       PageQueryParam param, Map<String, Object> model){

        //煤种暂时没有id和编号之类的.
        String coalWord = null;
        if (coaltype != null) {
            if (coaltype != 0) {
                coalWord = dictionaryMapper.getCaolNameByCoalId(coaltype);
            }
        }
        /* 因为查询可能会选择其它,数字转化字符串会带逗号,取最大的三位数999
           实现页面selected选择找到对比值*/
        int otherDeliveryPlace = 0;
        if (port != null) {
            if (port != 0) {
                if(port == 999){
                    otherDeliveryPlace = 999;
                }
            }
        }

        param.setPageSize(pagesize);
        /*//releasetime创建时间.用了数字代替参数值,因为文字传入存在单双引号的问题.
        int orderByInt = 0;
        if(orderByColumn.equals("NCV")){
            orderByInt = 1;
        }
        if(orderByColumn.equals("RS")){
            orderByInt = 2;
        }*/
        int totalCount = demandMapper.countDemandsByCheck(coalWord, area, province, port, otherDeliveryPlace, lowHotValue, highHotValue, lowSulfurContent, highSulfurContent);
        if(param.getPage() > (totalCount / param.getPageSize() + 1)) param.setPage(totalCount / param.getPageSize() + 1);
        List<Demand> demandList = demandMapper.getDemandsByCheck(coalWord, area,province, port,otherDeliveryPlace,lowHotValue, highHotValue,
                lowSulfurContent, highSulfurContent,param.getPageSize(), param.getIndexNum());

        if (demandList != null && demandList.size() > 0) {
            model.put("demandList", demandList);
        }

        List<Dictionary> coalList = dictionaryMapper.getAllCoalTypes();
        List<Areaport> areatList = areaportMapper.getAllArea();
        List<Areaport> provincesList = null;

        if (area != null && area != 0) {
            provincesList = areaportMapper.getProvincesOrPortsByParentid(area);
        }else{
            provincesList = areaportMapper.getAllProvince();
        }
        model.put("coalList", coalList);
        model.put("areatList", areatList);
        model.put("provincesList", provincesList);

        if (province != null && province != 0) {
            List<Areaport> portsList = areaportMapper.getProvincesOrPortsByParentid(province);
            Areaport areaport = new Areaport();
            areaport.setId(999);
            areaport.setName("其它");
            portsList.add(areaport);
            model.put("portsList", portsList);
        }
        model.put("totalCount", totalCount);
        model.put("pageNumber", param.getPage());
        model.put("coaltype", String.valueOf(coaltype == null ? "0" : coaltype));
        model.put("area", String.valueOf(area));
        model.put("province", String.valueOf(province));
        model.put("port", String.valueOf(port));
        model.put("lowHotValue",String.valueOf(lowHotValue == null ? "0":lowHotValue));
        model.put("highHotValue", String.valueOf(highHotValue == null ? "10000" :highHotValue));
        model.put("lowSulfurContent", String.valueOf(lowSulfurContent == null ? "0" : lowSulfurContent));
        model.put("highSulfurContent", String.valueOf(highSulfurContent == null ? "10" : highSulfurContent));
       // model.put("orderByColumn",orderByColumn);
        model.put("pagesize", param.getPageSize());

        return "sell";
    }

    //检查公司信息是否完整
    @RequestMapping(value = "/checkCompanyInfo", method = RequestMethod.POST)
    @ResponseBody
    public String CheckCompanyInfo() {
        String message = null;
        if (session.getUser() != null) {
            User u = userMapper.getUserById(session.getUser().getId());
            if (u.getVerifystatus().equals("待完善信息") || u.getVerifystatus().equals("")) {
                message = "improve";
            } else if (u.getVerifystatus().equals("待审核")) {
                message = "verifying";
            } else if (u.getVerifystatus().equals("审核未通过")) {
                message = "notPass";
            } else {
                message = "success";
            }
            } else {
                message = "notLogin";
        }
        return JSON.toString(message);
    }

    //检查客户是否对某条需求报价过,检查是否是客户自己发布的信息.
    @RequestMapping(value = "/checkIsQuoted", method = RequestMethod.POST)
    @LoginRequired
    @ResponseBody
    public String checkIsQuoted(@RequestParam("demandid") Integer demandid) {
        String message = null;
        int countQuoted = quoteMapper.countQuotedByUserIdAndDemandid(session.getUser().getId(),demandid);
        if (countQuoted == 1) {
            message = "quoted";
        } else {
            message = "gotoquote";
        }

        int countMydemand = demandMapper.countMydemandByDemandidAndUserid(demandid,session.getUser().getId());
        if(countMydemand == 1){
            message = "mydemand";
        }
        return JSON.toString(message);
    }

    //跳转到报价或查看页面
    @RequestMapping("/sell/gotoQuote")
    public String gotoQuote(@RequestParam(value="id", required = true)Integer id,
                            String reqsource, Map<String, Object> model) {
        Demand demand = demandMapper.getDemandById(id);
        if(demand == null){
            throw new NotFoundException();
        }
        demandMapper.setPageViewTimesById(demand.getViewtimes()+1, id);
        model.put("demand", demandMapper.getDemandById(id));
        model.put("reqsource", reqsource);
        return "sellInfo";
    }

    //需求详情页面报价
    @RequestMapping(value = "/quote", method = RequestMethod.POST)
    @LoginRequired
    @ResponseBody
    public String demandQuote(@RequestParam("id") Integer id, @RequestParam("supplyton") Integer supplyton,
                              @RequestParam("quote") Integer quote) {
        Quote q = quoteMapper.getQuoteByUserIdAndDemandid(session.getUser().getId(),id);
        String message = "success";
        if(q == null) {
            Demand demand = demandMapper.getDemandById(id);
            if(demand != null) {
                Company company = companyMapper.getCompanyByUserid(session.getUser().getId());
                Quote newQuote = new Quote(session.getUser().getId(), demand.getId(), demand.getDemandcode(),
                        supplyton, quote, LocalDateTime.now(), "报价中", demand.getDeliverymode(), demand.getDeliverydate(), demand.getDeliverydatestart(), demand.getDeliverydateend(), demand.getQuoteenddate(),
                        company.getName(), demand.getTraderphone());
                quoteMapper.addQuote(newQuote);
                //主表的报价人数加1
                demandMapper.plusQuotenum(demand.getDemandcode());
                //我的需求表报价人数加1
                mydemandMapper.plusQuotenum(demand.getDemandcode());
            }else{
                message = "notExist";
            }
        }else{
            quoteMapper.modifyQuoteByQuoteid(supplyton,quote,q.getId());
        }
        return JSON.toString(message);
    }

    //查询热值和硫分区间返回过滤的结果集做查询区间
    //返回高热值结果集
    @RequestMapping(value = "/getHighHeatValue", method = RequestMethod.POST)
    @ResponseBody
    public List getHighHeatValue(@RequestParam("lowHotValue") Integer lowHotValue) {
        if(lowHotValue == 1){
            lowHotValue = 3500;
        }
        List list = DropdownListUtil.genMapIntList(lowHotValue);
        return list;
    }

    //返回高硫份结果集
    @RequestMapping(value = "/getHighSulfurContent", method = RequestMethod.POST)
    @ResponseBody
    public List getHighSulfurContent(@RequestParam("lowSulfurContent") String lowSulfurContent) {
        if(lowSulfurContent.equals("1")){
            lowSulfurContent = "0.1";
        }
        List decilmalList = DropdownListUtil.genMapDoubleList(Double.parseDouble(lowSulfurContent));
        return decilmalList;
    }

    //发布需求
    @RequestMapping("/buy/releaseDemand")
    @LoginRequired
    public String releaseDemand(Map<String, Object> model, String demandcode) {
            Demand demand = demandMapper.getDemandByDemandcodeAndUserid(demandcode, session.getUser().getId());
            List<Areaport> portsList = null;
            if (demand != null) {
                portsList = areaportMapper.getProcvincesOrPortsByParentname(demand.getDeliveryprovince());
                model.put("demand", demand);
            }else{
                portsList = areaportMapper.getProcvincesOrPortsByParentid(5);
            }
            List<Dictionary> cocaltypeList = dictionaryMapper.getAllCoalTypes();
            List<Areaport> provincesList = areaportMapper.getAllProvince();

            Areaport areaport = new Areaport("其它", "other");
            portsList.add(areaport);

           // List<Areaport> areaList = areaportMapper.getAllArea();
            List<Dictionary> deliverymodeList = dictionaryMapper.getDeliverymodes();
            List<Dictionary> orgList = dictionaryMapper.getAllInspectionagencys();
            List<Dictionary> inspectionagencyList = new ArrayList<>();
            Dictionary d1 = new Dictionary("nothing", "无");
            Dictionary d2 = new Dictionary("ohter", "其它");
            inspectionagencyList.add(d1);
            for (int i = 0; i < orgList.size(); i++) {
                inspectionagencyList.add(orgList.get(i));
            }
            inspectionagencyList.add(d2);

            model.put("cocaltypeList", cocaltypeList);
          //  model.put("areaList", areaList);
            model.put("provincesList", provincesList);
            model.put("portsList", portsList);
            model.put("deliverymodeList", deliverymodeList);
            model.put("inspectionagencyList", inspectionagencyList);
            return "releaseDemand";
    }

    //根据父id获取子集-省份
    @RequestMapping(value = "/getProvincesByParentid", method = RequestMethod.POST)
    @ResponseBody
    public List<Areaport> getProcvincesByParentid(@RequestParam("id") Integer id) {
        List<Areaport> provincesList = null;
        if(id != 0) {
            provincesList = areaportMapper.getProvincesOrPortsByParentid(id);
        }else{
            provincesList = areaportMapper.getAllProvince();
        }
        return provincesList;
    }

   /* //根据父id获取子集-港口
    @RequestMapping(value = "/getPortsByParentid", method = RequestMethod.POST)
    @ResponseBody
    public List<Areaport> getPortsByParentid(@RequestParam("id") Integer id) {
        List<Areaport> portsList = areaportMapper.getProvincesOrPortsByParentid(id);
        Areaport areaport = new Areaport("其它", "other");
        portsList.add(areaport);
        return portsList;
    }*/

    //根据省份id获取子集-港口和地区
    @RequestMapping(value = "/getPortsByParentid", method = RequestMethod.POST)
    @ResponseBody
    public Object getPortsByParentid(@RequestParam("id") Integer id) {
        Map map = new HashMap<>();
        List<Areaport> portsList = areaportMapper.getProvincesOrPortsByParentid(id);
        Areaport areaport = new Areaport("其它", "other");
        portsList.add(areaport);
        map.put("portsList",portsList);
        Areaport  area = areaportMapper.getAreaByProvinceId(id);
        map.put("area",area);
        return map;
    }

    //根据父名称获取子集-省份
    @RequestMapping(value = "/getProvincesByParentname", method = RequestMethod.POST)
    @ResponseBody
    public List<Areaport> getProcvincesByParentname(@RequestParam("name") String name) {
        List<Areaport> provincesList = null;
        if(!name.equals("全部")) {
           provincesList = areaportMapper.getProcvincesOrPortsByParentname(name);
        }else{
            provincesList = areaportMapper.getAllProvince();
        }
        return provincesList;
    }

    //根据父名称获取子集-港口
    @RequestMapping(value = "/getPortsByParentname", method = RequestMethod.POST)
    @ResponseBody
    public List<Areaport> getPortsByParentname(@RequestParam("name") String name) {
        List<Areaport> portsList = areaportMapper.getProcvincesOrPortsByParentname(name);
        Areaport areaport = new Areaport("其它", "other");
        portsList.add(areaport);
        return portsList;
    }

    //通过省份id获取地区对象
    @RequestMapping(value = "/getAreaByProvinceId", method = RequestMethod.POST)
    @ResponseBody
    public Object getAreaByProvinceId(@RequestParam("provinceid") int provinceid) {
        Areaport  area = areaportMapper.getAreaByProvinceId(provinceid);
        List<Areaport> areaList = areaportMapper.getAllArea();
        Map map = new HashMap<>();
        map.put("area",area);
        return map;
    }

    //通过省份名称获取地区对象和集合
    @RequestMapping(value = "/getAreaByProvinceName", method = RequestMethod.POST)
    @ResponseBody
    public Object getAreaByProvinceName(@RequestParam("provincename") String  provincename) {
        Areaport  area = areaportMapper.getAreaByProvincename(provincename);
        List<Areaport> areaList = areaportMapper.getAllArea();
        Map map = new HashMap<>();
        map.put("area",area);
        return map;
    }

   /* //通过省份id获取地区对象
    @RequestMapping(value = "/getAreaByProvinceId", method = RequestMethod.POST)
    @ResponseBody
    public Object getAreaByProvinceId(@RequestParam("provinceid") int provinceid) {
        Areaport  area = areaportMapper.getAreaByProvinceId(provinceid);
        List<Areaport> areaList = areaportMapper.getAllArea();
        Map map = new HashMap<>();
        map.put("area",area);
        map.put("areaList",areaList);
        return map;
    }

    //通过省份名称获取地区对象和集合
    @RequestMapping(value = "/getAreaByProvinceName", method = RequestMethod.POST)
    @ResponseBody
    public Object getAreaByProvinceName(@RequestParam("provincename") String  provincename) {
        Areaport  area = areaportMapper.getAreaByProvincename(provincename);
        List<Areaport> areaList = areaportMapper.getAllArea();
        Map map = new HashMap<>();
        map.put("area",area);
        map.put("areaList",areaList);
        return map;
    }*/

    //需求发布校验
    @RequestMapping(value = "/checkDemand", method = RequestMethod.POST)
    @LoginRequired
    @ResponseBody
    public Object checkDemand(@Valid DemandForm demandForm ,BindingResult result) {
        Demand demand = new Demand();
        demand.setUserid(session.getUser().getId());
        Company company = companyMapper.getCompanyByUserid(session.getUser().getId());
        demand.setDemandcustomer(company.getName());
        demand.setCoaltype(demandForm.coaltype);
        demand.setNCV(demandForm.NCV);
        demand.setADS(demandForm.ADS == null ? new BigDecimal(0) : demandForm.ADS);
        demand.setRS(demandForm.RS);
        demand.setTM(demandForm.TM);
        demand.setIM(demandForm.IM == null ? new BigDecimal(0) : demandForm.IM);
        demand.setADV(demandForm.ADV);
        demand.setRV(demandForm.RV == null ? new BigDecimal(0) : demandForm.RV);
        demand.setAFT(demandForm.AFT == 0 ? 0 : demandForm.AFT);
        demand.setASH(demandForm.ASH == null ? new BigDecimal(0) : demandForm.ASH);
        demand.setHGI(demandForm.HGI == 0 ? 0 : demandForm.HGI);
        Areaport areaport = areaportMapper.getAreaByProvincename(demandForm.deliveryprovince);
        demand.setDeliverydistrict(areaport.getName());
        demand.setDeliveryprovince(demandForm.deliveryprovince);
        demand.setDeliveryplace(demandForm.deliveryplace);  //提货地点可能是"其它"
        if (demandForm.otherplace != null && demandForm.otherplace != "" && !demandForm.otherplace.equals(",")) {
            String otherplaceArr[] = demandForm.otherplace.split(",");
            demand.setOtherplace(otherplaceArr[0]);
        } else {
            demand.setOtherplace(null);
        }
        demand.setDemandamount(Integer.parseInt(demandForm.demandamount));
        demand.setDeliverymode(demandForm.deliverymode);
        if (demandForm.deliverymode.equals("场地自提")) {   //提货时间可以不用判断提货方式来set,如果不用,就要判断是否为空.
            demand.setDeliverydatestart(demandForm.deliverydatestart);
            demand.setDeliverydateend(demandForm.deliverydateend);
        } else {
            demand.setDeliverydate(demandForm.deliverydate);
        }

        demand.setResidualdemand(Integer.parseInt(demandForm.demandamount));
        demand.setInspectionagency(demandForm.inspectionagency);
        if (demandForm.otherorg != null && demandForm.otherorg != "" && !demandForm.otherorg.equals(",")) {
            String otherorgArr[] = demandForm.otherorg.split(",");
            demand.setOtherorg(otherorgArr[0]);
        } else {
            demand.setOtherorg(null);
        }
        //报价截止日期
        demand.setQuoteenddate(demandForm.quoteenddate);
        demand.setNoshowdate(demandForm.quoteenddate.plusDays(7));  //7天后
        demand.setReleasetime(LocalDateTime.now());
        demand.setCheckstatus("待审核");
        demand.setTradestatus("未开始报价");
        demand.setComment(null);
        demand.setReleasecomment(demandForm.releasecomment);
        //设置需求区域id、省份id、港口id
        demand.setRegionId(areaportMapper.getParentIdByName(demandForm.getDeliveryprovince()));
        demand.setProvinceId(areaportMapper.getIdByName(demandForm.getDeliveryprovince()));
        Integer portId=areaportMapper.getIdByName(demandForm.getDeliveryplace());
        portId=portId==null?-1:portId;
        demand.setPortId(portId);

        Demand d1 = demandMapper.getDemandByDemandcodeAndUserid(demandForm.demandcode, session.getUser().getId());
        Map map = new HashMap();
        if (d1 == null) {
            demandMapper.addDemand(demand);
            Demand d2 = demandMapper.getDemandById(demand.getId());
            //需求编号改为数据库生成;这里传需求编号为修改做准备
            map.put("demandcode", d2.getDemandcode());
            //插入到我的需求表,对象的取值可以从页面传的参数取,也可以从查出来的对象d2中取.
            Mydemand mydemand = new Mydemand(session.getUser().getId(), d2.getDemandcode(),
                    demand.getReleasetime(), demand.getQuoteenddate(), demand.getDemandamount(), "审核中",
                    demand.getDeliverydate(), demand.getDeliverydatestart(), demand.getDeliverydateend());
            mydemandMapper.addMydemand(mydemand);

        } else {
            if(d1.getCheckstatus().equals("审核未通过")){
                demandMapper.addDemand(demand);
                Demand d2 = demandMapper.getDemandById(demand.getId());
                map.put("demandcode", d2.getDemandcode());
                Mydemand mydemand = new Mydemand(session.getUser().getId(), d2.getDemandcode(),
                        demand.getReleasetime(), demand.getQuoteenddate(), demand.getDemandamount(), "审核中",
                        demand.getDeliverydate(), demand.getDeliverydatestart(), demand.getDeliverydateend());
                mydemandMapper.addMydemand(mydemand);
                mydemandMapper.deleteMyDemandByDemandcode(d1.getDemandcode(),session.getUser().getId());
            }else{
                demand.setId(d1.getId());
                demand.setDemandcode(d1.getDemandcode());
                demandMapper.modifyDemand(demand);
                map.put("demandcode", demandForm.demandcode);

                //更新我的需求表
                Mydemand mydemand = new Mydemand(demandForm.demandcode, demand.getReleasetime(), demand.getQuoteenddate(),
                        demand.getDemandamount(), "审核中", demand.getDeliverydate(), demand.getDeliverydatestart(), demand.getDeliverydateend());
                mydemandMapper.modifyMyDemand(mydemand);
            }
        }
        boolean success = true;
        map.put("success", success);
        return map;
    }

    //跳转到需求发布校验页面
    @RequestMapping(value = "/buy/gotoCheck", method = RequestMethod.GET)
    public String gotoCheck(@RequestParam(value="demandcode", required = true) String demandcode, Map<String, Object> model) {
        Demand demand = demandMapper.getDemandByDemandcodeAndUserid(demandcode,session.getUser().getId());
        model.put("demand", demand);
        return "checkDemandInfo";
    }

    //保存需求(更改发布状态)
    @RequestMapping(value = "/saveDemand", method = RequestMethod.POST)
    @ResponseBody
    public String saveDemand(@RequestParam("demandcode") String demandcode) {
        demandMapper.modifyReleaseStatusByDemandcode(demandcode);
        return JSON.toString("success");
    }

    //报价状态-中标
    @RequestMapping(value = "/quoteBid", method = RequestMethod.POST)
    @ResponseBody
    public String quoteBid(@RequestParam("quoteid") Integer quoteid) {
        quoteMapper.modifyStatusByQuoteid("已中标",quoteid);
        //我的需求表已匹配数量增加,累加
        Quote quote = quoteMapper.getQuoteById(quoteid);
        demandMapper.modifyPurchaseNumByDemandcode(quote.getSupplyton(),quote.getDemandcode());
        mydemandMapper.modifyPurchaseNumByDemandcode(quote.getSupplyton(),quote.getDemandcode());
        return JSON.toString("success");
    }
}
