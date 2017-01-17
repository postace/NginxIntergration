package vn.com.vndirect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.com.vndirect.auth.TokenConsumer;
import vn.com.vndirect.auth.UtilMaker;

@SpringBootApplication
public class NginxApplication {

	public static void main(String[] args) {
		SpringApplication.run(NginxApplication.class, args);
	}

	@Bean
	public TokenConsumer consumer() {
		return new TokenConsumer(UtilMaker.getSecretKey());
	}
}
