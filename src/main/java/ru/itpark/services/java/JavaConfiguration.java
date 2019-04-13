package ru.itpark.services.java;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itpark.CashedAnnotationBeanPostProcessor;
import ru.itpark.models.PostModel;
import ru.itpark.utils.Parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;


@Configuration
public class JavaConfiguration {
    @Bean
    public JavaRequestClient javaRequestClient() {
        return new JavaRequestClient();
    }

    @Bean("service")
    public JavaPostService javaPostService() {
        return new JavaPostService (javaRequestClient());
    }
    @Bean
    public CashedAnnotationBeanPostProcessor cashedAnnotationBeanPostProcessor(){
        return new CashedAnnotationBeanPostProcessor();
    }
    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() throws FileNotFoundException {
        var configurer = new PropertyPlaceholderConfigurer();
        Properties properties = new Properties();
        PostModel postModel = Parser.getPostModelByFileReader(new FileReader("./src/main/resources/properties.json"));
        properties.setProperty("id", postModel.getId().toString());
        configurer.setProperties(properties);
        return configurer;
    }

}
