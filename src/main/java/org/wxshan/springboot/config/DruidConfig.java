package org.wxshan.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wxshan.springboot.utils.LogException;
import org.wxshan.springboot.utils.log;

import java.sql.SQLException;


/**
 * Created by Administrator on 2017/9/6 0006.
 * 德鲁伊数据库连接池配置，打开sql语句监控管理页面（ip:prot/druid2/login.html）
 */
@Configuration
@Log4j2
@MapperScan(basePackages ="org.wxshan.springboot.Mapper")
public class DruidConfig {

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("${spring.datasource.connectionProperties}")
    private String connectionProperties;

    @Bean
    public DruidDataSource dataSource(DataSourceProperties dataSourceProperties){
        DruidDataSource druidDataSource = (DruidDataSource) dataSourceProperties.initializeDataSourceBuilder().type(DruidDataSource.class).build();
        try{
            druidDataSource.setFilters(filters);
            druidDataSource.setConnectionProperties(connectionProperties);
        } catch (SQLException e){
            log.error(LogException.getTrace(e));
        }
        return druidDataSource;
    }

    @Bean
    public ServletRegistrationBean DruidStatViewServle2(){

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid2/*");

        //添加初始化参数：initParams
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        //登录查看信息的账号密码
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","123456");

        //是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean druidStatFilter2(){

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());

        //添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");

        //添加不需要的格式信息
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*,*.html,*.hml,*jpeg,*.bmp,*.txt,*.mp4");

        return filterRegistrationBean;
    }
}
