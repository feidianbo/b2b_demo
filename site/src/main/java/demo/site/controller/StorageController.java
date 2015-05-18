package demo.site.controller;

import demo.site.basic.JsonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fanjun on 15-1-23.
 */
@Controller
public class StorageController extends JsonController {

    //跳转到仓储页面
    @RequestMapping("/storage")
    public String gotoStorage() {
        return "storage";
    }
}
