package com.github.dexluthor.diary.activities.mainActivity

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.github.dexluthor.diary.fragment.HomeworkFragment
import com.github.dexluthor.diary.fragment.SubjectsFragment

class PagerAdapter(private val fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int) =
        when (position) {
            0 -> SubjectsFragment.newInstance(fm)
            1 -> HomeworkFragment.newInstance(fm)
            else -> throw RuntimeException()
        }


    override fun getCount() = 2
}