package com.virtualpairprogrammers.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.virtualpairprogrammers.dataaccess.ActionDao;
import com.virtualpairprogrammers.dataaccess.ActionDaoJpaImpl;
import com.virtualpairprogrammers.dataaccess.CustomerDao;
import com.virtualpairprogrammers.dataaccess.CustomerDaoJpaImpl;
import com.virtualpairprogrammers.services.calls.CallHandlingService;
import com.virtualpairprogrammers.services.calls.CallHandlingServiceImpl;
import com.virtualpairprogrammers.services.customers.CustomerManagementService;
import com.virtualpairprogrammers.services.customers.CustomerManagementServiceProductionImpl;
import com.virtualpairprogrammers.services.diary.DiaryManagementService;
import com.virtualpairprogrammers.services.diary.DiaryManagementServiceProductionImpl;

@Configuration
@EnableTransactionManagement
public class ApplicationConfig 
{

	/*
	<!--  DAOs -->
	<bean id="customerDao" class="com.virtualpairprogrammers.dataaccess.CustomerDaoJpaImpl">
	</bean>
	
	<bean id="actionDao" class="com.virtualpairprogrammers.dataaccess.ActionDaoJpaImpl">
	</bean>
	*/
	
	@Bean
	public CustomerDao customerDao()
	{
		return new CustomerDaoJpaImpl();
	}
	
	@Bean
	public ActionDao actionDao()
	{
		return new ActionDaoJpaImpl();
	}
	
	/*
	<!--  Service Beans -->
	<bean id="customerService" class="com.virtualpairprogrammers.services.customers.CustomerManagementServiceProductionImpl">
		<constructor-arg ref="customerDao"/>
	</bean>
	
	<bean id="diaryService" class="com.virtualpairprogrammers.services.diary.DiaryManagementServiceProductionImpl">
		<constructor-arg ref="actionDao"/>
	</bean>
	
	<bean id="callService" class="com.virtualpairprogrammers.services.calls.CallHandlingServiceImpl">	
		<constructor-arg ref="customerService"/>
		<constructor-arg ref="diaryService"/>	
	</bean>
	*/
	
	@Bean
	public CustomerManagementService customerService(CustomerDao dao)
	{
		return new CustomerManagementServiceProductionImpl(dao);
	}

	@Bean
	public DiaryManagementService diaryService(ActionDao dao)
	{
		return new DiaryManagementServiceProductionImpl(dao);
	}
	
	@Bean
	public CallHandlingService callService(CustomerManagementService customers, DiaryManagementService diary)
	{
		return new CallHandlingServiceImpl(customers, diary);
	}
	
	/*
	<!--  Data source - warning, the application name is hardcoded into the URL for database! -->
	<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
		<property name="driverClassName" value="org.hsqldb.jdbcDriver"/>
		<property name="url" value="jdbc:hsqldb:file:../webapps/crm/WEB-INF/database.dat;shutdown=true"/>
		<property name="username" value="sa"/>
		<property name="password" value=""/>
	</bean>
	
	<bean id="entityManagerFactory" 
      class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
		
        <property name="jpaVendorAdapter">
	       <bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
	         <property name="showSql" value="true"/>
		     <property name="generateDdl" value="true"/>
	       </bean>
        </property>
     	
        <property name="dataSource" ref="dataSource"/>		
    </bean>
    */

	@Bean
	@Profile("production")
	public DataSource dataSourceProduction()
	{
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("org.hsqldb.jdbcDriver");
		ds.setUrl("jdbc:hsqldb:file:../webapps/crm/WEB-INF/database.dat;shutdown=true");
		ds.setUsername("sa");
		ds.setPassword("");
		return ds;
	}
	
	/*
	<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
		<property name="driverClassName" value="org.hsqldb.jdbcDriver"/>
		<property name="url" value="jdbc:hsqldb:file:databaseTESTING.dat;shutdown=true"/>
		<property name="username" value="sa"/>
		<property name="password" value=""/>
	</bean>
	*/
	@Bean
	@Profile("integration")
	public DataSource dataSourceTesting()
	{
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("org.hsqldb.jdbcDriver");
		ds.setUrl("jdbc:hsqldb:file:databaseTESTING.dat;shutdown=true");
		ds.setUsername("sa");
		ds.setPassword("");
		return ds;		
	}
	
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource)
	{
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		
		emf.setDataSource(dataSource);
		
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(true);
		jpaVendorAdapter.setGenerateDdl(true);		
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		return emf;
	}
	
	/*
	<bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
		<property name="entityManagerFactory" ref="entityManagerFactory"/>
	</bean>

	<tx:annotation-driven/>
	*/
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf)
	{
		JpaTransactionManager tx = new JpaTransactionManager();
		tx.setEntityManagerFactory(emf);
		return tx;
	}
}
