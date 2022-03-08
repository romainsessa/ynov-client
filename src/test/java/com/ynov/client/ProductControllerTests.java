package com.ynov.client;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(value = "romain")
	public void testPostNewProduct() throws Exception {
		mockMvc.perform(post("/private/products").param("name", "test").param("description", "desc").param("cost", "50")
				.with(csrf())).andExpect(status().is(302)); // J'attends une 302 car après la sauvegarde une redirection est faite.

		mockMvc.perform(get("/private/products")).andExpect(status().isOk()).andDo(print());

	}

	@Test
	@WithMockUser(value = "romain")
	public void testGetProducts() throws Exception {
		mockMvc.perform(get("/private/products")).andExpect(status().isOk()).andDo(print());
	}

}
