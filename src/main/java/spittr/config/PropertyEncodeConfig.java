package spittr.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyEncodeConfig {

	@Bean
	public PropertiesFactoryBean propertiesFactoryBean() {
		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		bean.setFileEncoding("UTF-8");
		return bean;
	}
}
