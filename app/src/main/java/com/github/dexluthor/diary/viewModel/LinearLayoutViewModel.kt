package com.github.dexluthor.diary.viewModel

import android.content.Context
import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LinearLayoutViewModel(context: Context) : ViewModel() {
    private val linearLayouts: MutableLiveData<List<LinearLayout>> =
        MutableLiveData<List<LinearLayout>>(
            mutableListOf(
                LinearLayout(context),
                LinearLayout(context),
                LinearLayout(context),
                LinearLayout(context),
                LinearLayout(context),
                LinearLayout(context)
            )
        )

    fun getLinearLayouts() = linearLayouts as LiveData<List<LinearLayout>>

}