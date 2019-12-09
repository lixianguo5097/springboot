package com.lxg.config.member;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author XIANGUO LI
 * @date 2019-12-6 14:23
 */
@Configuration
@MapperScan(value = "com.lxg.mapper.member",sqlSessionTemplateRef = "memberSqlSessionTemplate")
public class MemberDatasourceConfig {
    /**
     * 创建DataSource
     * @return
     */
    @Bean("memberDataSource")
    public DataSource memberDataSource(MemberConfig memberConfig) throws SQLException {
        System.out.println(memberConfig);
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(memberConfig.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(memberConfig.getPassword());
        mysqlXaDataSource.setUser(memberConfig.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        //注册到全局事务
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName(memberConfig.getUniqueResourceName());
        xaDataSource.setMinPoolSize(memberConfig.getMinPoolSize());
        xaDataSource.setMaxPoolSize(memberConfig.getMaxPoolSize());
        xaDataSource.setMaxLifetime(memberConfig.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(memberConfig.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(memberConfig.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(memberConfig.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(memberConfig.getMaxIdleTime());
        xaDataSource.setTestQuery(memberConfig.getTestQuery());
        return xaDataSource;
    }

    /**
     * 创建sqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "memberSqlSessionFactory")
    public SqlSessionFactory memberSqlSessionFactory(@Qualifier("memberDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 创建事务管理器
     * @param dataSource
     * @return
     */
    @Bean(name = "memberTransactionManager")
    public DataSourceTransactionManager memberTransactionManager(@Qualifier("memberDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 创建sqlSession模板
     * @param sqlSessionFactory
     * @return
     */
    @Bean(name = "memberSqlSessionTemplate")
    public SqlSessionTemplate memberSqlSessionTemplate(@Qualifier("memberSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}