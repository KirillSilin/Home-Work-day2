package ru.specialist.spring.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.specialist.spring.bean.Computer;
import ru.specialist.spring.config.AnnotationConfig;

import java.util.Arrays;

public class AnnotationConfigBasedApp {

    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        System.out.println("Context Name: " + context.getDisplayName());
        System.out.println("Bean Definitions: " + Arrays.toString(context.getBeanDefinitionNames()));

        Computer computer = context.getBean(Computer.class);
        System.out.println(computer);
    }

}
