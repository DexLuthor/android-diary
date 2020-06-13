package com.github.dexluthor.diary.entities

import java.time.LocalDateTime

class Homework(
    val deadline: LocalDateTime,
    val description: String,
    val subjectName: String
) : Comparable<Homework> {
    var status: Boolean = false

    override fun toString(): String {
        return "Homework(deadline=$deadline, subjectName='$subjectName')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Homework

        if (deadline != other.deadline) return false
        if (description != other.description) return false
        if (status != other.status) return false
        if (subjectName != other.subjectName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = deadline.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + status.hashCode()
        result = 31 * result + subjectName.hashCode()
        return result
    }

    override fun compareTo(other: Homework) =
        if (deadline > other.deadline) 1 else if (deadline < other.deadline) -1 else 0

}