package demo.site.service;

import demo.core.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by zhangbolun on 15/3/13.
 */
@Service
@Scope()
public class SessionListener implements HttpSessionListener {

    @Autowired
    protected UserMapper userMapper;

    private HttpSession session;

    @Override
    public void sessionCreated(HttpSessionEvent se){
        session= se.getSession();
        // session 失效时间
        session.setMaxInactiveInterval(60* 60);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se){

    }

}
