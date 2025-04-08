package br.edu.satc.todolistcompose

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

@Entity
data class TaskData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val complete: Boolean = false
)

@Dao
interface TaskDao {
    @Query("SELECT * FROM taskdata")
    fun getAll(): List<TaskData>

    @Insert
    fun insert(task: TaskData)

    @Delete
    fun delete(task: TaskData)

    @Update
    fun update(task: TaskData)
}

@Database(entities = [TaskData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}