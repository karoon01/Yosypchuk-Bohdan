package com.epam.spring.homework2.beans;

public class BeanB extends Bean {

    public BeanB(String name, Double value) {
        super(name, value);
    }

    public void customInitMethod() {
        System.out.println(this.getClass().getSimpleName() + " custom init method");
    }

    public void newCustomInitMethod() {
        System.out.println(this.getClass().getSimpleName() + " new custom init method");
    }

    public void customDestroyMethod() {
        System.out.println(this.getClass().getSimpleName() + " custom destroy method");
    }
}
