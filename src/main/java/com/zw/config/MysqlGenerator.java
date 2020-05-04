package com.zw.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MysqlGenerator {
    /**
     * 代码生成 示例代码
     */

    public static void main(String[] args) {
        //1.全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true)//是否支持AR模式
                .setAuthor("yzj")//作者
                .setOutputDir("D:\\yzj\\demo2\\src\\main\\java")//生成路径
                .setFileOverride(true)//文件覆盖
                .setIdType(IdType.AUTO)//主键策略
                .setServiceImplName("%sService")//设置生成的service接口的名字的首字母是否为I
                //IEmployeeService
                .setBaseResultMap(true)
                .setBaseColumnList(true);
        //2.数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)//设置数据库类型
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/zw?useUnicode=true&characterEncoding=utf-8&useLegacyDatetimeCode=false&serverTimezone=UTC")
                .setUsername("root")
                .setPassword("root");
        //3.策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true)//全局大写命名
//                .setDbColumnUnderline(true)//指定表名是否使用下划线
                .setNaming(NamingStrategy.underline_to_camel)//数据库表映射到实体的命名策略
                .setTablePrefix("tbz_")
                .setInclude("tbz_bill_details");//生成的表

        //4.包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.zw")
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("beans")
                .setXml("mapper");
        //5.整合配置
        AutoGenerator ag = new AutoGenerator();

        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);

        //6.执行
        ag.execute();

    }
}


