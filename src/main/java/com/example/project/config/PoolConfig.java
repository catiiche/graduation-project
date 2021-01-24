package com.example.project.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

//pool.config.corePoolSize=2
//pool.config.maxPoolSize=6
//pool.config.threadNamePrefix=taskExecutor
// списки и мапы в properties файле
@ConfigurationProperties(prefix = "pool.config") // инициализация полей в классе будет происходить из properties файла
// prefix для того, чтобы при инициализации полей было понятно, что конкретно из properties файла имеет отношение к config
@Configuration // объект такого класса будет создан и
public class PoolConfig {
// имена свойств такие же как в properties файле
    private int corePoolSize;
    private int maxPoolSize;
    private String threadNamePrefix;

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

    @Bean // метод будет вызван, вернет объект и этот объект будет добавлен в контейнер
    @Qualifier("executor")// задаем бину уникальное имя (придумываемсами)
    // @Primary если несколько объектов общего типа - достанет тот, что отмечен @Primary
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.initialize();
        return executor;
    }
}
