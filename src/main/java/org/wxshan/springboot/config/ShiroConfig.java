package org.wxshan.springboot.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.wxshan.springboot.shiro.AdvancedFilterFactoryBean.MyFilterFactoryBean;
import org.wxshan.springboot.shiro.Session.SessionService;
import org.wxshan.springboot.shiro.realm.StatelessRealm;

/**
 * Created by Administrator on 2017/9/6 0006.
 * shiro配置文件（redis实现session的远程持久化+ecache实现本地物理机的session缓存，减少远程读取操作）
 */
public class ShiroConfig {

    @Bean(name = "ehCacheManager")
    public EhCacheManager ehCacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:cache/ehcache.xml");
        return cacheManager;
    }

    //配置自定义的密码比较器
    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        return hashedCredentialsMatcher;
    }

    @Bean(name = "sessionIdGenerator")
    public JavaUuidSessionIdGenerator javaUuidSessionIdGenerator(){
        return new JavaUuidSessionIdGenerator();
    }

    @Bean(name = "sessionIdCookie")
    public SimpleCookie simpleCookie(){
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(2592000);
        simpleCookie.setName("ROCKFISHID");
        return simpleCookie;
    }

    @Bean(name = "sessionManager")
    public DefaultWebSessionManager defaultWebSessionManager(){
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setGlobalSessionTimeout(2592000000L);
        defaultWebSessionManager.setDeleteInvalidSessions(true);
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
        defaultWebSessionManager.setSessionIdCookieEnabled(true);
        defaultWebSessionManager.setSessionValidationInterval(3600000);
        defaultWebSessionManager.setSessionDAO(sessionService());
        defaultWebSessionManager.setSessionIdCookie(simpleCookie());
        return defaultWebSessionManager;
    }

    @Bean(name = "sessionDAO")
    public SessionService sessionService(){
        SessionService sessionService = new SessionService();
        sessionService.setActiveSessionsCacheName("shiro-activeSessionCache");
        sessionService.setSessionIdGenerator(javaUuidSessionIdGenerator());
        return sessionService;
    }

    @Bean(name = "statelessRealm")
    public StatelessRealm statelessRealm(){
        StatelessRealm statelessRealm = new StatelessRealm();
        statelessRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return statelessRealm;
    }

    @Bean (name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(statelessRealm());
        defaultWebSecurityManager.setSessionManager(defaultWebSessionManager());
        defaultWebSecurityManager.setCacheManager(ehCacheManager());
        return defaultWebSecurityManager;
    }

    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(){
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvokingFactoryBean.setArguments(new Object[]{defaultWebSecurityManager()});
        return methodInvokingFactoryBean;
    }

    @Bean(name = "shiroFilter")
    public MyFilterFactoryBean myFilterFactoryBean(){
        MyFilterFactoryBean myFilterFactoryBean = new MyFilterFactoryBean();
        myFilterFactoryBean.setSecurityManager(defaultWebSecurityManager());
        return myFilterFactoryBean;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager());
        return authorizationAttributeSourceAdvisor;
    }
}
