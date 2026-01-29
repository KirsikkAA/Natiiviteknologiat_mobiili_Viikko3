package com.example.viikko3.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikko3.viewModel.TaskViewModel
import androidx.compose.material3.Card
import androidx.compose.foundation.lazy.items
import com.example.viikko3.model.Task


@Composable
fun HomeScreen(taskViewModel: TaskViewModel = viewModel()) {

    val tasks by taskViewModel.tasks.collectAsState()
    var selectedTask by remember { mutableStateOf<Task?>(null) }
    var newTaskTitle by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {

        Spacer(modifier = Modifier.height(height = 24.dp))

        Text(
            text = "Task List",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(height = 8.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(tasks) { task ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    onClick = { selectedTask = task }
                ) {
                    TaskRow(
                        task = task,
                        onToggle = { taskViewModel.toggleDone(task.id) },
                        onDelete = { taskViewModel.removeTask(task.id) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(height = 16.dp))

        OutlinedTextField(
            value = newTaskTitle,
            onValueChange = { newTaskTitle = it },
            label = { Text("New task") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(height = 8.dp))

        Button(
            onClick = {
                if (newTaskTitle.isNotEmpty()) {
                    taskViewModel.addTask(newTaskTitle)
                    newTaskTitle = ""
                }
            },
            modifier = Modifier.fillMaxWidth()

        ) {
            Text(text = "Add Task")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                taskViewModel.sortDueDate()
            }) {
                Text("Sort by date")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                taskViewModel.filterByDone(true)
            }) {
                Text("Show done")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                taskViewModel.reset()
            }) {
                Text("Reset")
            }
        }

        selectedTask?.let {
            TaskDetailDialog(
                task = it,
                onClose = { selectedTask = null },
                onUpdate = { updated ->
                    taskViewModel.updateTask(updated)
                    selectedTask = null
                }
            )
        }
    }
}



@Composable
fun TaskRow(
    task: Task,
    onToggle: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = task.done,
                onCheckedChange = { onToggle() }
            )
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(text = task.title)
                Text("Due: ${task.dueDate}")
            }
        }
        Button(
            onClick = onDelete) {
            Text("Delete")
        }
    }
}