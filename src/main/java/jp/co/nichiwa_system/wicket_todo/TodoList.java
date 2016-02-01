/*
 * Copyright 2015 okumura.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.nichiwa_system.wicket_todo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author okumura
 */
class TodoList implements Serializable {

	private final List<Todo> list = new ArrayList<>();
    
    // FIXME!! TodoListにこれがあるのはおかしい。新しいTodoを一個持ってる？
	private String description = "";
	private Date due;
	
	public TodoList() {
	}

	void newTask() {
		list.add(new Todo(description, due));
		description = "";
		due = null;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDue() {
		return due;
	}

	public void setDue(Date due) {
		this.due = due;
	}
	
	public List<Todo> getList() {
		return Collections.unmodifiableList(list);
	}
}

/**
 *
 * @author okumura
 */
class Todo implements Serializable {
	private boolean done;
	private String description;
	private Date due;
	
	public Todo(String description, Date due) {
		this.description = description;
		this.due = due;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDue() {
		return due;
	}

	public void setDue(Date due) {
		this.due = due;
	}
}
