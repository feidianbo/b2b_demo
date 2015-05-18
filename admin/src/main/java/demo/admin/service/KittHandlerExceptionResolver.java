package demo.admin.service;

import demo.admin.basic.exception.BusinessException;
import demo.core.service.ExceptionReporter;
import demo.ext.WithLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by joe on 1/15/15.
 */
@Service
public class KittHandlerExceptionResolver implements HandlerExceptionResolver, WithLogger {
    @Autowired
    ExceptionReporter reporter;
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        StringBuffer formData = new StringBuffer();
        StringBuffer value = null;
        ResponseStatus status = ex.getClass().getDeclaredAnnotation(ResponseStatus.class);
        ModelAndView modelAndView = new ModelAndView("http/500");
        modelAndView.addObject("exception", ex);
        if (status != null) {
            modelAndView.setViewName("http/" + status.value().value());
            response.setStatus(status.value().value());
        }else if (ex instanceof BusinessException){
            try {
                response.setHeader("content-type", "application/json;charset=UTF-8");
                response.getWriter().write(ex.getMessage());
                response.setStatus(409);
            } catch (IOException e) {
                logger().warn("",e);
            }
            return new ModelAndView();
        } else {
            Map<String,String[]>paramerMap= request.getParameterMap();
            Set<Map.Entry<String,String[]>> set =paramerMap.entrySet();
            for(Map.Entry<String,String[]>entry:set){
                value = new StringBuffer();
                for(String v:entry.getValue()){
                    value.append(v).append(",");
                }
                formData.append(entry.getKey() + ":" + value.substring(0, value.length() - 1).toString());
            }
            reporter.handle(ex,request.getRequestURL().toString(),formData.toString());
            logger().error(ex.getMessage(), ex);
            response.setStatus(500);
        }
        return modelAndView;
    }
}
