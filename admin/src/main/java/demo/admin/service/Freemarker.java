package demo.admin.service;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * Created by joe on 1/13/15.
 */
@Component
public class Freemarker {
    @Autowired
    protected freemarker.template.Configuration configuration;

    public String render(String templateName, Map<String, Object> model) throws IOException, TemplateException {
        Template template = configuration.getTemplate(templateName + ".ftl");

        StringWriter writer = new StringWriter();
        template.process(model, writer);
        return writer.toString();
    }
}
