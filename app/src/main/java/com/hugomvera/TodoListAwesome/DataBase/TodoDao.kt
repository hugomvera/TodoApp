package com.hugomvera.TodoListAwesome.DataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface TodoDao {

    @Query("SELECT * FROM TODO")
    fun  getAllTodo() : LiveData<List<Todo>>

    @Insert
    fun addTodo(todo: Todo)

    @Query("DELETE FROM TODO where id =  :id")
    fun deleteTodo(id: Int)

}