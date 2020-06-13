package com.github.dexluthor.diary.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.dexluthor.diary.R
import com.github.dexluthor.diary.entities.Subject
import com.github.dexluthor.diary.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_add_subject.*

class AddSubjectActivity : AppCompatActivity() {
    private val viewModel = ViewModelFactory.getSubjectViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_subject)
    }

    fun saveSubject(view: View) {
        val subject = extractSubjectFromFields()
        if (subject != null) {
            viewModel.addSubject(subject)
            finish()
            return
        }
        Toast.makeText(this, "Fill all necessary fields first", Toast.LENGTH_SHORT).show()
    }

    fun removeSubject(view: View) {
        val subject = extractSubjectFromFields()
        if (subject != null) {
            viewModel.removeSubject(subject)
            finish()
            return
        }
        Toast.makeText(this, "Fill all necessary fields first", Toast.LENGTH_SHORT).show()
    }

    private fun extractSubjectFromFields(): Subject? {
        return if (subjectName.text.isEmpty()) null
        else Subject(
            subjectName.text.toString(),
            site.text.toString(),
            email.text.toString()
        )
    }

}
