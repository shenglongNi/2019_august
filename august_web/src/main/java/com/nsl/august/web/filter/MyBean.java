package com.nsl.august.web.filter;

import org.springframework.beans.factory.InitializingBean;

public class MyBean implements InitializingBean{

    private String name;


    static {
        System.out.println("MyBean static Block");
    }

    public MyBean() {
        System.out.println("MyBean Constructor init");

    }

    public MyBean(String name) {
        System.out.println("Before name:" +name);
        this.name = name;
        System.out.println("after name:" +name);
    }

    public void init() {
        System.out.println("MyBean inti method ");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MyBean afterPropertiesSet .....");
        System.out.println("my name: " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
