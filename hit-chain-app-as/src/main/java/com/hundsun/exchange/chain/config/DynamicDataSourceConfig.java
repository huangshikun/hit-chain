package com.hundsun.exchange.chain.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.config.GlobalConfig.DbConfig;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.google.common.collect.Maps;
import com.hundsun.exchange.chain.enums.DataSourceEnum;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 功能说明：动态数据源配置类<br>
 * 注意事项：<br>
 * 系统版本：version 1.0<br>
 * @author 开发人员：huangsk20406<br>
 * @date 开发时间：2021年8月3日<br>
 */
@EnableTransactionManagement
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DynamicDataSourceConfig {

//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
//        // paginationInterceptor.setOverflow(false);
//        // 设置最大单页限制数量，默认 500 条，-1 不受限制
//        // paginationInterceptor.setLimit(500);
//        return new PaginationInterceptor();
//    }

    @Bean(name = "master")
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDatasource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "slave")
    @ConfigurationProperties("spring.datasource.druid.slave")
    public DataSource slaveDatasource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "uc")
    @ConfigurationProperties("spring.datasource.druid.uc")
    public DataSource ucDatasource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> dataSourceMap = Maps.newHashMapWithExpectedSize(2);

        dataSourceMap.put(DataSourceEnum.MASTER.getValue(), masterDatasource());
        dataSourceMap.put(DataSourceEnum.SLAVE.getValue(), slaveDatasource());
        dataSourceMap.put(DataSourceEnum.UC.getValue(), ucDatasource());
        // 默认启用master数据源
        dynamicRoutingDataSource.setDefaultTargetDataSource(masterDatasource());
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);

        DynamicDataSourceContextHolder.dataSourceKeys.addAll(dataSourceMap.keySet());
        return dynamicRoutingDataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dynamicDataSource());

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        // PerformanceInterceptor(),OptimisticLockerInterceptor()
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/chain/*/*.xml"));
        // 添加分页功能
//        sqlSessionFactory.setPlugins(paginationInterceptor());
        sqlSessionFactory.setGlobalConfig(getGlobalConfig());
        return sqlSessionFactory.getObject();
    }

    @Bean
    public GlobalConfig getGlobalConfig() {
        return new GlobalConfig().setDbConfig(getDbConfig());
    }

    @Bean
    public DbConfig getDbConfig() {
        return new DbConfig().setKeyGenerator(new OracleKeyGenerator());
    }
}
