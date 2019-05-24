package dev.ch8n.room_demo.data.dao

import androidx.room.*
import dev.ch8n.room_demo.data.entity.Task
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface TaskDao {


    /** CREATE **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTask(task: Task): Completable

    /** READ **/
    @Query("SELECT * FROM task WHERE task.id = :taskId")
    fun getTask(taskId: Long): Single<Task>

    @Query("SELECT * FROM task ORDER BY priority DESC")
    fun getAllTask(): Flowable<List<Task>>

    /** UPDATE **/
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(task: Task): Completable


    /** DELETE **/
    @Delete
    fun deleteTask(task: Task): Completable

    @Query("DELETE FROM task WHERE task.id = :taskId")
    fun deleteTask(taskId: Long): Completable

    @Query("DELETE FROM task")
    fun deleteAllTask(): Completable

    @Query("DELETE FROM task WHERE task.priority = :priority")
    fun deleteAllTask(priority: Int): Completable

}