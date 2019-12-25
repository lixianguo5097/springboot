package com.lxg.config.order;

import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author LXG
 * @date 2019-12-6
 */
@Configuration
@MapperScan(value = "com.lxg.mapper.order",sqlSessionTemplateRef = "orderSqlSessionTemplate")
public class OrderDatasourceConfig {
    /**
     * 创建DataSource
     * @return
     */
    @Bean("orderDataSource")
    public DataSource orderDataSource(OrderConfig orderConfig) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(orderConfig.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(orderConfig.getPassword());
        mysqlXaDataSource.setUser(orderConfig.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        //注册到全局事务
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName(orderConfig.getUniqueResourceName());
        xaDataSource.setMinPoolSize(orderConfig.getMinPoolSize());
        xaDataSource.setMaxPoolSize(orderConfig.getMaxPoolSize());
        xaDataSource.setMaxLifetime(orderConfig.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(orderConfig.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(orderConfig.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(orderConfig.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(orderConfig.getMaxIdleTime());
        xaDataSource.setTestQuery(orderConfig.getTestQuery());
        return xaDataSource;
    }

    /**
     * 创建sqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "orderSqlSessionFactory")
    public SqlSessionFactory orderSqlSessionFactory(@Qualifier("orderDataSource") DataSource dataSource) throws Exception {
        //逻辑删除 如果值为1,表示已经删除,如果为0,表示未删除
        GlobalConfiguration globalConfig = new GlobalConfiguration();
        globalConfig.setLogicDeleteValue("1");
        globalConfig.setLogicNotDeleteValue("0");
        globalConfig.setSqlInjector(new LogicSqlInjector());
        //分页插件
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.addInterceptor(new PaginationInterceptor());

        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setGlobalConfig(globalConfig);

        return sqlSessionFactoryBean.getObject();
    }


    /**
     * 创建sqlSession模板
     * @param sqlSessionFactory
     * @return
     */
    @Bean(name = "orderSqlSessionTemplate")
    public SqlSessionTemplate orderSqlSessionTemplate(@Qualifier("orderSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
