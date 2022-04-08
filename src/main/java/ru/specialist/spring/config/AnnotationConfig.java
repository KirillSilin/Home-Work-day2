package ru.specialist.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.specialist.spring.bean.*;

import java.util.List;

@Configuration
@ComponentScan("ru.specialist.spring.bean")
public class AnnotationConfig {

    @Bean
    public List<RAM> ramList(){
        return List.of(kingstonRam(), sonyRAM(), kingstonRam());
    }

    @Bean
    @Scope("prototype")
    public RAM kingstonRam(){
        return new KingstonRAM();
    }

    @Bean
    @Scope("prototype")
    public RAM sonyRAM(){
        return new SonyRAM();
    }
}
