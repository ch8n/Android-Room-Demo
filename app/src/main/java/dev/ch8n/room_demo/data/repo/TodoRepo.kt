package dev.ch8n.room_demo.data.repo

import android.app.Application
import android.util.Log
import androidx.lifecycle.toLiveData
import dev.ch8n.room_demo.data.dao.TaskDao
import dev.ch8n.room_demo.data.db.TodoDb
import dev.ch8n.room_demo.data.entity.Task
import io.reactivex.schedulers.Schedulers

class TodoRepo(application: Application) {

    private val taskDao: TaskDao

    init {
        TodoDb.getInstance(application).let {
            requireNotNull(it)
            taskDao = it.taskDao()
        }
    }

    fun alltask() = taskDao.getAllTask()
        .subscribeOn(Schedulers.io())
        .doOnError { Log.e("all-note", it.localizedMessage) }
        .toLiveData()

    fun addTask(task: Task) = taskDao.addTask(task)
        .subscribeOn(Schedulers.io())
        .doOnError { Log.e("add-note", it.localizedMessage) }
        .toSingleDefault(true)
        .onErrorReturnItem(false)
        .toFlowable()
        .toLiveData()

    fun deleteTask(task: Task) = taskDao.deleteTask(task)
        .subscribeOn(Schedulers.io())
        .doOnError { Log.e("delete-note", it.localizedMessage) }
        .toSingleDefault(true)
        .onErrorReturnItem(false)
        .toFlowable()
        .toLiveData()


}