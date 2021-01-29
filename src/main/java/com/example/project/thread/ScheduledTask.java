package com.example.project.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@EnableScheduling // в данном классе будет метод, вызываемый по расписанию
//@Component
//public class ScheduledTask {
//    private TaskExecutor executor;
//    private ApplicationContext context; //  контейнер в котором спринг создаем сам
//
//    @Autowired //при создании объекта spring возьмет тот самый объект executor из контейнера и установит в качестве зависимости
//    public ScheduledTask(@Qualifier("executor") TaskExecutor executor, ApplicationContext context) {
//        this.executor = executor;
//        this.context = context;
//    }
//
//    @Scheduled(fixedRate = 6000000) // в мс, но можно задать через cron ...на классе тоже должна быть аннотация, запуск с промежуток
//    public void start(){
//        System.out.println("Start");
//        for (int i = 0; i < 10; i++) {
//            // достаем объект инструкций из контейнера
//            ThreadTask task = context.getBean(ThreadTask.class);
//            // через сеттеры можно передать в task свойства
//            executor.execute(task); // передаем задачу на выполнение потокам
//        }
//    }
//
//}
