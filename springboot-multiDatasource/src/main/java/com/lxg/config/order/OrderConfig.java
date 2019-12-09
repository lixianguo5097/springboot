package com.lxg.config.order;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author XIANGUO LI
 * @date 2019-12-9 15:13
 */
@Data
@ConfigurationProperties(prefix = "spring.datasource.order")
public class OrderConfig {
    private String url;
    private String username;
    private String password;
    private int minPoolSize;
    private int maxPoolSize;
    private int maxLifetime;
    private int borrowConnectionTimeout;
    private int loginTimeout;
    private int maintenanceInterval;
    private int maxIdleTime;
    private String testQuery;
    private String uniqueResourceName;
}
