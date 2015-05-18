package demo.admin.controller;

import demo.core.domain.Areaport;
import demo.core.persistence.AreaportMapper;
import demo.core.persistence.DealerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiangyang on 15-4-2.
 */
@RestController
@RequestMapping("/port")
public class PortController {

    @Autowired
    private AreaportMapper areaportMapper;
    @Autowired
    private DealerMapper dealerMapper;
    private static final int pageSize=10;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Object list(
            @RequestParam(value = "provinceId",required = false,defaultValue = "-1")int porvinceId,
            @RequestParam(value = "portName",required = false)String portName,
            @RequestParam(value = "page",defaultValue = "1")Integer page){
        Map<String,Object> maps = new HashMap<String,Object>();
        maps.put("provinceId",porvinceId);
        maps.put("portName",portName);
        maps.put("provinceList", areaportMapper.getAllProvince());
        maps.put("portList",areaportMapper.pageAllPort(porvinceId,portName,page,pageSize));
        return maps;
    }

    @RequestMapping(value = "/portDetail",method = RequestMethod.POST)
    public Object showPortDetail(@RequestParam(value = "portId")Integer portId){
        Map<String,Object> params= new HashMap<String,Object>();
        params.put("port",areaportMapper.findPortById(portId));
        params.put("currentDealer",dealerMapper.findAllDealerByPortId(portId));
        params.put("allDealer", dealerMapper.findAllDealer(portId));
        return params;
    }






}
