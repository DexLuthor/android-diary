package com.github.dexluthor.diary.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.dexluthor.diary.R
import com.github.dexluthor.diary.entities.Subject
import com.github.dexluthor.diary.viewModel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_edit_subject.*

class EditSubjectActivity : AppCompatActivity() {
    private val viewModel = ViewModelFactory.getSubjectViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_subject)

        fillFieldsIfHasExtras()
    }

    private fun fillFieldsIfHasExtras() {
        if (intent.extras == null) return

        val subject = intent.extras!!.getSerializable("subject") as Subject

        subjectName.setText(subject.name)
        email.setText(subject.email)
        site.setText(subject.site)
    }

    fun saveSubject(view: View) {
        val subject = extractSubjectFromFields()
        if (subject != null) {
            if (viewModel.addSubject(subject)) {
                Snackbar.make(view, "Subject was successfully added", Snackbar.LENGTH_SHORT).show()
                finish()
                return
            }
            Snackbar.make(view, "Subject already exists", Snackbar.LENGTH_SHORT).show()
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
