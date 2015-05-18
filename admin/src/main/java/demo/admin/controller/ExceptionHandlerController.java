package demo.admin.controller;

import demo.admin.basic.exception.UnauthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by joe on 11/18/14.
 */
@Controller
public class ExceptionHandlerController {
    @RequestMapping("/401")
    public String h404(UnauthorizedException ex){
        System.out.println(ex.getMessage());
        return "http/401";
    }
}
