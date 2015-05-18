package demo.site.basic;

import demo.ext.spring.WithJson;
import org.springframework.validation.Errors;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joe on 11/7/14.
 */
public class JsonController extends BaseController {
    public Object json(Errors errors){
        Map<String,Object> ret=new HashMap<String,Object>();
        ret.put("success", !errors.hasErrors());
        if(errors.hasErrors()){
            ret.put("errors", errors.getAllErrors());
        }
        return ret;
    }
}
