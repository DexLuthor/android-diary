package com.github.dexluthor.diary.activities.mainActivity

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.dexluthor.diary.R
import com.github.dexluthor.diary.activities.mainActivity.lessonsRecyclerView.*
import com.github.dexluthor.diary.fragment.ChooseWhatToCreateDialogFragment
import com.github.dexluthor.diary.viewModel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


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
        mondayLessonsRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mondayLessonsRecycler.adapter = mondayAdapter

        tuesdayLessonsRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        tuesdayLessonsRecycler.adapter = tuesdayAdapter

        wednesdayLessonsRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        wednesdayLessonsRecycler.adapter = wednesdayAdapter

        thursdayLessonsRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        thursdayLessonsRecycler.adapter = thursdayAdapter

        fridayLessonsRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        fridayLessonsRecycler.adapter = fridayAdapter

        setSwipeCallback(mondayLessonsRecycler)
        setSwipeCallback(tuesdayLessonsRecycler)
        setSwipeCallback(wednesdayLessonsRecycler)
        setSwipeCallback(thursdayLessonsRecycler)
        setSwipeCallback(fridayLessonsRecycler)
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
                    "Lesson was successfully removed",
                    Snackbar.LENGTH_LONG
                ).setAction("UNDO") {
                    lessonsViewModel.addLesson(removedLesson)
                }.show()
            }
        }
        ItemTouchHelper(swipeCallback).attachToRecyclerView(recyclerView)
    }

    private fun initObservers() {
        val lessonViewModel = lessonsViewModel
        lessonViewModel.getMondayLessons().observe(this, Observer { lessons ->
            mondayAdapter.submitList(lessons)
        })
        lessonViewModel.getTuesdayLessons().observe(this, Observer { lessons ->
            tuesdayAdapter.submitList(lessons)
        })
        lessonViewModel.getWednesdayLessons().observe(this, Observer { lessons ->
            wednesdayAdapter.submitList(lessons)
        })
        lessonViewModel.getThursdayLessons().observe(this, Observer { lessons ->
            thursdayAdapter.submitList(lessons)
        })
        lessonViewModel.getFridayLessons().observe(this, Observer { lessons ->
            fridayAdapter.submitList(lessons)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.upper_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.En -> {
                setLocale("en")
                recreate()
            }
            R.id.Sk -> {
                setLocale("sk")
                recreate()
            }
            R.id.Ru -> {
                setLocale("ru")
                recreate()
            }
        }
        return true
    }

    private fun setLocale(lang: String) {
        //https://stackoverflow.com/questions/12908289/how-to-change-language-of-app-when-user-selects-language
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val cnf = Configuration()
        cnf.setLocale(locale)
        baseContext.resources.updateConfiguration(cnf, baseContext.resources.displayMetrics)

        val refresh = Intent(this, MainActivity::class.java)
        finish()
        startActivity(refresh)
    }

    fun onFloatingActionButtonClick(view: View) {
        ChooseWhatToCreateDialogFragment.newInstance().show(supportFragmentManager, "Tag")
    }

}
