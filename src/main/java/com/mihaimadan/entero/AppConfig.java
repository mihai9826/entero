package com.mihaimadan.entero;



import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class AppConfig {
	

	@Bean("enteroSource")//or @Primary
	public DataSource theDataSource() {
		
		ComboPooledDataSource theDataSource = new ComboPooledDataSource();
		
		
		// set db connection props
		theDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/entero?useSSL=false");
		theDataSource.setUser("entero");
		theDataSource.setPassword("entero");
		
		theDataSource.setInitialPoolSize(5);
		theDataSource.setMinPoolSize(5);
		theDataSource.setMaxPoolSize(20);		
		theDataSource.setMaxIdleTime(30000);

		return theDataSource;
	}
	
	@Bean("entero")
	public LocalSessionFactoryBean sessionFactory(){
		
		// create session factory
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		// set the properties
		sessionFactory.setDataSource(theDataSource());
		sessionFactory.setPackagesToScan("com.mihaimadan.entero.entity");
		
		return sessionFactory;
	}
	
	@Bean
	@Primary
	@Autowired
	public HibernateTransactionManager transactionManager(@Qualifier("entero") SessionFactory sessionFactory) {
		
		// setup transaction manager based on session factory
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}	
	
	@Bean("dermatoSource")
	public DataSource dermatoDataSource() {
		
		ComboPooledDataSource theDataSource = new ComboPooledDataSource();
		
		
		// set db connection props
		theDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/dermato?useSSL=false");
		theDataSource.setUser("entero");
		theDataSource.setPassword("entero");
		
		theDataSource.setInitialPoolSize(5);
		theDataSource.setMinPoolSize(5);
		theDataSource.setMaxPoolSize(20);		
		theDataSource.setMaxIdleTime(30000);

		return theDataSource;
	}
	
	@Bean("dermato")
	public LocalSessionFactoryBean dermatoSessionFactory(){
		
		// create session factory
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		// set the properties
		sessionFactory.setDataSource(dermatoDataSource());
		sessionFactory.setPackagesToScan("com.mihaimadan.entero.entity");
		
		return sessionFactory;
	}
	
	@Bean
	@Qualifier("dermatoTx")
	@Autowired
	public HibernateTransactionManager dermatoTransactionManager(@Qualifier("dermato") SessionFactory sessionFactory) {
		
		// setup transaction manager based on session factory
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}

}
