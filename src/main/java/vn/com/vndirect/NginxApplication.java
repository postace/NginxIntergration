package vn.com.vndirect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NginxApplication {

	public static void main(String[] args) {
		SpringApplication.run(NginxApplication.class, args);
	}
}
