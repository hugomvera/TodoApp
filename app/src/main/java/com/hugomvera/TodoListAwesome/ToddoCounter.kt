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

import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel

var textGlobal = ""

@Composable
fun StatefulCounter(
                     todoViewModel: TodoViewModel = viewModel(),
                     modifier: Modifier = Modifier
) {

    var count by rememberSaveable { mutableStateOf(32) }
    StatelessCounter(

        //TODO need to change this so the count is up to date
        count = 32,
        onIncrement = {


            val item = TodoTask(count, textGlobal, false)
            todoViewModel.add( item)
            count++;
                      },
        modifier = modifier
    )
}

@Composable
fun StatelessCounter(count: Int
                     ,onIncrement: () -> Unit, modifier: Modifier = Modifier
                    ,todoViewModel: TodoViewModel = viewModel()
) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
           // Text("You've had $count glasses.")
        }




//TODO MIGHT NOT NEED PERMISSIONS FOR ROOM DATABASE
//
//
//        val dialogQueue = todoViewModel.visiblePermissionQueue
//
//        val cameraPermissionResultLauncher = rememberLauncherForActivityResult(
//            contract = ActivityResultContracts.RequestPermission(),
//            onResult = { isGranted ->
//                todoViewModel.onPermissionResult(
//                    permission =  android.Manifest.permission.READ_EXTERNAL_STORAGE,
//                    isGranted =  isGranted)
//            }
//        )
//
//        Button(
//            onClick = {
//                cameraPermissionResultLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
//            }
//        ) {
//            Text(text = "Check and Request Permission")
//        }














//                val requestPermissionLauncher = registerForActivityResult(
//                    ActivityResultContracts.RequestPermission()
//                )
//                { isGranted: Boolean ->
//                    if(isGranted){Log.i("Permission","Granted")}
//                    else{
//                        Log.i("Permission","Denied")
//                       //TODO guess could ask for permisison here
//                      //  writinPermissionResultLauncher.launch(android.Manifest.permission.CAMERA)
//                    }}







        //can probably add button here to ask for permission read and write



        Row(modifier = modifier.padding(16.dp)) {
            Button(
                //TODO when click button it adds the text on tyhe bottom
                //TODO look at the mechaninsm that rmoves the item
             onClick = onIncrement,
               // enabled = count < 10,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Add one")
            }
            var text by remember { mutableStateOf("") }

            OutlinedTextField(
                value = text,
                onValueChange = { text = it
                    textGlobal = text
                                },
                label = { Text("Type in Todo...") }
            )



        }

        //todo add input field in compose
    }
}
