package demo.site.controller;

import demo.core.dao.DemoDao;
import demo.core.domain.*;
import demo.core.persistence.*;
import demo.ext.WithLogger;
import demo.site.basic.BaseController;
import demo.site.basic.annotation.LoginRequired;
import demo.site.basic.exception.NotFoundException;
import demo.site.service.Freemarker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by joe on 10/26/14.
 */
@Controller
public class IndexController extends BaseController{
    @Autowired
    protected Freemarker freemarker;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private BuyMapper buyMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ChartMapper chartMapper;
    @Autowired
    DemoDao demoDao;
    @Autowired
    private IndexBannerMapper indexBannerMapper;

    /** Single quote */
    public static final String SINGLE_QUOTE = "\'";
    /** Left square bracket */
    public static final String L_SQUARE_BRACKET = "[";
    /** Right square bracket */
    public static final String R_SQUARE_BRACKET = "]";
    /** Comma */
    public static final String COMMA = ",";

    public  static  final  String  ANTHRACITECOAL="无烟煤";
    public  static  final  String  POWERCOAL="动力煤";
    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        List<SellInfo> sellInfoList = buyMapper.getRecommondSellinfoList();
        model.put("sellInfoList", sellInfoList);

        // 区域行情
        model.put("regionalNewsList", articleMapper.getSeveralArticlesByParentPath("/区域行情",5));
        model.put("firstArticleList", articleMapper.getSeveralArticlesByParentPath("/BSPI", 2));
        model.put("secondArticleList", articleMapper.getSeveralArticlesByParentPath("/API8", 2));
        model.put("thirdArticleList", articleMapper.getSeveralArticlesByParentPath("/TC主力合约", 2));

        //公测天数， 成交总瓶数， 总金额
        int onlineDays = LocalDate.now().getDayOfYear() - 71;
        List<Order> allCompleteOrders = orderMapper.getOrderListByStatus(EnumOrder.Completed);
        Long totalAmount = 0L;
        BigDecimal totalMoney = BigDecimal.valueOf(0);
        for(Order order : allCompleteOrders){
            totalAmount += order.getAmount();
            totalMoney = totalMoney.add(order.getTotalmoney());
        }
        model.put("onlineDays", onlineDays);
        model.put("totalAmount", totalAmount);
        model.put("totalMoney", totalMoney);

        //成交记录
        List<Order> orderList = orderMapper.listRecentTransactionOrders();
        List<OrderTransaction> orderTransactionList = new ArrayList<OrderTransaction>();
        if(orderList.size() != 0){
            for(Order order : orderList){
                OrderTransaction orderTransaction = new OrderTransaction();
                orderTransaction.setPname(buyMapper.getSellInfoById(order.getSellinfoid()).getPname());
                orderTransaction.setAmount(order.getAmount());
                String harbour = order.getDeliveryplace().equals("其它") ? order.getOtherharbour() : order.getDeliveryplace();
                harbour = harbour.length() > 3 ? harbour.substring(0,3) + ".." : harbour;
                orderTransaction.setHarbour(harbour);
                orderTransaction.setTime(order.getCreatetime().getMonthValue() + "-" + order.getCreatetime().getDayOfMonth());
                orderTransaction.setSellinfoid(order.getSellinfoid());
                orderTransaction.setSeller(order.getSeller());
                orderTransactionList.add(orderTransaction);
            }
        }
        model.put("orderTransactionList", orderTransactionList);

        // ------------ Chart ---------------
        // 查询BSPI的最近12条记录
        List<Chart> bspiList = chartMapper.getAllBSPI();
        // 查询API8的最近10条记录
        List<Chart> api8List = chartMapper.getAllAPI8();
        // 查询TC1505的最近10条记录
        List<Chart> tc1505List = chartMapper.getAllTC1505();

        // 生成图表相关信息
        ChartInfo bspiInfo = generateChartInfo(bspiList);
        ChartInfo api8Info = generateChartInfo(api8List);
        ChartInfo tc1505Info = generateChartInfo(tc1505List);

