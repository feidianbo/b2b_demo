package demo.site.service;

import demo.site.basic.annotation.LoginRequired;
import demo.site.basic.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by joe on 1/15/15.
 */
@Service
public class ACLInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    protected Session session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            if (method.getMethodAnnotation(LoginRequired.class) != null || method.getBeanType().getDeclaredAnnotation(LoginRequired.class) != null) {
                if (!session.isLogined()) {
                    String url=request.getRequestURI();
                    if(request.getQueryString()!=null)
                        url=url+"?"+request.getQueryString();
                    throw new UnauthorizedException(url);
                }
            }
        }
        return super.preHandle(request, response, handler);
    }
}
