package com.example.project.thread;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

////// инструкции, которые должен выполнять поток
//@Component // будет создан 1 объект и будет добавлен в контейнер
//@Scope("prototype") // потребуется несколько объектов такого типа, по мере необходимости на основе первого объекта будут создаваться доп.объекты
//public class ThreadTask implements Runnable{
//// описываем свойства
//    @Override
//    public void run() {
//        // инструкции для одного потока вместо sout & sleep
//        try {
//            System.out.println(Thread.currentThread().getName());
//            Thread.sleep((long) (Math.random() * 5000));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}

