package xyz.refrain.onlineedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Application Entrance
 *
 */
//@ComponentScan(basePackages = "xyz.refrain.onlineedu")
@EnableScheduling
@ConfigurationPropertiesScan("xyz.refrain.onlineedu.config.properties")
@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class})
public class OnlineEduApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineEduApplication.class, args);
	}

}
