package com.lxg.config.member;

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
    @Bean(name = "memberSqlSessionTemplate")
    public SqlSessionTemplate memberSqlSessionTemplate(@Qualifier("memberSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}