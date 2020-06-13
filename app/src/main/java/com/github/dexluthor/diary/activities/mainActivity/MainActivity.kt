package com.github.dexluthor.diary.activities.mainActivity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.github.dexluthor.diary.R
import com.github.dexluthor.diary.activities.AddHomeworkActivity
import com.github.dexluthor.diary.activities.AddLessonActivity
import com.github.dexluthor.diary.activities.AddSubjectActivity
import com.github.dexluthor.diary.customWidgets.LessonTextView
import com.github.dexluthor.diary.entities.Lesson
import com.github.dexluthor.diary.viewModel.ViewModelFactory
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.time.DayOfWeek

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObservers()
        initPageAdapter()
    }

    private fun initObservers() {
        val lessonViewModel = ViewModelFactory.getLessonsViewModel()
        lessonViewModel.getLessons().observe(this, Observer { lessons ->
            createLessonTextViews(lessons)
        })
    }

    private fun initPageAdapter() {
        viewPager.adapter = PagerAdapter(supportFragmentManager)
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabBar))
        tabBar.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun createLessonTextViews(lessons: List<Lesson>) {
        for (lesson in lessons) {
            val layout = when (lesson.dayOfWeek) {
                DayOfWeek.MONDAY -> monday
                DayOfWeek.TUESDAY -> tuesday
                DayOfWeek.WEDNESDAY -> wednesday
                DayOfWeek.THURSDAY -> thursday
                DayOfWeek.FRIDAY -> friday
                DayOfWeek.SATURDAY -> saturday
                else -> null//TODO()
            }
            layout!!.addView(LessonTextView(this, lesson))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.upper_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addSubject -> startActivity(Intent(this, AddSubjectActivity::class.java))
            R.id.addLesson -> startActivity(Intent(this, AddLessonActivity::class.java))
            R.id.addHomework -> startActivity(Intent(this, AddHomeworkActivity::class.java))
        }
        return true
    }

    fun onFloatingActionButtonClick(view: View) {
        // TODO
    }

}
