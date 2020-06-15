package com.github.dexluthor.diary.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.dexluthor.diary.R
import com.github.dexluthor.diary.entities.Homework
import kotlinx.android.synthetic.main.activity_homework_info.*

class HomeworkInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homework_info)

        fillFieldsFromExtras()
    }

    private fun fillFieldsFromExtras() {
        val homework = intent.extras!!.getSerializable("homework") as Homework

        subject_name.text = homework.subject.name
        description.text = homework.description
        dateTextView.text = homework.deadline.toLocalDate().toString()
        timeTextView.text = homework.deadline.toLocalTime().toString()
    }
}