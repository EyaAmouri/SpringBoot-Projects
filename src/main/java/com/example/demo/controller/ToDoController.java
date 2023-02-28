package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ToDoService;
import com.example.demo.entity.ToDo;
import com.example.demo.ToDoException;
import org.springframework.web.client.RestTemplate;

import static javafx.beans.binding.Bindings.when;

@RestController
public class ToDoController {

	private static final Logger logger = LoggerFactory.getLogger(ToDoController.class);

	@Autowired
	private ToDoService toDoService;
	
	@GetMapping(value="/todo")
	public List <ToDo> getAllToDo(){
    	logger.info("Returning all the ToDo´s");
		return toDoService.getAllToDo();
	}
	
	@GetMapping(value="/")
	public String hello(){
    	
		return "Hello World";
	}
	@GetMapping(value = "/todo/{id}")
	public ToDo getToDoById(@PathVariable("id") long id) throws ToDoException{
    	logger.info("ToDo id to return " + id);
    	ToDo toDo = toDoService.getToDoById(id);

    	if (toDo == null || toDo.getId() <= 0){
            throw new ToDoException("ToDo doesn't exist");
    	}
	return toDoService.getToDoById(id);
    }


}
