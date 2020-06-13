package com.github.dexluthor.diary.entities

class Subject(
    val name: String,
    val site: String,
    val email: String
) {

    override fun toString(): String {
        return "Subject(name='$name', site='$site', email='$email')"
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