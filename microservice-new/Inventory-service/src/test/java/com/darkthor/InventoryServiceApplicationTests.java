package com.darkthor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class InventoryServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldGetInventory() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/inventory")
						.param("skuCodes", "sku123,sku456")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
