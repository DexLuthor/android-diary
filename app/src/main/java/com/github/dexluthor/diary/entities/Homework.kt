package com.github.dexluthor.diary.entities

import java.io.Serializable
import java.time.LocalDateTime

class Homework(
    var deadline: LocalDateTime,
    var description: String,
    var subject: Subject
) : Comparable<Homework>, Serializable {
    var status: Boolean = false

    override fun toString(): String {
        return "Homework(deadline=$deadline, subjectName='$subject')"
    }

    override fun compareTo(other: Homework) =
        if (deadline > other.deadline) 1 else if (deadline < other.deadline) -1 else 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Homework

        if (subject != other.subject) return false
        if (deadline != other.deadline) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = deadline.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + subject.hashCode()
        return result
    }

}