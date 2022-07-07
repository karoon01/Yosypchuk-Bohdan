package com.epam.spring.homework2.beans;

public class BeanC extends Bean{

    public BeanC(String name, Double value) {
        super(name, value);
    }

    public void customInitMethod() {
        System.out.println(this.getClass().getSimpleName() + " custom init method");
    }

    public void customDestroyMethod() {
        System.out.println(this.getClass().getSimpleName() + " custom destroy method");
    }

}
