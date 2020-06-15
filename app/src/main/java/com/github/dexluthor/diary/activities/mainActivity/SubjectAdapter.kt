package com.github.dexluthor.diary.activities.mainActivity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.dexluthor.diary.R
import com.github.dexluthor.diary.activities.EditSubjectActivity
import com.github.dexluthor.diary.entities.Subject

class SubjectAdapter(private val ct: Context) :
    ListAdapter<Subject, SubjectTextViewHolder>(SubjectDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SubjectTextViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.subject_recycler_view_row, parent, false)
        )

    override fun onBindViewHolder(holderSubject: SubjectTextViewHolder, position: Int) {
        holderSubject.bind(getItem(position), ct)
    }
}

class SubjectTextViewHolder(layout: View) : RecyclerView.ViewHolder(layout) {
    private val textView: TextView = layout.findViewById(R.id.textView)

    fun bind(sbj: Subject, ct: Context) {
        textView.text = sbj.name

        textView.setOnLongClickListener {
            val intent = Intent(ct, EditSubjectActivity::class.java)
            intent.putExtra("subject", sbj)
            ct.startActivity(intent)
            return@setOnLongClickListener true
        }
    }
}

class SubjectDiff : DiffUtil.ItemCallback<Subject>() {
    override fun areItemsTheSame(oldItem: Subject, newItem: Subject) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Subject, newItem: Subject) =
        areItemsTheSame(oldItem, newItem)
}