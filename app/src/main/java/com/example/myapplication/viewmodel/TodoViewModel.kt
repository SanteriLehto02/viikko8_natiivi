package com.example.viikko8natiivi.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.viewmodel.TodosApi
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

public class TodoViewModel : ViewModel() {
    private val _todos = MutableLiveData<List<Todo>>(emptyList())
    val todos: LiveData<List<Todo>> get() = _todos

    init {
        getTodosList()
    }

    private fun getTodosList() {
        viewModelScope.launch {
            try {
                Log.d("TodoViewModel", "Fetching todos...")
                val todosApi = TodosApi.getInstance()

                val fetchedTodos = todosApi.getTodos()
                Log.d("TodoViewModel", "Fetched ${fetchedTodos.size} todos")
                _todos.value = fetchedTodos // Update LiveData
            } catch (e: Exception) {
                Log.d("TodoViewModel", "Error fetching todos: ${e.message}")
            }
        }
    }
}



