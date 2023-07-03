package org.example;

import org.example.Controller.MainController;
import org.example.config.SpringConfig;
import org.example.service.TakenBookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
//        TakenBookService takenBookService = (TakenBookService) context.getBean("takenBookService");

        MainController mainController = new MainController();
        mainController.start();
    }
}