/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hugomvera.TodoListAwesome

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {
    /**
     * Don't expose the mutable list of tasks from outside the ViewModel.
     * Instead define _tasks and tasks. _tasks is internal and mutable inside the ViewModel.
     * tasks is public and read-only.
     */
    private val _tasks = getTodoTasks().toMutableStateList()

    val visiblePermissionQueue = mutableListOf<String>()


    val tasks: List<TodoTask>
        get() = _tasks

    fun remove(item: TodoTask) {
        _tasks.remove(item)
    }

    //TODO add function that adds item
    fun add(item: TodoTask){
        //will probably will need to lmake a new
        _tasks.add(item)
    }

    fun changeTaskChecked(item: TodoTask, checked: Boolean) =
        tasks.find { it.id == item.id }?.let { task ->
            task.checked = checked
        }

    fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ){
        if(!isGranted){
            visiblePermissionQueue.add(0,permission)
        }
    }




}

private fun getTodoTasks() = List(0) { i -> TodoTask(i, "Task # $i") }

//TODO make a function that will print out the List with  the format off
//ID task.description  checked(True/False)
