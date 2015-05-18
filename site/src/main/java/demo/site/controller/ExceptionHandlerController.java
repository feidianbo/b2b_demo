package demo.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by joe on 11/18/14.
 */
@Controller
public class ExceptionHandlerController {
    @RequestMapping("/400")
    public String h400(Exception ex){
        return "http/400";
    }
    @RequestMapping("/404")
    public String h404(Exception ex){
        return "http/404";
    }
}
