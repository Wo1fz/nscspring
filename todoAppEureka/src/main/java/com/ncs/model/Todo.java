package com.ncs.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Todo {
	@Id
	@GeneratedValue
	private int id;

	@NotNull(message = "User cannot be null!")
	@NotEmpty(message = "User cannot be empty!")
	private String user;

	@Size(min = 5, max = 250, message = "Description must be between 5 to 250 character!")
	private String description;
	
	@Future
	private LocalDate targetDate;
	private boolean done;

	public Todo() {
		super();
	}

	public Todo(int id, String user, String description, LocalDate targetDate, boolean done) {
		super();
		this.id = id;
		this.user = user;
		this.description = description;
		this.targetDate = targetDate;
		this.done = done;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
}
