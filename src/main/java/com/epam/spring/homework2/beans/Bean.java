package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validation.Validator;

public abstract class Bean implements Validator {

    private String name;
    private Double value;

    public Bean() {
    }

    public Bean(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean validate() {
        return this.name != null && this.value > 0;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
