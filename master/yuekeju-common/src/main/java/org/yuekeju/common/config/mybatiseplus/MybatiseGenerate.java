package org.yuekeju.common.config.mybatiseplus;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 2020-7-10
 * @author szk
 * mybatise plus 生成代码
 */
public class MybatiseGenerate {
	 /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) {

        AutoGenerator autoGenerator = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //得到当前项目的路径
        String oPath = System.getProperty("user.dir");
        //生成文件输出根目录
        gc.setOutputDir(oPath + "/src/main/java");   
        //生成完成后不弹出文件框
        gc.setOpen(false);
        //文件覆盖
        gc.setFileOverride(true); 
        //不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(false);
        //XML 二级缓存
        gc.setEnableCache(false);
        //XML ResultMap
        gc.setBaseResultMap(true); 
        //XML columList
        gc.setBaseColumnList(false);
        //作者
        gc.setAuthor("suzk"); 

        //自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sDAO");
        gc.setXmlName("%sMapper");
        autoGenerator.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);   
        //设置数据库类型，我是mysql
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("kah717...");
        //指定数据库
        dsc.setUrl("jdbc:mysql://47.101.219.100:3306/yuekejucommons");  
        autoGenerator.setDataSource(dsc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);     
        // 需要生成的表
        strategy.setInclude(new String[] { "yuekeju_app_banner","yuekeju_dept","yuekeju_role","yuekeju_role_permission","yuekeju_role_user","yuekeju_sys_dict","yuekeju_sys_role_user","yuekeju_user_dept","yuekeju_user_loginpassword"});     
        strategy.setSuperServiceClass(null);
        strategy.setSuperServiceImplClass(null);
        strategy.setSuperMapperClass(null);
        autoGenerator.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.demo");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("dao");
        pc.setEntity("entity");
        pc.setXml("mapper");
        autoGenerator.setPackageInfo(pc);

        // 执行生成
        autoGenerator.execute();
    }
}
