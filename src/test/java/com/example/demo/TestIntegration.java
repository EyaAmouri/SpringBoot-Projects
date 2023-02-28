package com.example.demo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TestIntegration {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("GET /todo/2")
	void testGetTodoById() throws Exception {
		// Execute the GET request
		mockMvc.perform(get("/todo/{id}", 2L))
				// Validate the response code and content type

				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))

				// Validate the returned fields
				.andExpect(jsonPath("$.id", is(2)))
				.andExpect(jsonPath("$.text", is("Clean the code")))
				.andExpect(jsonPath("$.completed", is(true)));

	}
	@Test
	public void shouldReturnDefaultMessage() throws Exception {

		this.mockMvc.perform(get("/"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello World")));
	}

}
