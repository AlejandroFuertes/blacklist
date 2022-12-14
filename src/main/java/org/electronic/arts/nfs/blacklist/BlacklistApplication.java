package org.electronic.arts.nfs.blacklist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan(basePackages = { "org.electronic.arts.nfs.blacklist" })
@ComponentScan(basePackageClasses = { BlacklistApplication.class })
@Configuration
public class BlacklistApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlacklistApplication.class, args);
	}

}
