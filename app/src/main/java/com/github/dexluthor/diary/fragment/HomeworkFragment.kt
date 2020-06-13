package com.github.dexluthor.diary.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.dexluthor.diary.R
import com.github.dexluthor.diary.activities.mainActivity.HomeworkAdapter
import com.github.dexluthor.diary.viewModel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_homework.*

class HomeworkFragment(supportedFragmentManager: FragmentManager) : Fragment() {

    private val homeworkAdapter = HomeworkAdapter(supportedFragmentManager)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_homework, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeworkRecyclerView.layoutManager = LinearLayoutManager(context)
        homeworkRecyclerView.adapter = homeworkAdapter

        initObservers()
    }

    private fun initObservers() {
        val homeworkViewModel = ViewModelFactory.getHomeworkViewModel()
        homeworkViewModel.getHomework().observe(viewLifecycleOwner, Observer { homework ->
            homeworkAdapter.submitList(ArrayList(homework))
        })
    }

    fun onCheckBoxClick(view: View) {
        Snackbar.make(view, "Good", Snackbar.LENGTH_SHORT)
    }

    companion object {
        @JvmStatic
        fun newInstance(fm: FragmentManager) = HomeworkFragment(fm)
    }

}