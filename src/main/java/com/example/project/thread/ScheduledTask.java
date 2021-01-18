package com.example.project.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class ScheduledTask {
    private TaskExecutor executor;
    private ApplicationContext context;

    @Autowired
    public ScheduledTask(@Qualifier("executor") TaskExecutor executor, ApplicationContext context) {
        this.executor = executor;
        this.context = context;
    }

    @Scheduled(fixedRate = 6000000) // cron
    public void start(){
        System.out.println("Start");
        for (int i = 0; i < 10; i++) {
            ThreadTask task = context.getBean(ThreadTask.class);
            executor.execute(task);
        }
    }

}
