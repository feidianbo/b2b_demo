package demo.ext.spring;

import org.springframework.validation.Errors;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joe on 11/7/14.
 */
public interface WithJson {
    public default Object json(Errors errors){
        Map<String,Object> ret=new HashMap<String,Object>();
        ret.put("success", !errors.hasErrors());
        if(errors.hasErrors()){
            ret.put("errors", errors.getAllErrors());
        }
        return ret;
    }
    public static class JsonSuccess{}
}
