package com.github.dexluthor.diary.viewModel

class ViewModelFactory {
    companion object {
        private var lessonsViewModel: LessonsViewModel? = null
        private var subjectViewModel: SubjectViewModel? = null
        private var homeworkViewModel: HomeworkViewModel? = null

        fun getLessonsViewModel() =
            lessonsViewModel
                ?: LessonsViewModel().also { lessonsViewModel = it }

        fun getSubjectViewModel() =
            subjectViewModel
                ?: SubjectViewModel().also { subjectViewModel = it }

        fun getHomeworkViewModel() =
            homeworkViewModel
                ?: HomeworkViewModel().also { homeworkViewModel = it }
    }
}