package com.example.visualprogramming.afl1.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import com.example.visualprogramming.afl1.model.Task
import com.example.visualprogramming.afl1.repository.TaskRepository
import kotlinx.coroutines.flow.StateFlow

class TaskViewModel(private val repository: TaskRepository = TaskRepository()) : ViewModel() {

    val tasks: StateFlow<List<Task>> = repository.tasks

    fun deleteTask(task: Task) {
        repository.deleteTask(task)
    }

    fun onTaskCheckedChange(task: Task, isCompleted: Boolean) {
        repository.toggleTaskCompletion(task, isCompleted)
    }

    /**
     * Menangani aksi pemulihan data ke kondisi default.
     * Meneruskan permintaan ke repository untuk mereset data.
     */
    fun restoreDefault() {
        repository.restoreDefault()
    }
}