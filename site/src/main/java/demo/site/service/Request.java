package demo.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by joe on 12/31/14.
 */

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Request {
    @Autowired
    protected HttpServletRequest request;

    public String getRequestURI() {
        return request.getRequestURI();
    }
}
