package com.github.dexluthor.diary

import com.github.dexluthor.diary.entities.Lesson
import com.github.dexluthor.diary.entities.LessonType
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

object LessonTest {
    val lesson = Lesson(
        LocalTime.MAX,
        DayOfWeek.FRIDAY,
        LocalDate.MAX,
        "oi",
        0,
        LessonType.LECTURE,
        "hello"
    )
    val lesson2 = Lesson(
        LocalTime.MAX,
        DayOfWeek.FRIDAY,
        LocalDate.MAX,
        "as",
        12,
        LessonType.LECTURE,
        "ls2"
    )
}