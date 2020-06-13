package com.github.dexluthor.diary.activities

import android.annotation.SuppressLint
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
import com.github.dexluthor.diary.entities.Lesson
import com.github.dexluthor.diary.entities.LessonType
import com.github.dexluthor.diary.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_add_lesson.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class AddLessonActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    private val viewModel = ViewModelFactory.getLessonsViewModel()
    private val subjectViewModel = ViewModelFactory.getSubjectViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_lesson)
        day_of_week.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.days,
            android.R.layout.simple_spinner_item
        )
        type.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, LessonType.values())

        subject_name.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, subjectViewModel.getNames())

        initUi()
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
        date.setText(ymd)
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
        time.setText(t)
    }

    private fun initUi() {
        //  val factory = InjectorUtils.providerSubjectViewModelFactory()
        //  val viewModel = ViewModelProviders.of(this, factory)
        //     .get(SubjectViewModel::class.java)

        //   viewModel.getSubjects().observe(this, Observer { subjects -> })
        //FIXME `!!!!!!!!!!!!!!!!!!
//        saveSubjectButton.setOnClickListener {
//      //      viewModel.addSubject(
//                Subject(
//                    subjectName.text.toString(),
//                    site.text.toString(),
//                    email.text.toString()
//                )
//            //)
//            subjectName.text.clear()
//            site.text.clear()
//            email.text.clear()
//        }
    }

    fun saveLesson(view: View) {
        val lesson = extractLessonFromFields()
        if (lesson != null) {
            viewModel.addLesson(lesson)
            finish()
            return
        }
        Toast.makeText(this, "Fill all necessary fields first", Toast.LENGTH_SHORT).show()
    }

    fun removeLesson(view: View) {
        val lesson = extractLessonFromFields()
        if (lesson != null) {
            viewModel.removeLesson(lesson)
            finish()
            return
        }
        Toast.makeText(this, "Fill all necessary fields first", Toast.LENGTH_SHORT).show()
    }

    private fun extractLessonFromFields(): Lesson? {
        if (!isValidState()) {
            return null
        }

        val type = if (type.selectedItem == "LECTURE") LessonType.LECTURE
        else LessonType.PRACTICE
        //TODO delete
        val arrayListOf = arrayListOf("kuku", "ahoj", "cao", "sws", "serus")
        return Lesson(
            LocalTime.parse(time.text),
            DayOfWeek.valueOf(
                day_of_week.selectedItem.toString().toUpperCase(Locale.ROOT)
            ),//FIXME 6:51 ne parsit
            LocalDate.parse(date.text.toString()),
            location.text.toString(),
            Integer.parseInt(duration.text.toString()),
            type,
            arrayListOf[(Math.random() * arrayListOf.size).toInt()]
            //TODO subject_name.selectedItem.toString()
        )
    }

    private fun isValidState() =
        //TODO  subject_name.selectedItem != null &&
        day_of_week.selectedItem != null
                && time.text != null
                && time.text.isNotEmpty()
                && duration.text != null
                && duration.text.isNotEmpty()
                && date.text != null
                && date.text.isNotEmpty()

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
}
