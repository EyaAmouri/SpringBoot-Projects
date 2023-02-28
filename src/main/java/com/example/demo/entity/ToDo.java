package com.example.demo.entity;

import lombok.*;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToDo implements Serializable{
	
	@Id
	@GeneratedValue
	private long id;
	private String text;
	private boolean completed;


	
	
/**	public ToDo(long id, String text, boolean completed) {
		super();
		this.id = id;
		this.text = text;
		this.completed = completed;
	}**/


	public ToDo(String text, boolean completed) {
		super();
		this.text = text;
		this.completed = completed;
	}

	/** public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	} **/
	
}