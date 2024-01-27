package com.hrs.parceltracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = {"com.hrs.parceltracker"})
@EnableSwagger2
public class ParcelTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParcelTrackerApplication.class, args);
	}

}
