package com.jbs.posbe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PosbeApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(PosbeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PosbeApplication.class, args);
		logger.info("PosbeApplication started successfully...");
		logger.info("Chitrita Bhattacharjee");
		logger.info("ABHIRUP SAHA");
		logger.info("This is from Agni-Glitch branch...");
		logger.info("This is another log message from Agni-Glitch branch...");
		logger.info("This is from Subhransu's branch.....");
	}

}
