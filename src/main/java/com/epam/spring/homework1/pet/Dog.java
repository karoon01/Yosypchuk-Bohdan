package com.epam.spring.homework1.pet;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class Dog implements Animal {
    public Dog() {
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
