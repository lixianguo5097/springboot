package com.lxg.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.type.JdbcType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MybatisPlus配置
 * @author LXG
 * @date 2019-10-31
 */
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {

    /**
     * 防止 修改与删除时对全表进行操作
     * @return
     */
    @Bean
    public BlockAttackInnerInterceptor blockAttackInnerInterceptor(){
        return new BlockAttackInnerInterceptor();
    }


    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }


    /**
     * ConfigurationCustomizer，这里引用的是MyBatisPlus自定义的一个和MyBatis同名的接口，com.baomidou.mybatisplus.spring.boot.starter.ConfigurationCustomizer，
     * 因此必须使用MyBatisPlus的ConfigurationCustomizer才行
     * @return
     */
    public ConfigurationCustomizer configurationCustomizer(){
        return new ConfigurationCustomizer() {
            @Override
            public void customize(MybatisConfiguration configuration) {
                configuration.setCacheEnabled(true);
                configuration.setMapUnderscoreToCamelCase(true);
                configuration.setCallSettersOnNulls(true);
                configuration.setJdbcTypeForNull(JdbcType.NULL);
            }
        };
    }
}
