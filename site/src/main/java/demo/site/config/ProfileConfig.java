package demo.site.config;

import freemarker.cache.FileTemplateLoader;
import demo.site.ext.freemarker.HtmlTemplateLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * Created by joe on 3/11/15.
 */
@Configuration
@AutoConfigureAfter({FreeMarkerAutoConfiguration.class})
public class ProfileConfig {
    @Configuration
    @Profile("dev")
    public static class DevConfig1{
        @Autowired
        protected freemarker.template.Configuration configuration;
        @PostConstruct
        public void setFreemarker() throws IOException {
            configuration.setTemplateLoader(new HtmlTemplateLoader(new FileTemplateLoader(new File("./src/main/resources/templates"))));
        }
    }

    @Configuration
    @Profile("prod")
    public static class ProductionConfig{
        @Autowired
        protected freemarker.template.Configuration configuration;
        @PostConstruct
        public void setFreemarker() throws IOException {
            configuration.setTemplateLoader(new HtmlTemplateLoader(configuration.getTemplateLoader()));
        }
    }



}
