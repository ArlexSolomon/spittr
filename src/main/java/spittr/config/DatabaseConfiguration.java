package spittr.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.tomcat.jdbc.pool.DataSourceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Import({ spittr.config.DatabaseJpaConfiguration.class })
@PropertySource("classpath:/application.properties")
public class DatabaseConfiguration {

	@Value("${jdbc.url}")
	private String jdbcUrl;

	@Value("${jdbc.username}")
	private String jdbcUsername;

	@Value("${jdbc.password}")
	private String jdbcPassword;

	// @Bean
	@Bean(name = "primaryDS")
	@Qualifier("primaryDS")
	@Primary
	public DataSource primaryDS() {
		DataSourceFactory factory = new DataSourceFactory();

		Properties props = new Properties();
		props.setProperty("testWhileIdle", "true");
		props.setProperty("testOnBorrow", "true");
		props.setProperty("testOnReturn", "false");
		props.setProperty("validationQuery", "SELECT 1");
		props.setProperty("validationInterval", "30000");
		props.setProperty("timeBetweenEvictionRunsMillis", "300000");
		props.setProperty("maxActive", "60");
		props.setProperty("minIdle", "5");
		props.setProperty("maxIdle", "10");
		props.setProperty("maxWait", "10000");
		props.setProperty("initialSize", "10");
		props.setProperty("removeAbandonedTimeout", "60");
		props.setProperty("removeAbandoned", "true");
		props.setProperty("logAbandoned", "true");
		props.setProperty("minEvictableIdleTimeMillis", "300000");
		props.setProperty("jmxEnabled", "true");
		props.setProperty("jdbcInterceptors",
				"org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
		props.setProperty("username", jdbcUsername);
		props.setProperty("password", jdbcPassword);
		props.setProperty("driverClassName", "com.mysql.jdbc.Driver");
		props.setProperty("url", jdbcUrl);
		try {
			return factory.createDataSource(props);
		} catch (Exception e) {
			return null;
		}
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
		jpaVendorAdapter.setDatabase(Database.MYSQL);
		jpaVendorAdapter.setGenerateDdl(false);
		jpaVendorAdapter.setShowSql(true);
		return jpaVendorAdapter;
	}
}
