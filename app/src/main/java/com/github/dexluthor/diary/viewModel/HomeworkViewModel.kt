package com.github.dexluthor.diary.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.dexluthor.diary.entities.Homework

class HomeworkViewModel : ViewModel() {
    private val homeworkData = MutableLiveData<List<Homework>>(ArrayList())

    fun addHomework(homework: Homework): Boolean {
        val arrayList = ArrayList(homeworkData.value!!)

        if (arrayList.contains(homework)) return false

        arrayList.add(homework)
        homeworkData.postValue(arrayList)
        return true
    }

    fun removeHomework(homework: Homework): Boolean {
        val arrayList = ArrayList(homeworkData.value!!)
        if (arrayList.remove(homework)) {
            homeworkData.postValue(arrayList)
            return true
        }
        return false
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