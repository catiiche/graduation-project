package com.example.project.config;

import com.example.project.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;


/// удалить!!!!!


//pool.config.corePoolSize=2
//pool.config.maxPoolSize=6
//pool.config.threadNamePrefix=taskExecutor
// списки и мапы в properties файле
@ConfigurationProperties(prefix = "pool.config") // инициализация полей в классе будет происходить из properties файла
// prefix для того, чтобы при инициализации полей было понятно, что конкретно из properties файла имеет отношение к config
@Configuration // объект такого класса будет создан
@EnableWebMvc
public class PoolConfig implements WebMvcConfigurer {
// имена свойств такие же как в properties файле
    private int corePoolSize;
    private int maxPoolSize;
    private String threadNamePrefix;


    private final ApplicationContext applicationContext;

    @Autowired
    public PoolConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }




    // сеттеры нужны для инициализации полей из properties файла
    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public void setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }

    @Bean // метод будет вызван только 1 раз, вернет объект и этот объект будет добавлен в контейнер
    @Qualifier("executor")// задаем бину уникальное имя (придумываем сами)
    // @Primary если несколько объектов общего типа - достанет тот, что отмечен @Primary
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.initialize();
        return executor;
    }


    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/web/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public Client createClient() {
        return new Client();
    }

    // задаем шаблонизатор
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }
}
