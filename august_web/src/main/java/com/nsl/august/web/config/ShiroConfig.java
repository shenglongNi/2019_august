package com.nsl.august.web.config;

import com.google.common.collect.Maps;
import com.nsl.august.web.shiro.AdminCredentialsMatchers;
import com.nsl.august.web.shiro.AdminRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ShiroConfig {


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        Map<String, String> filterChainMap = Maps.newLinkedHashMap();
//        filterChainMap.put("/login", "anon");

        filterChainMap.put("/loginSubmit", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);

        shiroFilterFactoryBean.setSecurityManager(this.defaultWebSecurityManager());
        return shiroFilterFactoryBean;

    }


    @Bean
    public Realm AdminRealm() {
        AdminRealm realm = new AdminRealm();
        realm.setCredentialsMatcher(adminCredentialsMatchers());

        return realm;
    }

    @Bean
    public CredentialsMatcher adminCredentialsMatchers() {
        return new AdminCredentialsMatchers();
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        webSecurityManager.setRealm(this.AdminRealm());

        return webSecurityManager;
    }


}
