package com.ltp.globalsuperstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



import static org.junit.Assert.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
class GlobalSuperstoreApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Test
	void contextLoads() {
		assertNotNull(mockMvc);
	}

	@Test
	public void testShowItemForm() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.get("/?id=123");

		mockMvc.perform(request)
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("form"))
			.andExpect(model().attributeExists("item"));
	}

	@Test 
	public void testSuccessfulSubmission() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.post("/submitItem")
			.param("category", "furniture")
			.param("name", "couch")
			.param("price", String.valueOf(100d))
			.param("discount", String.valueOf(10d))
			.param("date","2021-10-11") ;
			

		mockMvc.perform(request)
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/inventory"));
	}

	@Test
	public void unSuccessfulSubmision() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.post("/submitItem")
				.param("category", "  ")
				.param("name", "  ")
				.param("price", String.valueOf(100d))
				.param("discount", String.valueOf(10d))
				.param("date", "2021-10-11");

		mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("form"));
	}
	@Test
	public void testGetItems() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/inventory");
		mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("inventory"))
				.andExpect(model().attributeExists("items"));


	}

}
