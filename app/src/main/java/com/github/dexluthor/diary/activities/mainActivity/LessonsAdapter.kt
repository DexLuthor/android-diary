package com.github.dexluthor.diary.activities.mainActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.dexluthor.diary.R


class LessonsAdapter :
    ListAdapter<LinearLayout, LinearLayoutTextViewHolder>(LinearLayoutDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LinearLayoutTextViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.lesson_recycler_view_row, parent, false)
        )

    override fun onBindViewHolder(holder: LinearLayoutTextViewHolder, position: Int) {}
}

class LinearLayoutTextViewHolder(layout: View) : RecyclerView.ViewHolder(layout)

class LinearLayoutDiff : DiffUtil.ItemCallback<LinearLayout>() {
    override fun areItemsTheSame(oldItem: LinearLayout, newItem: LinearLayout) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: LinearLayout, newItem: LinearLayout) =
        areItemsTheSame(oldItem, newItem)
}