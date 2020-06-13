package com.github.dexluthor.diary.entities

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

data class Lesson(
    var startTime: LocalTime,
    var dayOfWeek: DayOfWeek,
    var tillDate: LocalDate,
    var location: String,
    var durationMinutes: Int,
    var type: LessonType,
    var subjectname: String
) : Comparable<Lesson> {

    override fun compareTo(other: Lesson) =
        if (startTime < other.startTime) -1 else if (startTime > other.startTime) 1 else 0

}

enum class LessonType {
    PRACTICE, LECTURE
}