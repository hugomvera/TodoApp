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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

var textGlobal = ""

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
     val wellnessViewModel: WellnessViewModel = viewModel()
    var count by rememberSaveable { mutableStateOf(30) }
    StatelessCounter(

        //TODO need to change this so the count is up to date
        count = 30,
        onIncrement = {


            val item = WellnessTask(count, textGlobal, false)
            wellnessViewModel.add( item)
            count++;
                      },
        modifier = modifier
    )
}

@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
           // Text("You've had $count glasses.")
        }

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
