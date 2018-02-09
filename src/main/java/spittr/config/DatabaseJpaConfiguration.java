package spittr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager", basePackages = {
		"spittr.role.repositories" })
public class DatabaseJpaConfiguration {
	@Autowired
	@Qualifier("primaryDS")
	private DataSource primaryDS;

	/**
	 * 指定本地容器管理 EntityManagerFactory,从而进行细粒度控制 \
	 * 
	 * @param jpaVendorAdapter
	 *            指定实现厂商专用特性，即generateDdl= false表示不自动生成DDL，database=
	 *            MySQL表示使用MySQL数据库；
	 * @return 本地容器管理EntityManagerFactory
	 */
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
		lemfb.setDataSource(primaryDS);
		lemfb.setJpaVendorAdapter(jpaVendorAdapter);
		lemfb.setPackagesToScan("spittr.role.entity");
		lemfb.setJpaProperties(getJpaProperties());
		return lemfb;
	}

	private Properties getJpaProperties() {
		return new Properties() {
			private static final long serialVersionUID = 9102937342519479384L;

			{
				// setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
				setProperty("hibernate.hbm2ddl.auto", "update");
				setProperty("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
				setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
				setProperty("hibernate.show_sql", "true");
				setProperty("hibernate.format_sql", "true");
			}
		};
	}

	@Bean
	@Primary
	public JpaTransactionManager transactionManager(JpaVendorAdapter jpaVendorAdapter) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory(jpaVendorAdapter).getObject());
		return jpaTransactionManager;
	}
}
