package com.example.mycalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var num1 by remember { mutableStateOf("0") }
            var num2 by remember { mutableStateOf("0") }
            var result by remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 48.dp, start = 16.dp, end = 16.dp)
            ) {
                TextField(
                    value = num1,
                    onValueChange = { num1 = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = num2,
                    onValueChange = { num2 = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = result,
                    onValueChange = {},
                    label = { Text("Result") },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Button(onClick = {
                        result = (num1.toInt() + num2.toInt()).toString()
                    }) {
                        Text(text = "Add")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        result = (num1.toInt() - num2.toInt()).toString()
                    }) {
                        Text(text = "Sub")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        result = (num1.toInt() * num2.toInt()).toString()
                    }) {
                        Text(text = "Mul")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        result = (num1.toInt() / num2.toInt()).toString()
                    }) {
                        Text(text = "Div")
                    }
                }
            }
        }
    }
}