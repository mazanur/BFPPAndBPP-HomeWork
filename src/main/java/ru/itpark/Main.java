package ru.itpark;


import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;
import ru.itpark.services.annotations.AnnotationPostService;
import ru.itpark.services.groovy.GroovyPostService;
import ru.itpark.services.java.JavaConfiguration;
import ru.itpark.services.java.JavaPostService;
import ru.itpark.services.programmatic.ProgrammaticPostService;
import ru.itpark.services.programmatic.ProgrammaticRequestClient;
import ru.itpark.services.xml.XmlPostService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        {//XmlConfiguration
            var ctx = new ClassPathXmlApplicationContext("beans.xml");
            XmlPostService service = ctx.getBean("XmlPostService", XmlPostService.class);
            System.out.println(service.getPost(3).getId());
            System.out.println(service.getPost(4).getId());
            System.out.println(service.getPost(3).getId());
        }

        {//Annotation
            var ctx = new AnnotationConfigApplicationContext("ru.itpark.services.annotations");
            AnnotationPostService service = ctx.getBean("service", AnnotationPostService.class);
            System.out.println(service.getPost(3).getId());
            System.out.println(service.getPost(4).getId());
            System.out.println(service.getPost(3).getId());
        }


        {//JavaConfiguration
            var ctx = new AnnotationConfigApplicationContext(JavaConfiguration.class);
            JavaPostService service = ctx.getBean("service", JavaPostService.class);
            System.out.println(service.getPost(3).getId());
            System.out.println(service.getPost(4).getId());
            System.out.println(service.getPost(3).getId());
        }

        { // Programmatic
            var ctx = new GenericApplicationContext();
            ctx.registerBean(ProgrammaticRequestClient.class);
            ctx.registerBean(CashedAnnotationBeanPostProcessor.class);
            ctx.registerBean(PropertyPlaceholderConfigurer.class);
            ctx.registerBean("service", ProgrammaticPostService.class);
            ctx.refresh();
            ProgrammaticPostService service = ctx.getBean("service", ProgrammaticPostService.class);
            System.out.println(service.getPost(3).getId());
            System.out.println(service.getPost(4).getId());
            System.out.println(service.getPost(3).getId());
        }


        {//GroovyConfigurationl
            var ctx = new GenericGroovyApplicationContext("beans.groovy");
            GroovyPostService service = ctx.getBean("service", GroovyPostService.class);
            System.out.println(service.getPost(3).getId());
            System.out.println(service.getPost(4).getId());
            System.out.println(service.getPost(3).getId());
        }


    }


}
