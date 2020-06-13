package com.github.dexluthor.diary.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.dexluthor.diary.entities.Subject
import java.text.FieldPosition

class SubjectViewModel {
    private val subjects = MutableLiveData<List<Subject>>(ArrayList())

    fun addSubject(subject: Subject) {
        val arrayList = ArrayList(subjects.value!!)
        arrayList.add(subject)
        subjects.postValue(arrayList)
    }

    fun removeSubject(subject: Subject) {
        val arrayList = ArrayList(subjects.value!!)
        arrayList.remove(subject)
        subjects.postValue(arrayList)
    }

    fun removeSubject(position: Int) {
        val arrayList = ArrayList(subjects.value!!)
        arrayList.removeAt(position)
        subjects.postValue(arrayList)
    }

    fun getSubjects() = subjects as LiveData<List<Subject>>

    fun getNames() = subjects.value!!.map { s -> s.name }


}