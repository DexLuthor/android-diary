package com.github.dexluthor.diary.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.dexluthor.diary.entities.Homework

class HomeworkViewModel : ViewModel() {
    private val homeworkData = MutableLiveData<List<Homework>>(ArrayList())

    fun addHomework(homework: Homework): Boolean {
        if (homeworkData.value!!.contains(homework)) return false

        val arrayList = ArrayList(homeworkData.value!!)
        arrayList.add(homework)
        arrayList.sort()
        homeworkData.postValue(arrayList)
        return true
    }

    fun getHomework() = homeworkData as LiveData<List<Homework>>

    @Throws(IndexOutOfBoundsException::class)
    fun removeHomeworkAt(position: Int): Homework {
        val arrayList = ArrayList(homeworkData.value!!)
        val removedHw = arrayList.removeAt(position)
        homeworkData.postValue(arrayList)
        return removedHw
    }
}