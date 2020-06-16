package com.github.dexluthor.diary.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.github.dexluthor.diary.R
import com.github.dexluthor.diary.entities.Lesson
import java.time.DayOfWeek

class LessonsViewModel : ViewModel() {
    private val lessonsMonday = MutableLiveData<List<Lesson>>(ArrayList())
    private val lessonsTuesday = MutableLiveData<List<Lesson>>(ArrayList())
    private val lessonsWednesday = MutableLiveData<List<Lesson>>(ArrayList())
    private val lessonsThursday = MutableLiveData<List<Lesson>>(ArrayList())
    private val lessonsFriday = MutableLiveData<List<Lesson>>(ArrayList())

    fun addLesson(lesson: Lesson) {
        val vm = when (lesson.dayOfWeek) {
            DayOfWeek.MONDAY -> lessonsMonday
            DayOfWeek.TUESDAY -> lessonsTuesday
            DayOfWeek.WEDNESDAY -> lessonsWednesday
            DayOfWeek.THURSDAY -> lessonsThursday
            else -> lessonsFriday
        }
        val arrayList = ArrayList(vm.value!!)
        arrayList.add(lesson)
        arrayList.sort()
        vm.postValue(arrayList)
    }

    @Throws(NumberFormatException::class)
    fun removeLessonAt(
        position: Int,
        recyclerView: RecyclerView
    ): Lesson {
        val vm = when (recyclerView.id) {
            R.id.mondayLessonsRecycler -> lessonsMonday
            R.id.tuesdayLessonsRecycler -> lessonsTuesday
            R.id.wednesdayLessonsRecycler -> lessonsWednesday
            R.id.thursdayLessonsRecycler -> lessonsThursday
            else -> lessonsFriday
        }
        val arrayList = ArrayList(vm.value!!)
        val removedLesson = arrayList.removeAt(position)
        vm.postValue(arrayList)
        return removedLesson
    }

    fun getMondayLessons() = lessonsMonday as LiveData<List<Lesson>>
    fun getTuesdayLessons() = lessonsTuesday as LiveData<List<Lesson>>
    fun getWednesdayLessons() = lessonsWednesday as LiveData<List<Lesson>>
    fun getThursdayLessons() = lessonsThursday as LiveData<List<Lesson>>
    fun getFridayLessons() = lessonsFriday as LiveData<List<Lesson>>

}