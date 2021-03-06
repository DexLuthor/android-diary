package com.github.dexluthor.diary.activities.mainActivity.lessonsRecyclerView

import androidx.recyclerview.widget.DiffUtil
import com.github.dexluthor.diary.entities.Lesson

class LessonDiff : DiffUtil.ItemCallback<Lesson>() {
    override fun areItemsTheSame(oldItem: Lesson, newItem: Lesson) =
        oldItem == newItem


    override fun areContentsTheSame(oldItem: Lesson, newItem: Lesson) =
        areItemsTheSame(oldItem, newItem)

}