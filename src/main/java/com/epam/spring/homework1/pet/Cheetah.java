package com.epam.spring.homework1.pet;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class Cheetah implements Animal {
    public Cheetah() {
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
