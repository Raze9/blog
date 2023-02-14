package com.blog;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import jdk.internal.org.objectweb.asm.tree.ParameterNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GeneratorTest {


    /**
     * 表名
     */
    private static final String
            tableName =
            "user";
    /**
     * 表前缀
     */
    private static final String tablePrefix = "system";
    /**
     * 模块包名,会生成包名,并且会在controller层的url自动添加前缀
     */
    private static final String moduleName = "";
    /**
     * 作者
     */
    private static final String author = "苦瓜不苦";
    /**
     * mysql
     */
    private static final String url = "jdbc:mysql://localhost:3306/wheel?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&serverTimezone=GMT%2b8";
    private static final String username = "root";
    private static final String password = "root";
    /**
     * 上级包名
     */
    private static final String parentPackName = "org.example";


    /**
     * 自动生成模板
     */
    @Test
    public void test() {

        AutoGenerator generator = new AutoGenerator();

        // 全局配置
        GlobalConfig global = new GlobalConfig();
        String path = System.getProperty("user.dir");
        global.setOutputDir(path + "/src/main/java");
        global.setAuthor(author);
        global.setOpen(false);
        global.setIdType(IdType.ASSIGN_ID);
        global.setServiceName("%sService");
        global.setServiceImplName("%sServiceImpl");
        generator.setGlobalConfig(global);

        // 数据源配置
        DataSourceConfig source = new DataSourceConfig();
        source.setUrl(url);
        source.setDriverName("com.mysql.cj.jdbc.Driver");
        source.setUsername(username);
        source.setPassword(password);
        generator.setDataSource(source);

        // 包配置
        PackageConfig pack = new PackageConfig();
        pack.setModuleName(moduleName);
        pack.setParent(parentPackName);
        generator.setPackageInfo(pack);


        // 自定义配置
        InjectionConfig injection = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 模板引擎freemarker
        String freemarkerTemplates = "/templates/mapper.xml.ftl";
        // 模板引擎velocity
        String velocityTemplates = "/templates/repository.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> fileOutList = new ArrayList<>();
        fileOutList.add(new FileOutConfig(freemarkerTemplates) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名,如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return path + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        injection.setFileOutConfigList(fileOutList);
        generator.setCfg(injection);


        // 模板配置
        TemplateConfig template = new TemplateConfig();
        template.setXml(null);
        generator.setTemplate(template);

        // 策略配置,数据库表配置
        StrategyConfig strategy = new StrategyConfig();
        // 映射到实体类命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 映射到实体字段名命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 自定义继承entity类,生成实体类时继承entity
        strategy.setSuperEntityClass(ParameterNode.class);
        // 排除公共实体字段
        strategy.setSuperEntityColumns("create_id", "create_name", "create_time", "update_id", "update_name", "update_time");
        // 实体是否为lombok模型
        strategy.setEntityLombokModel(true);
        // 生成@RestController控制器
        strategy.setRestControllerStyle(true);
        // 表名
        strategy.setInclude(tableName.split(","));
        // 是否生成实体时，生成字段注解 默认false;
        strategy.setEntityTableFieldAnnotationEnable(true);
        // 驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        // 表前缀
        // strategy.setTablePrefix(tablePrefix);
        generator.setStrategy(strategy);

        // 在代码生成器主类上配置模板引擎
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        //生成
        generator.execute();
    }



}
