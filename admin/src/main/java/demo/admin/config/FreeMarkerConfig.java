package demo.admin.config;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.TemplateModelException;
import demo.admin.ext.freemarker.StaticMethod;
import demo.admin.ext.freemarker.directive.MarkdownDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;


/**
 * Created by joe on 10/26/14.
 */
@Configuration
@AutoConfigureAfter({FreeMarkerAutoConfiguration.class, ProfileConfig.class})
public class FreeMarkerConfig {
    @Autowired
    protected freemarker.template.Configuration configuration;
    @PostConstruct
    public void setSharedVariable() throws TemplateModelException, IOException {
        configuration.setSharedVariable("static", new StaticMethod());
        configuration.setSharedVariable("markdown", new MarkdownDirective());
        configuration.setClassicCompatible(true);
    }
}
