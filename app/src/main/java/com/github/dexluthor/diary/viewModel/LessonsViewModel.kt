package com.github.dexluthor.diary.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.dexluthor.diary.entities.Lesson

class LessonsViewModel : ViewModel() {
    private val lessons = MutableLiveData<List<Lesson>>(ArrayList())

    fun addLesson(lesson: Lesson) {
        val arrayList = ArrayList(lessons.value!!)
        arrayList.add(lesson)
        lessons.value = arrayList
    }
    fun removeLesson(lesson: Lesson){
        val arrayList = ArrayList(lessons.value!!)
        arrayList.remove(lesson)
        lessons.postValue(arrayList)
    }

    fun getLessons() = lessons as LiveData<List<Lesson>>
}