package com.example.visualprogramming.afl1.repository

import com.example.visualprogramming.afl1.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Repository yang bertannggung jawab sebagai single source of Truth(SSOT) untuk data tugas.
 *
 * class ini mengelola data secara in-memory dan menyyediakan operasi untuk memanipulasi data
 * tersebut (menghapus dan meulihkan ke default). Data di eskpose menggunkan [kotlinx.coroutines.flow.StateFlo]w
 * agar perubahan dapat dipbservasi secara reaktif oleh viewModel*/
class TaskRepository {

    /**
     * Daftar data dummy default yang akan digunakan saat aplikasi pertama kali dibuka
     * atau saat pengguna memilijhuntuk memulihkan data.
     * */
    private val defaultTask = listOf(
        Task(1, "Baca modul MVVM", "Ringkas konsep utama", false),
        Task(2, "Implementasi Compose", "Buat UI list + tombol", false),
        Task(3, "Pisahkan logika", "Repo dan ViewModel", false),
        Task(4, "Testing", "Cek fungsionalitas hapus & restore", false),
        Task(5, "Submission", "Upload tugas ke e-learning", false),
        Task(6, "Review Kode", "Pastikan tidak ada memory leak", false),
        Task(7, "Optimasi UI", "Cek performa rendering LazyColumn", false),
        Task(8, "Dokumentasi", "Tulis README yang jelas", false),
        Task(9, "Refactoring", "Bersihkan kode yang tidak perlu", false),
        Task(10, "Final Check", "Pastikan semua fitur berjalan", false),
        Task(11, "Deploy", "Build APK release", false),
        Task(12, "Minum Kopi", "Istirahat sejenak", false)
    )

    private val _tasks = MutableStateFlow(defaultTask)

    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow();

    fun deleteTask(task: Task) {
        val currentList = _tasks.value.toMutableList()
        currentList.remove(task)
        _tasks.value =currentList
    }

    fun toggleTaskCompletion(task: Task, isCompleted: Boolean) {
        val currentLits = _tasks.value.map {
            if (it.id == task.id) {
                it.copy(isCompleted = isCompleted)
            } else {
                it
            }
        }
        _tasks.value = currentLits
    }

    fun restoreDefault() {
        _tasks.value = defaultTask
    }
}