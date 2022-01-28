package com.luv2code.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.luv2code.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {
	
	//setup variable to hold properties
	@Autowired
	private Environment environment;
	
	//setup logger
	private Logger logger = Logger.getLogger(getClass().getName());
	

	//define the  bean for ViewResolver
	 
	@Bean
	public ViewResolver viewResolver () {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	
	//define a bean for security  dataSource
	@Bean
	public DataSource securityDataSource () {
		 
		// connection pool
		ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
		
		//set jdbc driver
		try {
			pooledDataSource.setDriverClass(environment.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			 throw new RuntimeException(e);
		}
		//log the connection pros
		logger.info("===>>> jdbc.url ::==> "+environment.getProperty("jdbc.url"));
		logger.info("===>>> jdbc.user ::==> "+environment.getProperty("jdbc.user"));
		//set db connection pros
		pooledDataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
		pooledDataSource.setUser(environment.getProperty("jdbc.user"));
		pooledDataSource.setPassword(environment.getProperty("jdbc.password"));
		//set connection pool pros
	    pooledDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
	    pooledDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
	    pooledDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
	    pooledDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		return pooledDataSource;
	}
	
	//read environment property and convert it to int 
	private int getIntProperty (String propName) {
		//read property
		String propVal = environment.getProperty(propName);
		//convert property to int 
		int propInt = Integer.parseInt(propVal);
		
		return propInt;
	}
	
}
