//package eu.getsoftware.hotelico.hotel.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
///**
// * <br/>
// * Created by e.fanshil
// * At 25.04.2018 16:43
// */
//@Configuration
//@EnableJpaAuditing
//@EnableJpaRepositories(basePackages = { "eu.getsoftware.hotelico.hotel.repository" })
//@EnableTransactionManagement
//public class JpaConfig
//{
//	@Autowired
//	private DataSource dataSource;
//	
////	@Value("${spring.jpa.show-sql}")
////	private String showSql;
////	
////	@Value("${spring.jpa.generate-ddl}")
////	private String generateDdl;
//	
////	@Value("${entitymanager.packagesToScan}")
////	private String packagesToScan;
//	
//	@Bean
//	public PlatformTransactionManager annotationDrivenTransactionManager() {
//		return new JpaTransactionManager();
//	}
//	
////	@Bean
////	public JpaVendorAdapter jpaVendorAdapter() {
////		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
////		adapter.setShowSql(Boolean.valueOf(showSql));
////		adapter.setGenerateDdl(Boolean.valueOf(generateDdl));
////		return adapter;
////	}
////	
////	@Bean
////	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws ClassNotFoundException {
////		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
////		factoryBean.setDataSource(dataSource);
//////		factoryBean.setPackagesToScan(packagesToScan);
////		factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
////		
////		return factoryBean;
////	}
//	
////	@Bean
////	public JpaTransactionManager transactionManager() throws ClassNotFoundException {
////		JpaTransactionManager transactionManager = new JpaTransactionManager();
////		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
////		return transactionManager;
////	}
//}
