package demo.site.controller;

import demo.core.domain.Dictionary;
import demo.core.persistence.DictionaryMapper;
import demo.core.persistence.ResourceListMapper;
import demo.core.util.PageQueryParam;
import demo.site.basic.BaseController;
import demo.site.basic.JsonController;
import demo.site.service.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by FEI on 15/5/22.
 */
public class ResourceListController extends BaseController {
    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Autowired
    protected ResourceListMapper resourceListMapper;

    @RequestMapping(value = "/resourcelist", method = RequestMethod.GET)
    public String resourceList(
            @RequestParam(value = "city", required = false, defaultValue = "0") int city,
            @RequestParam(value = "coaltype", required = false, defaultValue = "0") int coaltype,
            @RequestParam(value = "sorttype", required = false, defaultValue = "0") int sorttype,
            @RequestParam(value = "sequence", required = false, defaultValue = "0") int sequence,
            @RequestParam(value = "tag", required = false, defaultValue = "0") int tag,
            @RequestParam(value = "tagPrice", required = false, defaultValue = "0") int tagPrice,
            @RequestParam(value = "pagesize", required = false, defaultValue = "10") int pagesize,
            Map<String, Object> model, PageQueryParam param) {
//        int citynum = harbour.equals("null") ? 0 : (areaportMapper.getIdByName(harbour) == null ? 0 : areaportMapper.getIdByName(harbour));
//        int coalname = coaltype.equals("null") ? 0 : (dictionaryMapper.getIdByName(coaltype) == null ? 0 : dictionaryMapper.getIdByName(coaltype));
        getResourceList(city, coaltype, sorttype, sequence, tag, tagPrice, pagesize, model, param);
//        model.put("mallType", "mall");
        return "resourcelist";
    }

    private void getResourceList(int city, int coaltype, int sorttype, int sequence, Integer tag, Integer tagPrice, int pagesize, Map<String, Object> model, PageQueryParam param) {
        List<Dictionary> coalList = new ArrayList<>();
        coalList.add(new Dictionary(0, "coaltype", "全部"));
        coalList.addAll(dictionaryMapper.getAllCoalTypes());
        model.put("coalList", coalList);
    }
}
