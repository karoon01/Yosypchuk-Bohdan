package com.epam.spring.homework2.bfpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        for (String name : configurableListableBeanFactory.getBeanDefinitionNames()) {
            if (name.equals("beanB")) {
                BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition(name);
                beanDefinition.setInitMethodName("newCustomInitMethod");
            }
        }
    }
}
