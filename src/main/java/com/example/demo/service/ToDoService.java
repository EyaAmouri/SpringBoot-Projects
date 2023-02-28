package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ToDo;

public interface ToDoService {
	public List<ToDo> getAllToDo();
	public ToDo getToDoById(long id);
	public ToDo saveToDo(ToDo todo);
	public void removeToDo(ToDo todo);
	public void deleteById(long l);
}

