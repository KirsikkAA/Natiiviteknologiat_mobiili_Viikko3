package com.example.viikko3.viewModel

import androidx.lifecycle.ViewModel
import com.example.viikko3.model.mockList
import kotlin.collections.plus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.viikko3.model.Task
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(mockList)
    val tasks: StateFlow<List<Task>> = _tasks

    fun addTask(title: String) {
        val newId = (_tasks.value.maxOfOrNull { it.id } ?: 0) + 1
        val currentDate =
            SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())

        val newTask = Task(
            id = newId,
            title = title,
            description = "",
            priority = 1,
            dueDate = currentDate,
            done = false
        )
        _tasks.value = _tasks.value + newTask
    }


    fun toggleDone(id: Int) {
        _tasks.value = _tasks.value.map {
            if (it.id == id) it.copy(done = !it.done) else it
        }
    }

    fun filterByDone(done: Boolean) {
        _tasks.value = _tasks.value.filter { it.done == done }
    }

    fun sortDueDate() {
        _tasks.value = _tasks.value.sortedBy { it.dueDate }
    }

    fun removeTask(id: Int) {
        _tasks.value = _tasks.value.filter { it.id != id }
    }

    fun reset() {
        _tasks.value = mockList
    }

    fun updateTask(updated: Task) {
        _tasks.value = _tasks.value.map {
            if (it.id == updated.id) updated else it
        }
    }
}