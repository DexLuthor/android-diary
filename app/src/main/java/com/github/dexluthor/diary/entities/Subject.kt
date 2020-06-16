package com.github.dexluthor.diary.entities

import java.io.Serializable

data class Subject(
    var name: String,
    var site: String,
    var email: String
) : Serializable, Comparable<Subject> {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Subject

        return name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun compareTo(other: Subject) = name.compareTo(other.name)
}