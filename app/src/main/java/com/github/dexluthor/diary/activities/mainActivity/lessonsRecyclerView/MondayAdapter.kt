package com.github.dexluthor.diary.activities.mainActivity.lessonsRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.dexluthor.diary.R
import com.github.dexluthor.diary.entities.Lesson

class MondayAdapter : ListAdapter<Lesson, LessonViewHolder>(LessonDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LessonViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.lesson_recycler_view_row, parent, false)
    )


    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}