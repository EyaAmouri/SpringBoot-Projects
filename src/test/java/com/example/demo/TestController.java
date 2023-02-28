package com.example.demo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.repository.ToDoRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.entity.ToDo;
import com.example.demo.service.ToDoService;

@SpringBootTest


@AutoConfigureMockMvc
class TestController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ToDoService toDoService;
	@Mock
	private ToDoRepository toDoRepository;

	ToDo t1;

	@BeforeEach
	public void setUp(){
		 t1 = new ToDo(1, "Deploy BD", true);

	}



	@Test
	public void getTodoByID2() throws Exception {
		String expected = "{" + "\"id\":1," + "\"text\":\"Deploy BD\"," + "\"completed\":true" + "}";

		when(toDoService.getToDoById(1)).thenReturn(t1);

		mockMvc.perform(get("/todo/1")).andExpect(status().isOk()).andExpect(content().json(expected));
	}

	@Test
	@DisplayName("GET /todos success")
	void testGetTodosSuccess() throws Exception {
		// Setup our mocked service
		ToDo todo1 = new ToDo(1, "todoName1", true);
		ToDo todo2 = new ToDo(2, "todoName2", false);
		List lsttodo = new ArrayList();
		lsttodo.add(todo1);
		lsttodo.add(todo2);
		doReturn(lsttodo).when(toDoService).getAllToDo();

		// 2ème méthode:
		/*
		 * List<ToDo> todos = Arrays.asList(new ToDo(1, "clean", true), new ToDo(2,
		 * "code", false)); when(service.getAllToDo()).thenReturn(todos);
		 */

		// Execute the GET request
		mockMvc.perform(get("/todo"))
				// Validate the response code and content type
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				// Validate the returned fields
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].text", is("todoName1"))).andExpect(jsonPath("$[0].completed", is(true)))

				.andExpect(jsonPath("$[1].id", is(2))).andExpect(jsonPath("$[1].text", is("todoName2")))
				.andExpect(jsonPath("$[1].completed", is(false)));
		// Verification
		verify(toDoService, times(1)).getAllToDo();
		verifyNoMoreInteractions(toDoService);
	}




	@Test
	@DisplayName("GET /todo/1")
	void testGetTodoById() throws Exception {
		// Setup our mocked service
		ToDo todo1 = new ToDo(1, "todoname", true);
		doReturn(todo1).when(toDoService).getToDoById(1);

		// Execute the GET request
		mockMvc.perform(get("/todo/{id}", 1L))
				// Validate the response code and content type
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))

				// Validate the returned fields
				.andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.text", is("todoname")))
				.andExpect(jsonPath("$.completed", is(true)));
	}

	@Test
	public void shouldReturnDefaultMessage() throws Exception {

		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello World")));
	}

}
