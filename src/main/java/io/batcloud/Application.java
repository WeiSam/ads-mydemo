package io.batcloud;

import io.batcloud.config.DemoConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude= { DataSourceAutoConfiguration.class} )
@ComponentScan(basePackages="io.batcloud")
@Configuration
@EnableConfigurationProperties({DemoConfig.class})
public class Application {
	
	@Value("${spring.profiles.active}")
	private String profile;
	
	
	/***
	 * 当前是否dev 环境
	 * @return
	 */
	public boolean isDevEnv() {
		return profile.equals("dev");
	}
	
	/** 是否线上环境 **/
	public boolean isReleaseEnv() {
		return profile.equals("release");
	}
	
	public String getProfile() {
		return profile;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}
}