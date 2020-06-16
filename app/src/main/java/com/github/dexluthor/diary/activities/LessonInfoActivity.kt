package com.github.dexluthor.diary.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.dexluthor.diary.R
import com.github.dexluthor.diary.entities.Lesson
import kotlinx.android.synthetic.main.activity_lesson_info.*


class LessonInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_info)

        val lesson = intent.extras?.getSerializable("lesson") as Lesson
        subject_name.text = lesson.subject.name
        time.text = lesson.startTime.toString().substring(0, 5)
        location.text = lesson.location
        duration.text = lesson.durationMinutes.toString()
        type.text = lesson.type.toString()
    }

}
