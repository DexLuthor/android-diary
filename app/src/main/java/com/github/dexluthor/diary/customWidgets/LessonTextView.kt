package com.github.dexluthor.diary.customWidgets

import android.content.Context
import android.graphics.Color
import com.github.dexluthor.diary.entities.Lesson

class LessonTextView(context: Context, lesson: Lesson) :
    androidx.appcompat.widget.AppCompatTextView(context) {

    init {
        text = lesson.subjectname
        setBackgroundColor(Color.RED)
        setTextColor(Color.WHITE)
        textSize = 20f
    }

}