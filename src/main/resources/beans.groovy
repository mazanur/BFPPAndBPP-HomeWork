import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
import ru.itpark.CashedAnnotationBeanPostProcessor
import ru.itpark.services.groovy.GroovyPostService
import ru.itpark.services.groovy.GroovyRequestClient

// DSL
beans {
    // имяБина(класс реализации) { кастомизируй бин, как хочешь }

    // var1 (круглые скобки можно не писать, {} - тоже)
    groovyRequestClient GroovyRequestClient

    cashedAnnotationBeanPostProcessor CashedAnnotationBeanPostProcessor

    propertyPlaceholderConfigurer PropertyPlaceholderConfigurer

    // сначала тип, потом аргументы конструктора
    service GroovyPostService, ref(groovyRequestClient)

}