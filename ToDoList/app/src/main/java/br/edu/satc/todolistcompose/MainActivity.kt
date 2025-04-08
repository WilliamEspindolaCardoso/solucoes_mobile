package br.edu.satc.todolistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "todolist-db"
        )
            .allowMainThreadQueries()
            .build()

        val taskDao = db.taskDao()
        var tasks by mutableStateOf(taskDao.getAll())

        setContent {
            TaskApp(tasks) { newTask ->
                taskDao.insert(newTask)
                tasks = taskDao.getAll()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TaskAppPreview() {
    val sampleTasks = listOf(
        TaskData(1, title = "Estudar Android", description = "Aprender sobre o programa"),
        TaskData(2, title = "Fazer compras", description = "Arroz, Feijão e Carne")
    )
    TaskApp(sampleTasks, {})
}