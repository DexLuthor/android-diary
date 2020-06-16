package com.github.dexluthor.diary.activities

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.dexluthor.diary.R
import com.github.dexluthor.diary.entities.Lesson
import com.github.dexluthor.diary.entities.LessonType
import com.github.dexluthor.diary.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_add_lesson.*
import java.time.DayOfWeek
import java.time.LocalTime

class AddLessonActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {

    private val viewModel = ViewModelFactory.getLessonsViewModel()
    private val subjectViewModel = ViewModelFactory.getSubjectViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_lesson)
        day_of_week.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.days,
            android.R.layout.simple_spinner_dropdown_item
        )
        type.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.types,
            android.R.layout.simple_spinner_dropdown_item
        )
        subject_name.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, subjectViewModel.getNames())
    }

    @SuppressLint("SetTextI18n")
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        var t = ""
        t += when {
            hourOfDay < 10 -> "0$hourOfDay"
            else -> "$hourOfDay"
        }
        t += when {
            minute < 10 -> ":0$minute"
            else -> ":$minute"
        }
        time.text = t
    }

    fun saveLesson(view: View) {
        val lesson = extractLessonFromFields()
        if (lesson != null) {
            viewModel.addLesson(lesson)
            finish()
            return
        }
        Toast.makeText(this, resources.getString(R.string.fill_all_fields), Toast.LENGTH_SHORT)
            .show()
    }

    private fun extractLessonFromFields(): Lesson? {
        if (!isValidState()) return null

        val type =
            if (type.selectedItem == "LECTURE" || type.selectedItem == "Лекция" || type.selectedItem == "Prednáška") LessonType.LECTURE
            else LessonType.PRACTICE

        val dayOfWeek = when (day_of_week.selectedItem) {
            "Понедельник" -> DayOfWeek.MONDAY
            "Pondelok" -> DayOfWeek.MONDAY
            "Вторник" -> DayOfWeek.TUESDAY
            "Utorok" -> DayOfWeek.TUESDAY
            "Среда" -> DayOfWeek.WEDNESDAY
            "Streda" -> DayOfWeek.WEDNESDAY
            "Четверг" -> DayOfWeek.THURSDAY
            "Štvrtok" -> DayOfWeek.THURSDAY
            else -> DayOfWeek.FRIDAY
        }

        return Lesson(
            LocalTime.parse(time.text),
            dayOfWeek,
            location.text.toString(),
            Integer.parseInt(duration.text.toString()),
            type,
            subjectViewModel.getSubjectByName(subject_name.selectedItem.toString())!!
        )
    }

    private fun isValidState() =
        subject_name.selectedItem != null
                && day_of_week.selectedItem != null
                && time.text != null
                && time.text.isNotEmpty()
                && duration.text != null
                && duration.text.isNotEmpty()

    fun onChooseTimeButtonClick(view: View) {
        val calendar = Calendar.getInstance()
        TimePickerDialog(
            this,
            this,
            calendar.get(Calendar.HOUR),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }

}
