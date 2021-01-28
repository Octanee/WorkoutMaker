package com.octaneee.workoutmaker.ui.bottom_sheet.series_log

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.octaneee.workoutmaker.model.entity.Series

class SeriesLogBottomSheetViewModel : ViewModel() {

    var series: MutableLiveData<Series> = MutableLiveData()

    var reps: Int? = null
    var weight: Float? = null
    var repsInReserve: Int? = null

}