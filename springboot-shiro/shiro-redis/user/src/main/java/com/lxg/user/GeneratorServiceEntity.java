package com.lxg.user;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

/**
 * @ClassName GeneratorServiceEntity
 * @Author ph
 * @Version 1.0
 * @Description
 * @Date 2020/3/4 16:13
 */
public class GeneratorServiceEntity {
    @Test
    public void generateCode() {
        String packageName = "com.hqx.user";
        //user -> UserDao, 设置成true: user -> IUserService
        boolean serviceNameStartWithI = false;
        generateByTables(serviceNameStartWithI, packageName,
                "sys_permission","sys_role","sys_role_permission","sys_user","sys_user_role");
    }

    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://192.168.2.191:3306/w20200221_04_java";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("hqxkj168++")
                .setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                //修改替换成你需要的表名，多个表名传数组
                .setInclude(tableNames)
                // 此处可以修改为您的表前缀
                .setTablePrefix(new String[]{"t_"});
        config.setActiveRecord(false)
                .setAuthor("PH")
                .setOutputDir("D:\\ideaProject\\W20200221_04_JAVA\\user\\src\\main\\java")
                .setFileOverride(false);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        // XML 二级缓存
        config.setEnableCache(false);
        // XML ResultMap
        config.setBaseResultMap(false);
        // XML columList
        config.setBaseColumnList(false);
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setEntity("model")
                ).execute();
    }


}
