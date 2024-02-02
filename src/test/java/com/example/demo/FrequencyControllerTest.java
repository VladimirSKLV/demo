package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FrequencyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testCalculateFrequency() throws Exception {
		String inputString = "aaaaabcccc";
		String requestJson = "{\"inputString\":\"" + inputString + "\"}";

		mockMvc.perform(
				MockMvcRequestBuilders.post("/calculate-frequency")
						.content(requestJson)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
		)
				.andExpect(status().isOk())
				.andExpect(content().json("{\"result\":{\"a\":5,\"c\":4,\"b\":1}}"));
		System.out.println(content());
	}

}
