package com.epam.spring.homework2.beans;

public class BeanD extends Bean {

    public BeanD(String name, Double value) {
        super(name, value);
    }

    public void customInitMethod() {
        System.out.println(this.getClass().getSimpleName() + " custom init method");
    }

    public void customDestroyMethod() {
        System.out.println(this.getClass().getSimpleName() + " custom destroy method");
    }
}
