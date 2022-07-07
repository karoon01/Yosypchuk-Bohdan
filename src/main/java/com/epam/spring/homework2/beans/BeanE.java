package com.epam.spring.homework2.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class BeanE extends Bean{

    @PostConstruct
    public void init() {
        System.out.println(this.getClass().getSimpleName() + " custom postConstruct method");
    }

    @PreDestroy
    public void destroy() {
        System.out.println(this.getClass().getSimpleName() + " custom PreDestroy method");
    }
}
