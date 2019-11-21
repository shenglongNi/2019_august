package com.nsl.august.web.filter;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class CusBean implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println(">>>BeanFactoryPostProcessor<<< " );
        MyBean myBean = (MyBean)configurableListableBeanFactory.getBean("myBean");
        myBean.setName("zl");

    }
}
