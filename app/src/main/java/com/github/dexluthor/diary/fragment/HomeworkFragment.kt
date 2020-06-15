package com.github.dexluthor.diary.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.dexluthor.diary.R
import com.github.dexluthor.diary.activities.mainActivity.HomeworkAdapter
import com.github.dexluthor.diary.viewModel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_homework.*

class HomeworkFragment(ct: Context) : Fragment() {
    private val homeworkAdapter = HomeworkAdapter(ct)
    private val homeworkViewModel = ViewModelFactory.getHomeworkViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_homework, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeworkRecyclerView.layoutManager = LinearLayoutManager(context)
        homeworkRecyclerView.adapter = homeworkAdapter

        initObservers()

        val swipeCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(holder: RecyclerView.ViewHolder, direction: Int) {
                val removedHomework = homeworkViewModel.removeHomeworkAt(holder.adapterPosition)
                Snackbar
                    .make(view, "Homework was successfully removed", Snackbar.LENGTH_LONG)
                    .setAction("UNDO") {
                        homeworkViewModel.addHomework(removedHomework)
                    }.show()
            }
        }
        ItemTouchHelper(swipeCallback).attachToRecyclerView(homeworkRecyclerView)
    }

    private fun initObservers() {
        homeworkViewModel.getHomework().observe(viewLifecycleOwner, Observer { homework ->
            homeworkAdapter.submitList(ArrayList(homework))
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(ct: Context) = HomeworkFragment(ct)
    }

}