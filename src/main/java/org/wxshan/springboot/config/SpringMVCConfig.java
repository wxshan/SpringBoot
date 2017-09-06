package org.wxshan.springboot.config;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.wxshan.springboot.shiro.exception.DefaultExceptionHandler;

/**
 * Created by Administrator on 2017/9/6 0006.
 */
@Configuration
@EnableScheduling
public class SpringMVCConfig extends WebMvcConfigurerAdapter {

    //配置数据校验验证器
    @Bean(name = "validator")
    public LocalValidatorFactoryBean getValidator(){
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean(name = "exceptionHandlerExceptionResolver")
    public ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver(){
        return new ExceptionHandlerExceptionResolver();
    }

    @Bean
    public DefaultExceptionHandler defaultExceptionHandler(){
        return new DefaultExceptionHandler();
    }
}
