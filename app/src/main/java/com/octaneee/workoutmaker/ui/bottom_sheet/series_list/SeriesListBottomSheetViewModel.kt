package com.octaneee.workoutmaker.ui.bottom_sheet.series_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.octaneee.workoutmaker.model.SimpleExercise
import com.octaneee.workoutmaker.model.entity.Series

class SeriesListBottomSheetViewModel @ViewModelInject constructor() :
    ViewModel() {

    var trainingExerciseCrossRefId: Long? = null
    val dataSet = MutableLiveData<MutableList<Series>>()

    lateinit var exercise: SimpleExercise
}