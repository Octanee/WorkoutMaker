package com.octaneee.workoutmaker.ui.bottom_sheet.mesocycle

import androidx.lifecycle.ViewModel
import com.octaneee.workoutmaker.model.entity.MesocycleType

class MesocycleBottomSheetViewModel : ViewModel() {
    var dataSet: List<MesocycleType> = listOf()
    var name: String = ""
    var index: Int = 0
}