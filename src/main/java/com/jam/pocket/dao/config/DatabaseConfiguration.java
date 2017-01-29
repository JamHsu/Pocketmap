package com.jam.pocket.dao.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.util.Properties;

@ComponentScan
@Configuration
@MapperScan(basePackages = "com.jam.pocket.dao.mapper")
public class DatabaseConfiguration {
	
	@Bean
    public DataSource getDataSource() {
		SimpleDriverDataSource dataSource = null;
        Properties props = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("src/main/resources/db.properties");
            props.load(fis);
 
            String dbUrl = props.getProperty("DB_URL");
            String dbName = props.getProperty("DB_NAME");
            String dbUri = dbUrl + dbName;
            String userName = props.getProperty("DB_USERNAME");
            String password = props.getProperty("DB_PASSWORD");

            // support utf8 properties
            Properties connectionProperties = new Properties();
            connectionProperties.setProperty("useUnicode", "yes");
            connectionProperties.setProperty("characterEncoding", "UTF-8");
 
            dataSource = new SimpleDriverDataSource();
            dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
            dataSource.setUrl(dbUri);
            dataSource.setConnectionProperties(connectionProperties);
            dataSource.setUsername(userName);  
            dataSource.setPassword(password);  
        } catch (Exception e) {
        	throw new RuntimeException("Get connection failed.", e);
        }
        return dataSource;
    }
 
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(getDataSource());
//        sqlSessionFactoryBean.setTypeAliases(new Class[]{User.class});
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }

}
