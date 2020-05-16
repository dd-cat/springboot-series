package com.example.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.mybatis.mapper.test1", sqlSessionTemplateRef = "test1SessionTemplate")
public class MybatisTest1Config {
    @Bean(name = "test1")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.test1")
    public DataSource test1() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public SqlSessionFactory test1SessionFactory(@Qualifier("test1") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //添加XML目录
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/test1/**/*Mapper.xml"));
        return bean.getObject();
    }

    @Bean
    @Primary
    public DataSourceTransactionManager test1TransactionManager(@Qualifier("test1") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    @Primary
    public SqlSessionTemplate test1SessionTemplate(@Qualifier("test1SessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
