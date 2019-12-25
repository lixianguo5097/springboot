package com.lxg.config;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 代码生成器
 * @author LXG
 * @date 2019-10-31
 */
public class CodeGenerator{

    /**
     * @param args
     * @Title: main
     * @Description: 根据数据库生成表格
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //代码存放地址
        gc.setOutputDir( "D://code4" );
        gc.setFileOverride( true );
        // 不需要ActiveRecord 特性的请改为false
        gc.setActiveRecord( false );
        // XML 二级缓存
        gc.setEnableCache( false );
        // XML ResultMap
        gc.setBaseResultMap( true );
        // XML columList
        gc.setBaseColumnList( false );
        // 作者
        gc.setAuthor( "LXG" );

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName( "%sController" );
        gc.setServiceName( "%sService" );
        gc.setServiceImplName( "%sServiceImpl" );
        gc.setMapperName( "%sMapper" );
        gc.setXmlName( "%sMapper" );
        mpg.setGlobalConfig( gc );

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType( DbType.MYSQL );
        dsc.setDriverName( "com.mysql.jdbc.Driver" );
        dsc.setUsername( "root" );
        dsc.setPassword( "root" );
        dsc.setUrl( "jdbc:mysql://localhost:3306/springboot?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL = false&serverTimezone = UTC" );
        mpg.setDataSource( dsc );

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 此处可以修改为您的表前缀
        strategy.setTablePrefix( new String[]{"t_"} );
        // 表名生成策略
        strategy.setNaming( NamingStrategy.underline_to_camel );
        // 需要生成的表
        strategy.setInclude( new String[]{"t_user"} );

        strategy.setSuperServiceClass( null );
        strategy.setSuperServiceImplClass( null );
        strategy.setSuperMapperClass( null );

        mpg.setStrategy( strategy );

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent( "com.lxg.springboot.dao.mybatisplus" );
        pc.setController( "com.lxg.controller" );
        pc.setService( "service" );
        pc.setServiceImpl( "service/serviceImpl" );
        pc.setMapper("mapper");
        pc.setEntity( "entity" );
        pc.setXml( "xml" );
        mpg.setPackageInfo( pc );
        // 执行生成
        mpg.execute();
    }
}