package com.github.dexluthor.diary.activities.mainActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.dexluthor.diary.R
import com.github.dexluthor.diary.entities.Homework
import com.github.dexluthor.diary.fragment.HomeworkInfoFragment

class HomeworkAdapter(private val supportedFragmentManager: FragmentManager) :
    ListAdapter<Homework, HomeworkTextViewHolder>(HomeworkDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeworkTextViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.homework_recycler_view_row, parent, false)
        )

    override fun onBindViewHolder(holder: HomeworkTextViewHolder, position: Int) {
        holder.bind(getItem(position), supportedFragmentManager)
    }
}

class HomeworkTextViewHolder(layout: View) : RecyclerView.ViewHolder(layout) {
    private val textView: TextView = layout.findViewById(R.id.text)
    private val checkBox: CheckBox = layout.findViewById(R.id.checkBox)

    fun bind(homework: Homework, supportedFragmentManager: FragmentManager) {
        textView.text = homework.subjectName
        textView.setOnLongClickListener {
            supportedFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_homework, HomeworkInfoFragment())
                .commit() > 0
        }

        checkBox.setOnClickListener {
            homework.status = checkBox.isSelected
        }
    }
}

class HomeworkDiff : DiffUtil.ItemCallback<Homework>() {
    override fun areItemsTheSame(oldItem: Homework, newItem: Homework) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Homework, newItem: Homework) =
        areItemsTheSame(oldItem, newItem)
}