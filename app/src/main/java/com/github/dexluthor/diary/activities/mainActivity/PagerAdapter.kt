package com.github.dexluthor.diary.activities.mainActivity

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.github.dexluthor.diary.fragment.HomeworkFragment
import com.github.dexluthor.diary.fragment.SubjectsFragment

class PagerAdapter(private val fm: FragmentManager, private val ct: Context) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int) = when (position) {
        0 -> SubjectsFragment.newInstance(ct)
        1 -> HomeworkFragment.newInstance(ct)
        else -> throw RuntimeException()
    }

    override fun getCount() = 2
}