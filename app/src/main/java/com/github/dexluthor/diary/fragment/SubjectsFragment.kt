package com.github.dexluthor.diary.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.dexluthor.diary.R
import com.github.dexluthor.diary.activities.mainActivity.SubjectAdapter
import com.github.dexluthor.diary.viewModel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class SubjectsFragment(ct: Context) : Fragment() {
    private val subjectAdapter = SubjectAdapter(ct)
    private val subjectViewModel = ViewModelFactory.getSubjectViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_subjects, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val subjectRecyclerView = view.findViewById<RecyclerView>(R.id.subjectRecyclerView)
        subjectRecyclerView.layoutManager = LinearLayoutManager(context)
        subjectRecyclerView.adapter = subjectAdapter

        initObservers()

        val swipeCallback = object : SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(holder: RecyclerView.ViewHolder, direction: Int) {
                val removedSubject = subjectViewModel.removeSubjectAt(holder.adapterPosition)
                Snackbar
                    .make(
                        view,
                        resources.getString(R.string.subject_was_removed),
                        Snackbar.LENGTH_LONG
                    )
                    .setAction(resources.getString(R.string.undo)) {
                        subjectViewModel.addSubject(removedSubject)
                    }.show()
            }
        }
        ItemTouchHelper(swipeCallback).attachToRecyclerView(subjectRecyclerView)
    }

    private fun initObservers() {
        subjectViewModel.getSubjects().observe(viewLifecycleOwner, Observer { subjects ->
            subjectAdapter.submitList(ArrayList(subjects))
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(ct: Context) = SubjectsFragment(ct)
    }
}