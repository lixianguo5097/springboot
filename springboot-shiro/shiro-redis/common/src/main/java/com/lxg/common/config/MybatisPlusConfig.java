package com.lxg.common.config;

import com.baomidou.mybatisplus.mapper.ISqlInjector;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by LiuLP on 2019/3/29.
 */
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件，自动识别数据库类型
     * 多租户，请参考官网【插件扩展】
     * @author liboyan
     * @date 2018-08-03 09:43
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    /**
     *
     * 功能描述: SQL执行效率插件， 设置 dev test 环境开启
     * @author liboyan
     * @date 2018-08-03 09:43
     */
    @Bean
    @Profile({"test"})
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor().setMaxTime(3000).setFormat(true);
    }

    /**
     *
     * 功能描述: 逻辑删除
     * @author liboyan
     * @date 2018-08-03 09:43
     */
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }

    /**
     *
     * 功能描述: 公共字段自动填充
     * @author liboyan
     * @date 2018-08-03 09:43
     */
    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return  new MyMetaObjectHandler();
    }
}
