package com.wenshuo.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.wenshuo"})
public class OssApplicationStart {
    public static void main(String[] args) {
        SpringApplication.run(OssApplicationStart.class, args);
    }
}
