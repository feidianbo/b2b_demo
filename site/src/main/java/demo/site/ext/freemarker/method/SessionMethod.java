package demo.site.ext.freemarker.method;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import demo.site.service.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by joe on 10/26/14.
 */
@Component
public class SessionMethod implements TemplateMethodModelEx {
    @Autowired protected Session session;
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        return session;
    }
}
