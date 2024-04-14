//package com.hundsun.exchange.chain.common;
//
//
//import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.FileOutConfig;
//import com.baomidou.mybatisplus.generator.config.GlobalConfig;
//import com.baomidou.mybatisplus.generator.config.PackageConfig;
//import com.baomidou.mybatisplus.generator.config.StrategyConfig;
//import com.baomidou.mybatisplus.generator.config.TemplateConfig;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//
///**
// * @auther: wsj
// * @date: 2019/11/6 09
// * @description: 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
// */
//
//
//public class MybatisGenerator {
//
//
//    /**
//     * 读取控制台内控
//     *
//     * @param tip
//     * @return null
//     */
//    public static String scanner(String tip) {
//        Scanner scanner = new Scanner(System.in);
//        StringBuilder help = new StringBuilder();
//        help.append("请输入" + tip + "：");
//        System.out.println(help.toString());
//        if (scanner.hasNext()) {
//            String ipt = scanner.next();
//            if (StringUtils.isNotEmpty(ipt)) {
//                return ipt;
//            }
//        }
//        throw new MybatisPlusException("请输入正确的" + tip + "！");
//    }
//
//    public static void main(String[] args) {
//        // 代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//
//
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        //无需修改 默认获取当前项目的实际存储路径 如 D:\study\mp
//        String projectPath = System.getProperty("user.dir");
//        //System.out.println(projectPath+"LLLLL");
//        gc.setFileOverride(true);// 是否覆盖同名文件，默认是false
//        //设置文件输出目录 如 D:\study\mp/src/main/java
//        gc.setOutputDir(projectPath + "/src/main/java");
//        gc.setAuthor("wsj");
//        gc.setOpen(false);
//        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
//        gc.setEnableCache(false);// XML 二级缓存
//        gc.setBaseResultMap(true);// XML ResultMap
//        gc.setBaseColumnList(false);// XML columList
//        // gc.setSwagger2(true); 实体属性 Swagger2 注解
//        mpg.setGlobalConfig(gc);
//
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://localhost:3306/jie?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false");
//        // dsc.setSchemaName("public");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("123456");
//        mpg.setDataSource(dsc);
//        /* 自定义文件命名，注意 %s 会自动填充表实体属性！ */
//        gc.setMapperName("%sDao");
//        gc.setXmlName("%sDao");
//        //接口加I填充前面
//        gc.setServiceName("I%sService");
//        //接口实现类
//        gc.setServiceImplName("%sServiceImpl");
//        // gc.setControllerName("%sAction");
//
//        // 包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
//        pc.setParent("com.jie");
//        mpg.setPackageInfo(pc);
//
//        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };
//
//        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
//        // 如果模板引擎是 velocity
//        // String templatePath = "/templates/mapper.xml.vm";
//
//        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//        /*cfg.setFileCreate(new IFileCreate() {
//            @Override
//            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
//                // 判断自定义文件夹是否需要创建
//                checkDir("调用默认方法创建的目录");
//                return false;
//            }
//        });*/
//
//
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);
//
//        // 配置模板
//        TemplateConfig templateConfig = new TemplateConfig();
//
//        // 配置自定义输出模板
//        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
//        // templateConfig.setEntity("templates/entity2.java");
//        // templateConfig.setService();
//        // templateConfig.setController();
//
//        templateConfig.setXml(null);
//        mpg.setTemplate(templateConfig);
//
//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        //strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
//        //是否启用lombok
//        strategy.setEntityLombokModel(true);
//        strategy.setRestControllerStyle(true);
//        // 公共父类
//        //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
//        // 写于父类中的公共字段
//        //strategy.setSuperEntityColumns("id");
//        //若想生成整个数据库表 则注释下面三行
//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
//        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
//
//        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//        mpg.execute();
//    }
//}
