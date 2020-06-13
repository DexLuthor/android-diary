package com.github.dexluthor.diary.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.dexluthor.diary.R
import com.github.dexluthor.diary.entities.Homework
import com.github.dexluthor.diary.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_add_homework.*
import java.time.LocalDateTime

class AddHomeworkActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    private val homeworkViewModel = ViewModelFactory.getHomeworkViewModel()
    private val subjectViewModel = ViewModelFactory.getSubjectViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_homework)

        subject_name.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, subjectViewModel.getNames())
    }

    fun saveHomework(view: View) {
        val homework = extractHomeworkFromFields()
        if (homework != null) {
            homeworkViewModel.addHomework(homework)
            finish()
            return
        }
        Toast.makeText(this, "Fill all necessary fields first", Toast.LENGTH_SHORT).show()
    }

    fun removeHomework(view: View) {
        val homework = extractHomeworkFromFields()
        if (homework != null) {
            homeworkViewModel.removeHomework(homework)
            finish()
            return
        }
        Toast.makeText(this, "Fill all necessary fields first", Toast.LENGTH_SHORT).show()
    }

    private fun extractHomeworkFromFields(): Homework? {
        return if (!isValidState()) null
        else Homework(
            LocalDateTime.parse("${dateTextView.text}T${timeTextView.text}"),
            description.text.toString(),
            subject_name.selectedItem.toString()
        )
    }

    private fun isValidState() =
        subject_name.selectedItem != null
                && subject_name.selectedItem.toString().isNotEmpty()
                && description.text.isNotEmpty()
                && dateTextView.text.isNotEmpty()
                && timeTextView.text.isNotEmpty()

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

    fun onChooseDateButtonClick(view: View) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            this,
            this,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DATE)
        ).show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        var ymd = "$year-"
        ymd += when {
            month < 10 -> "0$month-"
            else -> "$month-"
        }
        ymd += when {
            dayOfMonth < 10 -> "0$dayOfMonth"
            else -> "$dayOfMonth"
        }
        dateTextView.text = ymd
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        var time = ""
        time += when {
            hourOfDay < 10 -> "0$hourOfDay"
            else -> "$hourOfDay"
        }
        time += when {
            minute < 10 -> ":0$minute"
            else -> ":$minute"
        }
        timeTextView.text = time
    }
}