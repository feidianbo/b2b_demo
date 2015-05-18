package demo.site.config;

import freemarker.template.TemplateModelException;
import demo.site.ext.freemarker.*;
import demo.site.ext.freemarker.directive.BlockDirective;
import demo.site.ext.freemarker.directive.ExtendDirective;
import demo.site.ext.freemarker.directive.MarkdownDirective;
import demo.site.ext.freemarker.directive.PeriodDirective;
import demo.site.ext.freemarker.method.FileStorageMethod;
import demo.site.ext.freemarker.method.SessionMethod;
import demo.site.ext.freemarker.method.StaticMethod;
import demo.site.ext.freemarker.method.UrlMethod;
import demo.site.ext.freemarker.object.StatisticsHashModel;
import demo.site.service.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;


/**
 * Created by joe on 10/26/14.
 */
@Configuration
@AutoConfigureAfter({FreeMarkerAutoConfiguration.class, ProfileConfig.class})
public class FreeMarkerConfig {
    @Autowired
    protected freemarker.template.Configuration configuration;
    @Autowired
    protected SessionMethod sessionMethod;
    @Autowired
    protected StatisticsHashModel statisticsHashModel;
    @Autowired
    protected Request request;
    @PostConstruct
    public void setSharedVariable() throws TemplateModelException, IOException {
        //
        //configuration.setTemplateLoader(new HtmlTemplateLoader(new FileTemplateLoader(new File("./src/main/resources/templates"))));

        configuration.setObjectWrapper(new Java8ObjectWrapper());
        configuration.setDateFormat("yyyy-MM-dd");
        configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
        configuration.setSharedVariable("extend", new ExtendDirective());
        configuration.setSharedVariable("block", new BlockDirective());
        configuration.setSharedVariable("markdown", new MarkdownDirective());
        configuration.setSharedVariable("session", sessionMethod);
        configuration.setSharedVariable("request", request);
        configuration.setSharedVariable("static", new StaticMethod());
        configuration.setSharedVariable("fs", new FileStorageMethod());
        configuration.setSharedVariable("url", new UrlMethod());
        configuration.setSharedVariable("period", new PeriodDirective());
        configuration.setSharedVariable("statistics", statisticsHashModel);
        for(String name :new String[]{"page", "cmenu","cpersonal","buyorder"}){
            configuration.addAutoInclude("init/"+name+".ftl");
        }
        configuration.setClassicCompatible(true);
    }
}

