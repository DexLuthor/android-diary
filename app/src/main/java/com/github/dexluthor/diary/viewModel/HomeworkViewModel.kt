package com.github.dexluthor.diary.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.dexluthor.diary.entities.Homework

class HomeworkViewModel : ViewModel() {
    private val homeworkData = MutableLiveData<List<Homework>>(ArrayList())

    fun addHomework(homework: Homework) {
        val arrayList = ArrayList(homeworkData.value!!)
        arrayList.add(homework)
        homeworkData.postValue(arrayList)
    }

    fun removeHomework(homework: Homework) {
        val arrayList = ArrayList(homeworkData.value!!)
        arrayList.remove(homework)
        homeworkData.postValue(arrayList)
    }

    fun getHomework() = homeworkData as LiveData<List<Homework>>
}