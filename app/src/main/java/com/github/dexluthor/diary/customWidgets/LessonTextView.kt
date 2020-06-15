package com.github.dexluthor.diary.customWidgets

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import com.github.dexluthor.diary.entities.Lesson

@SuppressLint("ViewConstructor")
class LessonTextView(context: Context, lesson: Lesson) :
    androidx.appcompat.widget.AppCompatTextView(context) {

    init {
        text = lesson.subject.name
        setBackgroundColor(Color.rgb(66, 3, 111))
        setTextColor(Color.WHITE)
        height = 80// TODO 50dp
        textSize = 20f
    }

}