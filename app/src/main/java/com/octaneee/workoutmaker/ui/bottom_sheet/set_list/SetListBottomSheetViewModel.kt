package com.octaneee.workoutmaker.ui.bottom_sheet.set_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.octaneee.workoutmaker.model.entity.Set

class SetListBottomSheetViewModel @ViewModelInject constructor() :
    ViewModel() {

    val dataSet = MutableLiveData<MutableList<Set>>()
}