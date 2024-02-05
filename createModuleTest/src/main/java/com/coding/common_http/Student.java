/**
package com.coding.test;

import javafx.fxml.Initializable;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Data
@Component
public class Student implements InitializingBean, BeanFactoryAware, BeanNameAware, DisposableBean {
    String name;
    Integer age;

    public Student() {
        System.out.println("init");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory");

    }


    @Override
    public void setBeanName(String s) {
        System.out.println("setBeanName");

    }


    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");

    }
}


class Test {
    @Autowired
    Student student;
}
*/