        if (bspiInfo != null && api8Info != null && tc1505Info != null) {
            // 图表上下限边界值
            String chartConfine = L_SQUARE_BRACKET + bspiInfo.getMin() + COMMA + bspiInfo.getMax() + COMMA
                    + api8Info.getMin() + COMMA + api8Info.getMax() + COMMA
                    + tc1505Info.getMin() + COMMA + tc1505Info.getMax() + R_SQUARE_BRACKET;

            model.put("BSPIdate", bspiInfo.getDateAll());
            model.put("BSPInum", bspiInfo.getPriceAll());
            model.put("API8date", api8Info.getDateAll());
            model.put("API8num", api8Info.getPriceAll());
            model.put("TC1505date", tc1505Info.getDateAll());
            model.put("TC1505num", tc1505Info.getPriceAll());
            model.put("chartConfine", chartConfine);
        }

//        List<Chart> BSPIList = chartMapper.getAllBSPI();
//        List<Chart> API8List = chartMapper.getAllAPI8();
//        List<Chart> TC1505List = chartMapper.getAllTC1505();
//        String BSPIdate = null;
//        String BSPInum = null;
//        if(BSPIList != null && BSPIList.size()>0) {
//            Collections.sort(BSPIList, new DateComparator());
//            BSPIdate = "[";
//            BSPInum = "[";
//            for(Chart chart :BSPIList){
//                BSPIdate = BSPIdate + SINGLE_QUOTE + chart.getTradedate().toString().substring(5) + SINGLE_QUOTE +",";
//                BSPInum = BSPInum + chart.getAverageprice()+",";
//            }
//            BSPIdate = BSPIdate+"]";
//            BSPInum = BSPInum +"]";
//        }
//
//        String API8date = null;
//        String API8num = null;
//        if(API8List != null && API8List.size()>0) {
//            Collections.sort(API8List, new DateComparator());
//            API8date = "[";
//            API8num = "[";
//            for(Chart chart :API8List){
//                API8date = API8date + SINGLE_QUOTE + chart.getTradedate().toString().substring(5) + SINGLE_QUOTE +",";
//                API8num = API8num + chart.getAverageprice()+",";
//            }
//            API8date = API8date+"]";
//            API8num = API8num +"]";
//        }
//
//        String TC1505date = null;
//        String TC1505num = null;
//        if(TC1505List != null && TC1505List.size()>0) {
//            Collections.sort(TC1505List, new DateComparator());
//            TC1505date = "[";
//            TC1505num = "[";
//            for(Chart chart :TC1505List){
//                TC1505date = TC1505date + SINGLE_QUOTE + chart.getTradedate().toString().substring(5) + SINGLE_QUOTE +",";
//                TC1505num = TC1505num + chart.getAverageprice()+",";
//            }
//            TC1505date = TC1505date+"]";
//            TC1505num = TC1505num +"]";
//        }
//
//        String chartConfine = null;
//        ChartConfine BSPIconfine = chartMapper.getChartConfineByType("BSPI");
//        ChartConfine API8confine = chartMapper.getChartConfineByType("API8");
//        ChartConfine TC1505confine = chartMapper.getChartConfineByType("TC1505");
//        if(BSPIconfine != null && API8confine != null && TC1505confine != null){
//            chartConfine = "[";
//            chartConfine = chartConfine + BSPIconfine.getLowlimit()+","+BSPIconfine.getHighlimit()+","
//                    +API8confine.getLowlimit()+","+API8confine.getHighlimit()+","
//                    +TC1505confine.getLowlimit()+","+TC1505confine.getHighlimit()+"]";
//        }
//
//        model.put("BSPIdate", BSPIdate);
//        model.put("BSPInum",BSPInum);
//        model.put("API8date",API8date);
//        model.put("API8num",API8num);
//        model.put("TC1505date",TC1505date);
//        model.put("TC1505num",TC1505num);
//        model.put("chartConfine",chartConfine);   //图表上下限

        //XX直通车的数据
       // model.put("anthraciteDistribute",findBySellDistribute(ANTHRACITECOAL));
       // model.put("powercoalDistribute", findBySellDistribute(POWERCOAL));

        //首页图片
        int limitnum = 0;
        List<IndexBanner> indexBannerList = null;
        int countIndexBanner = indexBannerMapper.countIndexBanner();
        if(countIndexBanner != 0){
            limitnum = indexBannerMapper.getLimitnum(); //先取到显示数量参数
        }
        if(limitnum != 0) {
             indexBannerList = indexBannerMapper.getIndexBannnersWithLimit(limitnum);
        }
        model.put("indexBannerList",indexBannerList);
        return "index";
    }

    /**
     * Generate homepage chart info.
     * @param chartList
     * @return
     */
    public ChartInfo generateChartInfo(List<Chart> chartList) {
        if (chartList == null || chartList.size() == 0)
            return null;

        // init chart date & value collection
        StringBuffer chartDate = new StringBuffer(L_SQUARE_BRACKET);
        StringBuffer chartPrice =  new StringBuffer(L_SQUARE_BRACKET);

        // init max & min value, max=min=first_averagePrice
        double chartMin = chartList.get(0).getAverageprice();
        double chartMax = chartMin;

        for (int i=chartList.size()-1; i>=0; i--) {
            // get month & day: 2015-03-27 --> 03-27
            String date = chartList.get(i).getTradedate().toString().substring(5);
            double averagePrice = chartList.get(i).getAverageprice();

            chartDate = chartDate.append(SINGLE_QUOTE).append(date).append(SINGLE_QUOTE);
            chartPrice = chartPrice.append(averagePrice);

            // add comma if it's not the last element
            if (i > 0) {
                chartDate.append(COMMA);
                chartPrice.append(COMMA);
            }
            // find the max & min price for this chart
            chartMin = chartMin < averagePrice ? chartMin : averagePrice;
            chartMax = chartMax > averagePrice ? chartMax : averagePrice;
        }

        chartDate.append(R_SQUARE_BRACKET);
        chartPrice.append(R_SQUARE_BRACKET);

        // if chartMax!=chartMin, chart offset = (max - min)/10
        double offset = (chartMax != chartMin) ? (chartMax - chartMin) / 10 : 2;
        // add offset for the chart confine
        chartMin = chartMin - offset;
        chartMax = chartMax + offset;

        return new ChartInfo(chartList.size(), chartDate.toString(), chartPrice.toString(), chartMax, chartMin, offset);
    }

