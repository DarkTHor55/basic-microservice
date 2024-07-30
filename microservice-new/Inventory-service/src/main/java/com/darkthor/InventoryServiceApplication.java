package com.darkthor;

import com.darkthor.Model.Inventory;
import com.darkthor.Repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
            inventoryRepository.save(new Inventory( "iphone-13 ", 100));
            inventoryRepository.save(new Inventory("iphone-13-red", 50));
        };
	}

}
