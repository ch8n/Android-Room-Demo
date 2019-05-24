package dev.ch8n.room_demo

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dev.ch8n.room_demo.data.entity.Task
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var taskViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskViewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)

        taskViewModel.getAllTask().observe(this, Observer {
            list_task.run {
                adapter = ArrayAdapter<Task>(this@MainActivity, android.R.layout.simple_list_item_1, it)
                setOnItemClickListener { parent, view, position, id ->
                    val task = it.get(position)
                    taskViewModel.deleteTask(task).observe(this@MainActivity, Observer {
                        if (it) {
                            Toast.makeText(this@MainActivity, "deleted", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@MainActivity, "failed", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }
        })


        btn_add_task.setOnClickListener {
            taskViewModel.addTask(
                Task(
                    title = "Pokemon",
                    description = "Gonna ketch'em all ;D",
                    priority = 1,
                    day = 1
                )
            ).observe(this, Observer {
                if (it) {
                    Toast.makeText(this@MainActivity, "added", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
