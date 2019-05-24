package dev.ch8n.room_demo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dev.ch8n.room_demo.data.entity.Task
import dev.ch8n.room_demo.data.repo.TodoRepo

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val todoRepo = TodoRepo(getApplication())

    fun getAllTask() = todoRepo.alltask()

    fun addTask(task: Task) = todoRepo.addTask(task)

    fun deleteTask(task: Task) = todoRepo.deleteTask(task)

}
