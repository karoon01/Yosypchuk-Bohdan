package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Bean;

@Configuration
@ComponentScan("com.epam.spring.homework2.beans")
@PropertySource("classpath:application.properties")
@Import(SecondConfig.class)

public class FirstConfig {

    @Bean
    @DependsOn("beanC")
    public BeanA beanA() {
        return new BeanA();
    }

    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    @DependsOn("beanD")
    public BeanB beanB(@Value("${beanB.name}") String name,
                       @Value("${beanB.value}") Double value) {
        return new BeanB(name, value);
    }

    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    @DependsOn("beanB")
    public BeanC beanC(@Value("${beanC.name}") String name,
                       @Value("${beanC.value}") Double value) {
        return new BeanC(name, value);
    }

    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    public BeanD beanD(@Value("${beanD.name}") String name,
                       @Value("${beanD.value}") Double value) {
        return new BeanD(name, value);
    }

    @Bean
    public BeanE beanE() {
        return new BeanE();
    }

    @Bean
    @Lazy
    public BeanF beanF() {
        return new BeanF();
    }

}
