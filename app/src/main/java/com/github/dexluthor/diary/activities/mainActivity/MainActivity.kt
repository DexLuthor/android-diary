package com.github.dexluthor.diary.activities.mainActivity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.dexluthor.diary.R
import com.github.dexluthor.diary.activities.mainActivity.lessonsRecyclerView.*
import com.github.dexluthor.diary.entities.Lesson
import com.github.dexluthor.diary.fragment.ChooseWhatToCreateDialogFragment
import com.github.dexluthor.diary.viewModel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val mondayAdapter = MondayAdapter()
    private val tuesdayAdapter = TuesdayAdapter()
    private val wednesdayAdapter = WednesdayAdapter()
    private val thursdayAdapter = ThursdayAdapter()
    private val fridayAdapter = FridayAdapter()
    private val lessonsViewModel = ViewModelFactory.getLessonsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPagerAdapter()
        initLessonRecyclers()
        initObservers()
    }

    private fun initPagerAdapter() {
        viewPager.adapter = PagerAdapter(supportFragmentManager, this)
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabBar))
        tabBar.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun initLessonRecyclers() {
        val recyclers = listOf(
            mondayLessonsRecycler,
            tuesdayLessonsRecycler,
            wednesdayLessonsRecycler,
            thursdayLessonsRecycler,
            fridayLessonsRecycler
        )
        val adapters =
            listOf(mondayAdapter, tuesdayAdapter, wednesdayAdapter, thursdayAdapter, fridayAdapter)

        for (i in recyclers.indices) {
            initRecycler(recyclers[i], adapters[i])
        }

//        mondayLessonsRecycler.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        mondayLessonsRecycler.adapter = mondayAdapter
//
//        tuesdayLessonsRecycler.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        tuesdayLessonsRecycler.adapter = tuesdayAdapter
//
//        wednesdayLessonsRecycler.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        wednesdayLessonsRecycler.adapter = wednesdayAdapter
//
//        thursdayLessonsRecycler.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        thursdayLessonsRecycler.adapter = thursdayAdapter
//
//        fridayLessonsRecycler.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        fridayLessonsRecycler.adapter = fridayAdapter
//
//        setSwipeCallback(mondayLessonsRecycler)
//        setSwipeCallback(tuesdayLessonsRecycler)
//        setSwipeCallback(wednesdayLessonsRecycler)
//        setSwipeCallback(thursdayLessonsRecycler)
//        setSwipeCallback(fridayLessonsRecycler)
    }

    //TODO check
    private fun initRecycler(
        recyclerView: RecyclerView,
        adapter: ListAdapter<Lesson, LessonViewHolder>
    ) {
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
        setSwipeCallback(recyclerView)
    }

    private fun setSwipeCallback(recyclerView: RecyclerView) {
        val swipeCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.DOWN) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(holder: RecyclerView.ViewHolder, direction: Int) {
                val removedLesson =
                    lessonsViewModel.removeLessonAt(holder.adapterPosition, recyclerView)
                Snackbar.make(
                    recyclerView,
                    resources.getString(R.string.lesson_was_removed),
                    Snackbar.LENGTH_LONG
                ).setAction(resources.getString(R.string.undo)) {
                    lessonsViewModel.addLesson(removedLesson)
                }.show()
            }
        }
        ItemTouchHelper(swipeCallback).attachToRecyclerView(recyclerView)
    }

    private fun initObservers() {
        //val lessonViewModel = lessonsViewModel
        val vms = listOf(
            lessonsViewModel.getMondayLessons(),
            lessonsViewModel.getTuesdayLessons(),
            lessonsViewModel.getWednesdayLessons(),
            lessonsViewModel.getThursdayLessons(),
            lessonsViewModel.getFridayLessons()
        )
        val adapters =
            listOf(mondayAdapter, tuesdayAdapter, wednesdayAdapter, thursdayAdapter, fridayAdapter)

        for (i in vms.indices) {
            initObserver(vms[i], adapters[i])
        }

//        lessonsViewModel.getMondayLessons().observe(this, Observer { lessons ->
//            mondayAdapter.submitList(lessons)
//        })
//        lessonsViewModel.getTuesdayLessons().observe(this, Observer { lessons ->
//            tuesdayAdapter.submitList(lessons)
//        })
//        lessonViewModel.getWednesdayLessons().observe(this, Observer { lessons ->
//            wednesdayAdapter.submitList(lessons)
//        })
//        lessonViewModel.getThursdayLessons().observe(this, Observer { lessons ->
//            thursdayAdapter.submitList(lessons)
//        })
//        lessonViewModel.getFridayLessons().observe(this, Observer { lessons ->
//            fridayAdapter.submitList(lessons)
//        })
    }

    //TODO check
    private fun initObserver(
        liveData: LiveData<List<Lesson>>,
        adapter: ListAdapter<Lesson, LessonViewHolder>
    ) {
        liveData.observe(this, Observer { lessons ->
            adapter.submitList(lessons)
        })
    }

    fun onFloatingActionButtonClick(view: View) {
        ChooseWhatToCreateDialogFragment.newInstance().show(supportFragmentManager, "Tag")
    }

}
