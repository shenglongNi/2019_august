package com.nsl.august.web.config;

import com.nsl.august.web.filter.CorsFilter;
import com.nsl.august.web.filter.CusBean;
import com.nsl.august.web.filter.MyBean;
import com.nsl.august.web.filter.TestBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {


    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> corsFilter = new FilterRegistrationBean<>(new CorsFilter());
        corsFilter.addUrlPatterns("/*");
        return corsFilter;
    }

    @Bean
    public TestBean testBean() {
        return new TestBean();
    }

    @Bean
    public MyBean myBean() {
        return new MyBean("nsl");
    }
    @Bean
    public CusBean cusBean() {
        return new CusBean();
    }
}
