package com.sunshine.hardware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 *
 * TODO:启动类
 *
 * @author liuyicong
 * @Date 2018年11月26日 上午9:48:15
 */
@SpringBootApplication //Spring Boot核心注解，用于开启自动配置
@EnableScheduling
@MapperScan("com.sunshine.hardware")
public class HardWareApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }

    public static void main( String[] args ) {
        SpringApplication.run(HardWareApplication.class, args);
    }
}
