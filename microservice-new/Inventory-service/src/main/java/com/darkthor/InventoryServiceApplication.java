package com.darkthor;

import com.darkthor.Model.Inventory;
import com.darkthor.Repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {
	private static final Logger logger = LoggerFactory.getLogger(InventoryServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			try {
				inventoryRepository.save(new Inventory("iphone-13", 100));
				inventoryRepository.save(new Inventory("iphone-13-red", 0));
				logger.info("Inventory data loaded successfully");
			} catch (Exception e) {
				logger.error("Error loading inventory data", e);
			}
		};
	}


}
