package com.github.dexluthor.diary.activities.mainActivity.lessonsRecyclerView

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.dexluthor.diary.R
import com.github.dexluthor.diary.activities.LessonInfoActivity
import com.github.dexluthor.diary.entities.Lesson

class LessonViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val textView: TextView = view.findViewById(R.id.text)

    fun bind(lesson: Lesson) {
        textView.text = lesson.subject.name
        textView.setOnLongClickListener {
            val intent = Intent(view.context, LessonInfoActivity::class.java)
            intent.putExtra("lesson", lesson)
            view.context.startActivity(intent)

            return@setOnLongClickListener true
        }
    }
}