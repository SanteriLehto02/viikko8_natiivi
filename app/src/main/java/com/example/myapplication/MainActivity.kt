package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.viikko8natiivi.viewmodel.Todo
import com.example.viikko8natiivi.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TodoScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Composable
fun TodoScreen(todoViewModel: TodoViewModel = viewModel(), modifier: Modifier) {
    val todos by todoViewModel.todos.observeAsState(emptyList()) // Observe LiveData

    TodoList(todos)
}

@Composable
fun TodoList(todos: List<Todo>) {
    if (todos.isEmpty()) {
        Text("No todos available", modifier = Modifier.padding(16.dp))
    } else {
        LazyColumn(
            modifier = Modifier.padding(60.dp)
        ) {
            items(todos) { todo ->
                Text(
                    text = todo.title,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )
                Divider(color = Color.LightGray, thickness = 1.dp)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}