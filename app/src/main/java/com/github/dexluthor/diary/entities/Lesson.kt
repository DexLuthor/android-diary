package com.github.dexluthor.diary.entities

import java.io.Serializable
import java.time.DayOfWeek
import java.time.LocalTime

data class Lesson(
    var startTime: LocalTime,
    var dayOfWeek: DayOfWeek,
    var location: String,
    var durationMinutes: Int,
    var type: LessonType,
    var subject: Subject
) : Comparable<Lesson>, Serializable {

    override fun compareTo(other: Lesson): Int {
        if (dayOfWeek < other.dayOfWeek)
            return -1
        else if (dayOfWeek > dayOfWeek)
            return 1
        return if (startTime < other.startTime) -1 else if (startTime > other.startTime) 1 else 0
    }
}

enum class LessonType {
    PRACTICE, LECTURE
}