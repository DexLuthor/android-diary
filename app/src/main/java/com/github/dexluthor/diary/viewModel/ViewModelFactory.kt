package com.github.dexluthor.diary.viewModel

import android.content.Context

class ViewModelFactory {
    companion object {
        private var lessonsViewModel: LessonsViewModel? = null
        private var subjectViewModel: SubjectViewModel? = null
        private var homeworkViewModel: HomeworkViewModel? = null
        private var linearLayoutViewModel: LinearLayoutViewModel? = null

        fun getLessonsViewModel() =
            lessonsViewModel
                ?: LessonsViewModel().also { lessonsViewModel = it }

        fun getSubjectViewModel() =
            subjectViewModel
                ?: SubjectViewModel().also { subjectViewModel = it }

        fun getHomeworkViewModel() =
            homeworkViewModel
                ?: HomeworkViewModel().also { homeworkViewModel = it }

        fun getLinearLayoutViewModel(context: Context) =
            linearLayoutViewModel
                ?: LinearLayoutViewModel(context).also { linearLayoutViewModel = it }
    }
}