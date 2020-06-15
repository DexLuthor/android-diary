package com.github.dexluthor.diary.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.github.dexluthor.diary.R
import com.github.dexluthor.diary.activities.AddHomeworkActivity
import com.github.dexluthor.diary.activities.AddLessonActivity
import com.github.dexluthor.diary.activities.EditSubjectActivity
import kotlinx.android.synthetic.main.fragment_choose_what_to_create_dialog.*

class ChooseWhatToCreateDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_what_to_create_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_subject.setOnClickListener {
            startActivity(Intent(context, EditSubjectActivity::class.java))
        }
        add_homework.setOnClickListener {
            startActivity(Intent(context, AddHomeworkActivity::class.java))
        }
        add_lesson.setOnClickListener {
            startActivity(Intent(context, AddLessonActivity::class.java))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ChooseWhatToCreateDialogFragment()
    }

}