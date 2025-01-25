package tw.com.services.kagumachi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"ecpay.payment.integration", "org.example.ecpay"})
public class KagumachiServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(KagumachiServicesApplication.class, args);
	}

}
