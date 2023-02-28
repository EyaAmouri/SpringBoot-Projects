package com.example.demo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entity.ToDo;
import com.example.demo.repository.ToDoRepository;
@DataJpaTest
class TestRepository {
	@Autowired
	private ToDoRepository todoRepository;
	@Test
    public void givenTodo_whenSave_thenFindAll() {
     // given
		ToDo t1 = new ToDo("Clean the code",true);
        ToDo t2 = new ToDo("Deploy the jar",true);
        todoRepository.save(t2);
        todoRepository.save(t1);
        // when
        List<ToDo> todoList = todoRepository.findAll();
        
        // then
        assertThat(6,equalTo(todoList.size()));
    }
	@Test
    public void givenTodo_whenSave_thenGetOk() {
     // given
        ToDo t2 = new ToDo(2,"clean the code",true);
        todoRepository.save(t2);
     // when
        ToDo t3 = todoRepository.findById(new Long(t2.getId())).get();
        // then
        assertEquals("clean the code", t3.getText());
        assertThat(t3.getId(),equalTo(t2.getId()));
        assertThat(t3.getId(),is(2L));
    }

	@Test
	public void givenTodo_whenUpdate_thengetOk(){
		//given
		ToDo todo=todoRepository.findById(1L).get();
		
		todo.setText("new text");
		//when 
		todoRepository.save(todo);
		//then
		assertEquals(todoRepository.findById(1L).get().getText(), "new text");
	}

	@Test
	public void givenTodo_whenDelete_getNull() {
		//given
		ToDo todo=todoRepository.findById(1L).get();	
		//when 
		todoRepository.delete(todo);
		//then
		assertThat(todoRepository.findOneByText(todo.getText()),is(nullValue()));
	
	}
}
