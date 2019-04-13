package ru.itpark.services.annotations;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;
import ru.itpark.Cached;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class AnnotationCashedAnnotationBeanPostProcessor implements BeanPostProcessor {
    private final Map<String, Method> map = new HashMap<>();
    private final Map<Map<Method, Object>, Object> cashe = new HashMap<>();


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Cached.class)) {
                map.put(beanName, method);
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (Arrays.stream(bean.getClass().getMethods()).noneMatch(map::containsValue)) {
            return bean;
        }
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(map.get(beanName).getDeclaringClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override

            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if (cashe.containsKey(Map.of(method, objects[0]))) {
                    System.out.println("cashedResult");
                    return cashe.get(Map.of(method, objects[0]));
                }

                //Вызываем метод и реузльтат кладем в map cache
                Object result = methodProxy.invoke(bean, objects);
                //сохраняем результат метода, при следующем вызове возвращем только его
                System.out.println("cashed");
                cashe.put(Map.of(method, objects[0]), result);


                return result;
            }
        });

        return enhancer.create();
    }
}
