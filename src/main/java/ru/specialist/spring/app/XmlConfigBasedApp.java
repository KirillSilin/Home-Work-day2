package ru.specialist.spring.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.specialist.spring.bean.Computer;

import java.util.Arrays;

public class XmlConfigBasedApp {

    public static void main(String[] args) {
        ApplicationContext context
                = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println("Context Name: " + context.getDisplayName());
        System.out.println("Bean Definitions: " + Arrays.toString(context.getBeanDefinitionNames()));

        Computer computer = context.getBean(Computer.class);
        System.out.println(computer);
    }

}
