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
import java.util.List;

/**
 *
 * @author okumura
 */
class TodoList implements Serializable {

    private final List<Todo> list = new ArrayList<>();

    private Todo nextTask = new Todo();

    public TodoList() {
    }

    void addTask() {
        list.add(nextTask);
        nextTask = new Todo();
    }

    public List<Todo> getList() {
        return Collections.unmodifiableList(list);
    }

    /**
     * @return the nextTask
     */
    public Todo getNextTask() {
        return nextTask;
    }

    /**
     * @param nextTask the nextTask to set
     */
    public void setNextTask(Todo nextTask) {
        this.nextTask = nextTask;
    }
}
