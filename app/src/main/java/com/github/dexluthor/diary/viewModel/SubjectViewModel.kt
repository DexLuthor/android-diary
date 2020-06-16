package com.github.dexluthor.diary.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.dexluthor.diary.entities.Subject
import java.util.*
import kotlin.collections.ArrayList

class SubjectViewModel {
    private val subjects = MutableLiveData<List<Subject>>(ArrayList())

    fun addSubject(subject: Subject): Boolean {
        val arrayList = ArrayList(subjects.value!!)
        for (i in 0 until arrayList.size) {
            if (arrayList[i] == subject) {
                if (arrayList[i].email == subject.email && arrayList[i].site == subject.site) {
                    return false
                }
                arrayList[i].site = subject.site
                arrayList[i].email = subject.email
                return true
            }
        }
        arrayList.add(subject)
        arrayList.sort()
        subjects.postValue(arrayList)
        return true
    }

    @Throws(IndexOutOfBoundsException::class)
    fun removeSubjectAt(position: Int): Subject {
        val arrayList = ArrayList(subjects.value!!)
        val removedSbj = arrayList.removeAt(position)
        subjects.postValue(arrayList)
        return removedSbj
    }

    fun getSubjects() = subjects as LiveData<List<Subject>>

    fun getNames() = subjects.value!!.map { s -> s.name }

    fun getSubjectByName(name: String): Subject? {
        val nameLower = name.toLowerCase(Locale.ROOT)

        for (subject in subjects.value!!) {
            if (subject.name.toLowerCase(Locale.ROOT) == nameLower) {
                return subject
            }
        }
        return null
    }

}