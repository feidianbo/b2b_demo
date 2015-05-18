package demo.admin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.admin.service.ACLInterceptor;
import demo.admin.service.KittHandlerExceptionResolver;
import demo.admin.service.VerifyAuthenticationHandler;
import demo.ext.spring.jackson.Java8TimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by joe on 11/2/14.
 */
@Configuration
public class AdminConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/bower_components/**").addResourceLocations("file:./src-web/bower_components/");
        registry.addResourceHandler("/styles/**").addResourceLocations("file:./src-web/.tmp/styles/");
        registry.addResourceHandler("/images/**").addResourceLocations("file:./src-web/app/images/");
        registry.addResourceHandler("/scripts/**").addResourceLocations("file:./src-web/app/scripts/");
        registry.addResourceHandler("/views/**").addResourceLocations("file:./src-web/app/views/");
        registry.addResourceHandler("/files/**").addResourceLocations("file:../files/");
    }
    @Autowired
    protected ACLInterceptor aclInterceptor;
    @Autowired
    protected VerifyAuthenticationHandler  verifyAuthenticationHandler ;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(aclInterceptor).addPathPatterns("/**").excludePathPatterns("/login").excludePathPatterns("/");
        registry.addInterceptor(verifyAuthenticationHandler).addPathPatterns("/**").excludePathPatterns("/login");
    }
    @Autowired
    protected KittHandlerExceptionResolver kittHandlerExceptionResolver;
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(kittHandlerExceptionResolver);
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400"));
                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
                container.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/401"));
                container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403"));
            }
        };
    }
    @Autowired
    protected ObjectMapper objectMapper;
    @PostConstruct
    public void setThings(){
        objectMapper.registerModule(new Java8TimeModule());
        //objectMapper.registerModule(new JSR310Module());
    }
}
