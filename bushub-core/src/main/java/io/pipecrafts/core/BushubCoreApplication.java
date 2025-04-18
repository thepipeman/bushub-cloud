package io.pipecrafts.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("io.pipecrafts")
@ConfigurationPropertiesScan
public class BushubCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BushubCoreApplication.class, args);
	}

}
