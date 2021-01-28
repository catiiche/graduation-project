package com.example.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


// файл конфигурации
@Configuration
@ComponentScan("com.example.project.config")
public class WebMvcConfig {

    // создаём bean-компонент, который распознаёт представления (View)
    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}

