package demo.admin.service;

import demo.admin.basic.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by joe on 11/4/14.
 */
@Service
public class ACLInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    protected Session session;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!session.isLogined()){
            throw new UnauthorizedException();
        }
        return super.preHandle(request, response, handler);
    }
}
