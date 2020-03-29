package org.lms;

import org.lms.configurations.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SwaggerConfiguration.class)
public class LeaveManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaveManagementAppApplication.class, args);
	}
}
