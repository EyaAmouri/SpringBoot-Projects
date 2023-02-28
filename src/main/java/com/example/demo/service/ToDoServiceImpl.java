package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.ToDoRepository;
import com.example.demo.entity.ToDo;

@Service("toDoService")
public class ToDoServiceImpl implements ToDoService {
	@Autowired
	private ToDoRepository toDoRepository;
	public List<ToDo> getAllToDo() {
		
		return toDoRepository.findAll();
	}
	public ToDo getToDoById(long id) {
		
		
		return toDoRepository.findById(id).get();
	}
	public ToDo saveToDo(ToDo todo) {
		
		return toDoRepository.save(todo);
	}
	public void removeToDo(ToDo todo) {
		toDoRepository.delete(todo);
		//toDoRepository.delete(todo);
	}
	@Override
	public void deleteById(long l) {
		toDoRepository.deleteById(l);
		
	}

}
