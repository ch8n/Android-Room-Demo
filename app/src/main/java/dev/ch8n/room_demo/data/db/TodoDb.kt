package dev.ch8n.room_demo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dev.ch8n.room_demo.data.dao.TaskDao
import dev.ch8n.room_demo.data.entity.Task

@Database(entities = [Task::class], version = 1)
abstract class TodoDb : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {

        private var instance: TodoDb? = null

        private val todoDBCallback: RoomDatabase.Callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)



            }
        }

        @Synchronized
        fun getInstance(context: Context): TodoDb? {
            if (instance == null) {
                instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        TodoDb::class.java,
                        "todo_db"
                    )
                    .addCallback(todoDBCallback)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}