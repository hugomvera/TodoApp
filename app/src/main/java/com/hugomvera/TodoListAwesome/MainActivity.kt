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

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.hugomvera.TodoListAwesome.DataBase.TodoDatabase
import com.hugomvera.TodoListAwesome.ui.theme.BasicStateCodelabTheme
import java.io.BufferedReader
import java.io.File

class MainActivity : ComponentActivity() {

    //lets call the viewmodle here and pass the it to composable arguments
    private val todoViewModel by  viewModels<TodoViewModel> ()


    //creating an instance of the doa


    companion object {
        lateinit var todoDatabase: TodoDatabase
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        todoDatabase = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            "Todo_DB"
        ).build()



        //TODO want to see if i can read teh fiel

//        val bufferedReader: BufferedReader = File("somefile.txt").bufferedReader()
//        val inputString = bufferedReader.use { it.readText() }
//        Log.d("Destroy",inputString)



        super.onCreate(savedInstanceState)



        setContent {
            BasicStateCodelabTheme {


//                val writinPermissionResultLauncher = rememberLauncherForActivityResult(
//                    contract = ActivityResultContracts.RequestPermission(),
//                    onResult = { isGranted ->
//                        todoViewModel.onPermissionResult(
//                            permission =  Manifest.permission.READ_EXTERNAL_STORAGE,
//                            isGranted =  isGranted)
//                    }
//                )
//
//
//               // writinPermissionResultLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
//
//
//                val requestPermissionLauncher = registerForActivityResult(
//                    ActivityResultContracts.RequestPermission()
//                )
//                { isGranted: Boolean ->
//                    if(isGranted){Log.i("Permission","Granted")}
//                    else{
//                        Log.i("Permission","Denied")
//                        //TODO guess could ask for permisison here
//                      //  writinPermissionResultLauncher.launch(android.Manifest.permission.CAMERA)
//                    }}








                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TodoScreen(modifier = Modifier,todoViewModel)
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        val text = "Hello toast!"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(this, text, duration) // in Activity
        toast.show()

        println("application closed")
        Log.d("Destroy","onDestroy is being execurted")

        //either get function on the view model or use the value task
        Log.d("Destroy","going to print here")
        val listIterator = todoViewModel.tasks.listIterator()
//        while (listIterator.hasNext())
//        {
//            listIterator.toString()
//          //og.d("Destroy",listIterator.toString())
//        }

        Log.d("Destroy", todoViewModel.tasks.get(0).id.toString())

        var i =0;
        todoViewModel.tasks.listIterator().forEach {

            Log.d("Destroy", todoViewModel.tasks.get(i).id.toString())
            Log.d("Destroy", todoViewModel.tasks.get(i).label.toString())
            Log.d("Destroy", todoViewModel.tasks.get(i).checked.toString())
            i++
        }


        //will try to write to a File here
        File("somefile.txt").printWriter().use { out ->
            todoViewModel.tasks.listIterator().forEach {
                out.println("${it.id}, ${it.label},${it.checked}")
            }
        }




    }
}

