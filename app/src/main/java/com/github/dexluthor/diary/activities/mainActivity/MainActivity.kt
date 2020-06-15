package com.github.dexluthor.diary.activities.mainActivity

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.github.dexluthor.diary.R
import com.github.dexluthor.diary.entities.Lesson
import com.github.dexluthor.diary.fragment.ChooseWhatToCreateDialogFragment
import com.github.dexluthor.diary.viewModel.ViewModelFactory
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.time.DayOfWeek.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private val lessonsAdapter = LessonsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // lessonsRecycler.layoutManager = LinearLayoutManager(this)
        // lessonsRecycler.adapter = lessonsAdapter

        initObservers()
        initPageAdapter()
    }

    private fun initObservers() {
        val linearLayoutViewModel = ViewModelFactory.getLinearLayoutViewModel(this)
        linearLayoutViewModel.getLinearLayouts().observe(this, Observer { layouts ->
            lessonsAdapter.submitList(layouts)
        })

        val lessonViewModel = ViewModelFactory.getLessonsViewModel()
        lessonViewModel.getLessons().observe(this, Observer { lessons ->
            clearFromExistingData()
            createLessonTextViews(lessons)
        })
    }

    private fun initPageAdapter() {
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

    private fun clearFromExistingData() {
        val linearLayoutViewModel = ViewModelFactory.getLinearLayoutViewModel(this)

        val iterator = linearLayoutViewModel.getLinearLayouts().value!!.iterator()
        while (iterator.hasNext()) {
            iterator.next().removeAllViews()
        }
    }

    private fun createLessonTextViews(lessons: List<Lesson>) {
        for (lesson in lessons) {
            val day = when (lesson.dayOfWeek) {
                MONDAY -> 0
                TUESDAY -> 1
                WEDNESDAY -> 2
                THURSDAY -> 3
                FRIDAY -> 4
                SATURDAY -> 5
                else -> null//TODO()
            }
            val layout = lessonsRecycler.layoutManager!!.getChildAt(day!!) as LinearLayout
//            layout.addView(LessonTextView(layout.context, lesson))
            val textView = TextView(layout.context)
            textView.text = "Pool"
            layout.addView(textView)
        }
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
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val cnf = Configuration()
        //https://stackoverflow.com/questions/12908289/how-to-change-language-of-app-when-user-selects-language
        cnf.setLocale(locale)
        baseContext.resources.updateConfiguration(cnf, baseContext.resources.displayMetrics)

        val refresh = Intent(this, MainActivity::class.java)
        finish()
        startActivity(refresh)
    }

    fun onFloatingActionButtonClick(view: View) {
        val chooseWhatToCreateDialogFragment = ChooseWhatToCreateDialogFragment()
        chooseWhatToCreateDialogFragment.show(supportFragmentManager, "Tag")
    }

}
