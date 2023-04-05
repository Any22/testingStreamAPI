package com.unitTesting.streamunitTestdemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.unitTesting.streamunitTestdemo.model.Customer;

@SpringBootApplication
public class StreamUnitTestDemoApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(StreamUnitTestDemoApplication.class, args);
	}

	 @Bean
	    public Customer customer() {

		return new Customer();

	}
}
