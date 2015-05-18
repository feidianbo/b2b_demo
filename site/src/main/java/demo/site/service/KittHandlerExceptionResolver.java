package demo.site.service;

import demo.core.service.ExceptionReporter;
import demo.ext.WithLogger;
import demo.site.basic.exception.BusinessException;
import demo.site.basic.exception.NotFoundException;
import demo.site.basic.exception.UnauthorizedException;
import org.apache.http.HttpStatus;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by joe on 1/15/15.
 */
@Service
public class KittHandlerExceptionResolver extends DefaultHandlerExceptionResolver implements  WithLogger {
    @Autowired
    ExceptionReporter reporter;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView  modelAndView  ;
        modelAndView= super.doResolveException(request, response, handler, ex) ;
        if(modelAndView!=null){
            return modelAndView ;
        }else if(ex instanceof NotFoundException){
            logger().warn("",ex);
            try {
                response.sendError(HttpStatus.SC_NOT_FOUND);
            } catch (IOException e) {
                logger().warn("",e);
            }
            return new ModelAndView();
        }else if(ex instanceof UnauthorizedException){
            logger().warn("", ex);
            modelAndView =  new ModelAndView("/login");
            modelAndView.addObject("exception", ((UnauthorizedException) ex).getUrl());
            //response.setStatus(401);
            return  modelAndView ;
        }else  if (ex instanceof BusinessException ){
            try {
                //response.setCharacterEncoding("utf-8");
                response.setHeader("content-type", "application/json;charset=UTF-8");
                response.getWriter().write(ex.getMessage());
                response.setStatus(409);
            } catch (IOException e) {
                logger().warn("",e);
            }
            return new ModelAndView();
        }else{
            StringBuffer formData = new StringBuffer();
            StringBuffer value = null;
            modelAndView = new ModelAndView("http/500");
            modelAndView.addObject("exception", ex);
            Map<String,String[]> parameterMap = request.getParameterMap();
            Set<Map.Entry<String,String[]>> set =parameterMap.entrySet();
            for(Map.Entry<String,String[]>entry:set){
                value  = new StringBuffer();
                for(String v:entry.getValue()){
                    value.append(v).append(",");
                }
                formData.append(entry.getKey() + ":" + value.substring(0, value.length() - 1).toString());
            }
            logger().error("500", ex);
            reporter.handle(ex, request.getRequestURL().toString(), formData.toString());
            return modelAndView;
        }
    }

    @Override
    protected ModelAndView handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                               HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, ex.getMessage());
        return new ModelAndView();
    }
}