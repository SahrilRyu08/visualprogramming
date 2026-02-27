package com.example.tasky.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.visualprogramming.afl1.model.Task
import com.example.visualprogramming.afl1.viewmodel.TaskViewModel

/**
 * Screen utama aplikasi yang menampilkan daftar tugas dan tombol aksi.
 *
 * Composable ini bertindak sebagai "Screen Level Composable" yang menghubungkan
 * [TaskViewModel] dengan komponen UI. Ia meng-observe state dari ViewModel
 * dan meneruskan event (seperti klik tombol hapus) kembali ke ViewModel.
 *
 * @param viewModel Instance [TaskViewModel] yang digunakan untuk manajemen state.
 *                  Secara default menggunakan [viewModel()] factory.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun TaskScreen(viewModel: TaskViewModel = viewModel()) {
    // Mengambil state terbaru dari ViewModel secara reaktif
    val tasks by viewModel.tasks.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tasky — MVVM") },
                navigationIcon = {
                    IconButton(onClick = { /* No-op: No Navigation */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* No-op */ }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Opsi Lainnya"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Container Daftar Tugas (Card Putih Besar)
                // Menggunakan weight(1f, fill = false) agar Card menyesuaikan tinggi konten
                // tapi tidak melebihi ruang yang tersedia (scrollable jika panjang).
                if (tasks.isNotEmpty()) {
                    Card(
                        modifier = Modifier
                            .weight(1f, fill = false)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            // Tambahkan padding bawah agar item terakhir tidak tertutup tombol floating
                            contentPadding = PaddingValues(bottom = 80.dp)
                        ) {
                            itemsIndexed(items = tasks, key = { _, task -> task.id }) { index, task ->
                                TaskItem(
                                    task = task,
                                    onDelete = { viewModel.deleteTask(task) },
                                    onCheckedChange = { isChecked ->
                                        viewModel.onTaskCheckedChange(task, isChecked)
                                    }
                                )
                                // Tambahkan Divider jika bukan item terakhir
                                if (index < tasks.lastIndex) {
                                    HorizontalDivider(
                                        modifier = Modifier.padding(horizontal = 16.dp),
                                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                                    )
                                }
                            }
                        }
                    }
                } else {
                    // Empty state centered
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Tidak ada tugas",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Tombol Pulihkan Data Default (Floating di bawah)
            // Ditempatkan di luar Column agar tetap floating di atas konten
            Button(
                onClick = { viewModel.restoreDefault() },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
                    .height(56.dp), // Tinggi standar FAB/Pill button
                shape = RoundedCornerShape(50), // Pill shape
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Pulihkan Data Default",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

/**
 * Komponen UI yang merepresentasikan satu baris item tugas.
 *
 * @param task Data tugas yang akan ditampilkan.
 * @param onDelete Callback yang dipanggil saat tombol hapus ditekan.
 * @param onCheckedChange Callback yang dipanggil saat status checklist berubah.
 */
@Composable
fun TaskItem(
    task: Task,
    onDelete: () -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    // Tidak menggunakan Card individual lagi, melainkan Row langsung
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 8.dp), // Padding internal item
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Checkbox
        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primary,
                uncheckedColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )

        // Teks Tugas
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.titleMedium,
                color = if (task.isCompleted) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f) else MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = task.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Tombol Hapus
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Hapus Tugas ${task.title}",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
