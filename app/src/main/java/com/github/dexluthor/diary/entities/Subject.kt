package com.github.dexluthor.diary.entities

import java.io.Serializable

class Subject(
    var name: String,
    var site: String,
    var email: String
) : Serializable {

    override fun toString(): String {
        return "Subject(name='$name')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Subject

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}