package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import com.example.demo.service.ToDoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.ToDo;
import com.example.demo.repository.ToDoRepository;
import com.example.demo.service.ToDoService;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TestService {

	@Mock
	public ToDoRepository todoRepository;
	@InjectMocks
    public ToDoServiceImpl todoService;

	ToDo t1;

 @BeforeEach
public void setUp() throws Exception {
 t1 = new ToDo(1L,"Deploy BD",true);
 //when(todoRepository.findById(1L)).thenReturn(Optional.of(t1));


 }
 @Test
	public void whenValidID_thengetTodo() {

	 when(todoRepository.findById(1L)).thenReturn(Optional.of(t1));

	 ToDo found = todoService.getToDoById(1L);
		assertThat(found.getText(), equalTo("Deploy BD"));
	}

	@Test
	public void whensaveToDo() {
		ToDo t2 = new ToDo(2, "clean code", false);
		when(todoRepository.save(t2)).thenReturn(t2);
		ToDo result = todoService.saveToDo(t2);
		assertThat(result.getId(), equalTo(2L));
		assertThat(result.getText(), equalTo("clean code"));
		assertThat(result.isCompleted(), equalTo(false));
	}

 
 
	@Test
	public void whenremoveToDo(){
		ToDo toDo = new ToDo(8,"Todo Sample 8",true);
		doNothing().when(todoRepository).delete(ArgumentMatchers.any(ToDo.class));
		todoService.removeToDo(toDo);
		verify(todoRepository,times(1)).delete(toDo);
	}



}
