/*
 * Copyright 2016 uokumura.
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
import java.text.MessageFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 *
 * @author okumura
 */
@Entity
public class Todo implements Serializable {

    private @Id @GeneratedValue Long id;
    private boolean done;
    private String description;
    private Date due;

    Todo() {
        this("", null);
    }

    public Todo(String description, Date due) {
        this.description = description;
        this.due = due;
    }

    public static Todo done(String description, Date due) {
        Todo todo = new Todo(description, due);
        todo.setDone(true);
        return todo;
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

    @Override
    public String toString() {
        return MessageFormat.format("{0}{1}({2,date})", done ? '☑' : '□', description, due);
    }
}
