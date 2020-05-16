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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.mybatis.mapper.test2", sqlSessionTemplateRef = "test2SessionTemplate")
public class MybatisTest2Config {
    @Bean(name = "test2")
    @ConfigurationProperties(prefix = "spring.datasource.test2")
    public DataSource test2() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory test2SessionFactory(@Qualifier("test2") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/test2/**/*Mapper.xml"));
        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager test2TransactionManager(@Qualifier("test2") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate test2SessionTemplate(@Qualifier("test2SessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
