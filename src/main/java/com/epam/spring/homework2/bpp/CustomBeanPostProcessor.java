package com.epam.spring.homework2.bpp;

import com.epam.spring.homework2.beans.Bean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        String message;

        if(bean instanceof Bean) {
            boolean isValid = ((Bean) bean).validate();

            if(!isValid) {
                message = "+ Bean is not valid";
                ((Bean) bean).setName("Default name");
                ((Bean) bean).setValue(1.0);
            }else {
                message = "+ Bean is valid";
            }
            System.out.println(message);
        }
        return bean;
    }
}
