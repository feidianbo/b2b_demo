package demo.admin.controller;

import demo.admin.service.FileService;
import demo.core.domain.Chart;
import demo.core.domain.ChartConfine;
import demo.core.domain.IndexBanner;
import demo.core.persistence.ChartMapper;
import demo.core.persistence.IndexBannerMapper;
import org.eclipse.jetty.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by fanjun on 15-2-7.
 */
@RestController
public class ChartController {

    @Autowired
    private ChartMapper chartMapper;

    @Autowired
    protected FileService fileService;

    @Autowired
    protected IndexBannerMapper indexBannerMapper;

    //批量添加图表
    public static class ChartBatch {
        private List<Chart> chartList = new ArrayList<Chart>();

        public List<Chart> getChartList() {
            return chartList;
        }

        public void setChartList(List<Chart> chartList) {
            this.chartList = chartList;
        }
    }

    //批量添加首页图片
    public static class IndexBannerBatch {
        private List<IndexBanner> indexPicList = new ArrayList<IndexBanner>();

        public List<IndexBanner> getIndexPicList() {
            return indexPicList;
        }

        public void setIndexPicList(List<IndexBanner> indexPicList) {
            this.indexPicList = indexPicList;
        }
    }

    //BSPI列表
    @RequestMapping("/chart/BSPIList")
    public Object BSPIList(int page) {
        Map map = new HashMap();
        map.put("BSPI", chartMapper.pageAllBSPI(page,10));
        return map;
    }

    //API8列表
    @RequestMapping("/chart/API8List")
    public Object API8Lists(int page) {
        Map map = new HashMap();
        map.put("API8", chartMapper.pageAllAPI8(page,10));
        return map;
    }

    //TC1505列表
    @RequestMapping("/chart/TC1505List")
    public Object TC1505List(int page) {
        Map map = new HashMap();
        map.put("TC1505", chartMapper.pageAllTC1505(page,10));
        return map;
    }

    //保存图表数据
    @RequestMapping(value = "/chart/saveChart", method = RequestMethod.POST)
    public Object saveChart(Chart chart){
        chartMapper.addChart(chart);
        boolean success = true;
        return JSON.toString(success);
    }

    //根据id删除记录
    @RequestMapping("/chart/deleteOne")
    public Object deleteOne(Integer id) {
        chartMapper.deleteOne(id);
        boolean success = true;
        return JSON.toString(success);
    }

    //获取图表上限和下限
    @RequestMapping("/chart/getChartConfine")
    public Object getChartConfine(String type) {
        Map map = new HashMap();
        map.put("chartconfine", chartMapper.getChartConfineByType(type));
        return map;
    }

    //修改上下限参数
    @RequestMapping("/chart/modifyChartConfine")
    public Object modifyChartConfine(ChartConfine chartConfine) {
        int i = chartMapper.countChartConfineByType(chartConfine.getType());
        if(i == 1) {
            chartMapper.modifyChartConfine(chartConfine);
        }else{
            chartMapper.addChartConfine(chartConfine);
        }
        boolean success = true;
        return JSON.toString(success);
    }

    //批添加图表数据
    @RequestMapping("/chart/batchAdd")
    public Object batchAdd(ChartBatch chartBatch) {
        List<Chart> chartList = chartBatch.getChartList();
        if(chartList != null && chartList.size() > 0) {
            for (Chart chart : chartList) {
                if(chart.getTradedate() != null && chart.getAverageprice() != 0) {
                    chartMapper.addChart(chart);
                }
            }
        }
        boolean success = true;
        return success;
    }

    //验证交易日期唯一性
    @RequestMapping("/chart/checkTradedateSole")
    public Object checkTradedateSole(String tradedate,String type) {
        int i = chartMapper.checkTradedateSole(tradedate,type);
        return JSON.toString(i);
    }

    //上传首页图片
    @RequestMapping(value = "/chart/changeIndexPic", method = RequestMethod.POST)
    public Object changeIndexPic(@RequestParam("file") MultipartFile file,@RequestParam("picName") String picName, HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<>();
        boolean success = false;
        if(file.getSize() / 1000 / 1000 <= 10) {
            response.setContentType("text/html");
            map.put("filePath", fileService.uploadIndexPicture(file,picName));
            success = true;
        }
        map.put("success", success);
        return map;
    }

    //保存首页图片信息
    @RequestMapping("/chart/saveIndexPic")
    public Object saveIndexPic(@Valid IndexBannerBatch indexBannerBatch) {
        List<IndexBanner> indexBannerList = indexBannerBatch.getIndexPicList();
        int i = indexBannerMapper.countIndexBanner();
        if(i == 0){
            if(indexBannerList != null && indexBannerList.size()>0) {
                for (IndexBanner indexBanner : indexBannerList) {
                    indexBanner.setCreatetime(LocalDateTime.now());
                    indexBannerMapper.addIndexBanner(indexBanner);
                }
            }
        }else{
            if(indexBannerList != null && indexBannerList.size()>0) {
                for (IndexBanner indexBanner : indexBannerList) {
                    indexBanner.setCreatetime(LocalDateTime.now());
                    indexBannerMapper.modifyIndexBanner(indexBanner);
                }
            }
        }
        boolean success = true;
        return success;
    }

    //获取首页图片所有数据
    @RequestMapping("/chart/getAllIndexBanners")
    public Object getAllIndexBanners() {
        List<IndexBanner> bannerList = indexBannerMapper.getAllIndexBanners();
        Map map = new HashMap<>();
        map.put("bannerList",bannerList);
        return map;
    }
}
