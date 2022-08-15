package com.lxg;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @ClassName GeneratorServiceEntity
 * @Description 代码自动生成器
 * @Author ph
 * @Date 2020/7/2 10:53
 * @Version 1.0
 **/
public class GeneratorServiceEntity {


    /**
     * 使用步骤：
     * 1：修改outputDir地址
     * 2：数据库连接地址、账号密码
     * 3：包名parent
     * 4: 生成的表名setInclude，逗号隔开
     * 5：自定义要求，例如是否覆盖、是否要加IServer
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 1.全局配置
        GlobalConfig gc = new GlobalConfig();
        //todo 修改输出路径
        gc.setOutputDir("D:\\idea_workspace\\springboot\\springboot-postgre\\src\\main\\java");
        //todo 修改作者
        gc.setAuthor("lxg");
        gc.setOpen(false);
        //设置时间字段的类型Date
        // 是否覆盖
        gc.setFileOverride(false);
        // 去Service的I前缀 IUserService -> UserService
        gc.setServiceName("%sService");
        mpg.setGlobalConfig(gc);

        // 2.数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        //todo 修改数据源配置
        dsc.setUrl("jdbc:postgresql://192.168.56.102:5432/postgres");
        dsc.setDriverName("org.postgresql.Driver");
        dsc.setUsername("postgres");
        dsc.setPassword("postgres");
        dsc.setSchemaName("public");
        mpg.setDataSource(dsc);

        // 3.包配置
        PackageConfig pc = new PackageConfig();
        //todo 修改对应的模块名字
        pc.setParent("com.lxg");
        mpg.setPackageInfo(pc);

        // 4.策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 命名规则
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //自动lombok
        strategy.setEntityLombokModel(true);
        //RestController标签
        strategy.setRestControllerStyle(true);
        //todo 修改替换成你需要的表名，多个表名逗号隔开
        strategy.setInclude("car");
        strategy.setControllerMappingHyphenStyle(true);
        //表名是否有前缀
        strategy.setTablePrefix("");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
