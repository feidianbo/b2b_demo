package demo.admin.controller;

import demo.admin.service.FileService;
import demo.core.domain.Dictionary;
import demo.core.persistence.DictionaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fanjun on 15-4-10.
 */
@RestController
public class AndroidController {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Autowired
    protected FileService fileService;

    //获取当前apk版本号和当前日期
    @RequestMapping("/android/getVersion")
    public Object getVersion() {
        Dictionary dictionary = dictionaryMapper.getAPKVersion();
        String version = null;
        if(dictionary != null){
            version = dictionary.getName();
        }else{
            version = "待上传";
        }
        Map map = new HashMap<>();
        map.put("version",version);
        map.put("today",LocalDate.now());
        return map;
    }

    //上传安卓app文件
    @RequestMapping("/android/uploadApp")
    public Object uploadApp(@RequestParam("file") MultipartFile file) throws Exception{
        //参数appType,为如果有苹果app预留定义
        String uploadPath = fileService.uploadApp(file, "yimeiwang.apk","android");
        Map map = new HashMap<>();
        map.put("uploadPath",uploadPath);
        return map;
    }

    //保存版本号
    @RequestMapping("/android/saveVersion")
    public Object saveVersion(String newVersion) {
        newVersion = newVersion +";"+LocalDate.now();
        Dictionary dictionary = dictionaryMapper.getAPKVersion();
        if(dictionary == null){
            dictionaryMapper.addAPKVersion(newVersion);
        }else{
            dictionaryMapper.moidyfAPKVersion(newVersion);
        }
        Map map = new HashMap<>();
        boolean success = true;
        map.put("success",success);
        return map;
    }
}
