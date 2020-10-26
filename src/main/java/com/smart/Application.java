package com.smart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
//可以使用@SpringBootApplication代替以上三个注解
@SpringBootApplication
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer {//继承了springboot提供的servlet初始化

    @Bean
    public PlatformTransactionManager txManager(DataSource   dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    public static void main(String[] args) throws Exception{
        SpringApplication.run(Application.class,args);
    }

    //重写SpringBootServletInitializer的configure方法
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(Application.class);
    }
}
