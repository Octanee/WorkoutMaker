package com.octaneee.workoutmaker.ui.bottom_sheet.training

import androidx.lifecycle.ViewModel

class TrainingBottomSheetViewModel : ViewModel() {

    var name: String = ""
    var numberOfDay: Int = 1
    var numberOfDays = mutableListOf<Int>()
}