//    @RequestMapping("/test")
//    @LoginRequired
//    public String index2(){
//        return "index";
//    }

    //新手上路
    @RequestMapping("/footer/startOff")
    public String startOff(String pos,Map<String, Object> model) {
        model.put("pos",pos);
        return "/footer/startOff";
    }
    //如何买煤
    @RequestMapping("/footer/buyCoal")
    public String buyCoal() {
        return "/footer/buyCoal";
    }
    //如何卖煤
    @RequestMapping("/footer/sellCoal")
    public String sellCoal() {
        return "/footer/sellCoal";
    }

    //关于买卖
    @RequestMapping("/footer/aboutContract")
    public String aboutContract(String pos,Map<String, Object> model) {
        model.put("pos",pos);
        return "/footer/aboutContract";
    }
    //增值服务
    @RequestMapping("/footer/aboutServices")
    public String aboutServices(String pos,Map<String, Object> model) {
        model.put("pos",pos);
        return "/footer/aboutServices";
    }
    //增值服务
    @RequestMapping("/footer/aboutUs")
    public String aboutUs(String pos,Map<String, Object> model) {
        model.put("pos",pos);
        return "/footer/aboutUs";
    }
     static  class DateComparator implements Comparator<Chart>{

         public int compare(Chart c, Chart c2) {
             if(c.getTradedate().isBefore(c2.getTradedate())){
                 return -1;
             }
             return 1;
         }
    }
    private Set<SellDistribute> findBySellDistribute(String coalType) {

        List<SellInfo> originalData = buyMapper.findByCoalType(coalType);
        SellDistribute distribute = new SellDistribute();
        Set<SellDistribute> lists = new HashSet<SellDistribute>();

//		//构造区域分布的数据
        for (SellInfo sell : originalData) {
            if (null!=sell.getDeliveryregion() && sell.getDeliveryregion().equals(distribute.getSell())) {
                //得到区域下的港口
                distribute.setDeliverys(findPorts(originalData, sell.getDeliveryregion()));
                //得到港口所对应的低位热值
                distribute.setNcvs(findNcvs(originalData, distribute.getDeliverys()));
                continue;
            }
            distribute = new SellDistribute();
            distribute.setSell(sell.getDeliveryregion());
            //得到区域下的港口
            distribute.setDeliverys(findPorts(originalData, sell.getDeliveryregion()));
            //得到港口所对应的低位热值
            distribute.setNcvs(findNcvs(originalData, distribute.getDeliverys()));
            lists.add(distribute);
        }
            return lists;
    }

    private static Set<String> findPorts(List<SellInfo> originalData,String region){
        Set<String> ports = new HashSet<String>();
        for(SellInfo sell:originalData) {
            if(region.equals(sell.getDeliveryregion())){
                ports.add(sell.getDeliveryplace());
            }
        }
        return ports;
    }
    private static Set<Integer> findNcvs(List<SellInfo> originalData,Set<String> ports){
        Set<Integer> ncvs = new HashSet<Integer>();
        for(SellInfo s:originalData){
            for(String str:ports){
                if (str.equals(s.getDeliveryplace())){
                    ncvs.add(s.getNCV());
                }
            }
        }
        return ncvs;
    }

    @RequestMapping(value = "/getCoal", method = RequestMethod.POST)
    @ResponseBody
    public Object getCoal(){
        List<SellInfo> powerCoalList = null;
        List<SellInfo> anthrAciteCoalList = null;
        powerCoalList = buyMapper.getByDeliveryregion(POWERCOAL);
        anthrAciteCoalList = buyMapper.getByDeliveryregion(ANTHRACITECOAL);
        Map map = new HashMap<>();
        if(powerCoalList != null){
            map.put("powerCoalList",powerCoalList);
        }
        if(anthrAciteCoalList != null){
            map.put("AnthrAciteCoalList",anthrAciteCoalList);
        }
        return map;
    }
}
