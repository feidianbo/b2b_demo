package demo.core.config;

import demo.core.service.FileStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * Created by fanjun on 14-11-17.
 */
@Configuration
public class PicSaveConfig {

    @Value("${filePath}")
    private String filePath;

    @Bean
    public FileStore createFilePath(){
        return new FileStore(filePath);
    }
}
