package com.github.dexluthor.diary.fragment

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

class SubjectsFragment : Fragment() {

    private val subjectAdapter = SubjectAdapter()
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
                subjectViewModel.removeSubject(holder.adapterPosition)
            }
        }
        ItemTouchHelper(swipeCallback).attachToRecyclerView(subjectRecyclerView);
    }

    private fun initObservers() {
        subjectViewModel.getSubjects().observe(viewLifecycleOwner, Observer { subjects ->
            subjectAdapter.submitList(ArrayList(subjects))
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(fm: Any?) = SubjectsFragment()
    }
}