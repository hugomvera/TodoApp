package com.hugomvera.TodoListAwesome.DataBase

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.room.Entity
import androidx.room.PrimaryKey


//might need to put in false for the auto generate
//might need to playearound with the checked paramenter
@Entity
data class Todo (
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var label: String,
    var checked: Boolean
    )
//    initialChecked: Boolean = false
//    ) {
//        var checked: Boolean by mutableStateOf(initialChecked)
//    }
