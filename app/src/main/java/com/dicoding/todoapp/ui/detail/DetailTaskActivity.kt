package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        val title : TextView = findViewById(R.id.detail_ed_title)
        val description : TextView = findViewById(R.id.detail_ed_description)
        val dueDate : TextView = findViewById(R.id.detail_ed_due_date)
        val deleteBtn : Button = findViewById(R.id.btn_delete_task)
        val taskId = intent.getIntExtra(TASK_ID, 0)

        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailTaskViewModel::class.java]

        detailViewModel.setTaskId(taskId)

        detailViewModel.task.observe(this) {
            it?.let{
                title.text = it.title
                description.text = it.description
                dueDate.text = DateConverter.convertMillisToString(it.dueDateMillis)
            }
        }

        deleteBtn.setOnClickListener {
            detailViewModel.deleteTask()
            finish()
        }

    }
}