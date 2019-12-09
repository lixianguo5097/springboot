package com.lxg;

import com.lxg.config.member.MemberConfig;
import com.lxg.config.order.OrderConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author XIANGUO LI
 * @date 2019-12-3 17:55
 */
@SpringBootApplication
@EnableConfigurationProperties({MemberConfig.class, OrderConfig.class})
public class MultiDatasourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MultiDatasourceApplication.class);
    }
}
