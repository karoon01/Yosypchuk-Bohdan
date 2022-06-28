package com.epam.spring.homework2;

import com.epam.spring.homework2.beans.Bean;
import com.epam.spring.homework2.config.FirstConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FirstConfig.class);

        System.out.println("\n-------------------------------------------------");
        System.out.println("All beans:");
        Arrays.stream(context.getBeanDefinitionNames())
                .map(context::getBean)
                .filter(obj -> obj instanceof Bean)
                .forEach(System.out::println);

        System.out.println("\n-------------------------------------------------");
        System.out.println("Beans configurations");
        Arrays.stream(context.getBeanDefinitionNames())
                .map(context::getBeanDefinition)
                .forEach(System.out::println);

        System.out.println("\n-------------------------------------------------");
        System.out.println("Context close");
        context.close();
    }
}
