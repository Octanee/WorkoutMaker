package com.octaneee.workoutmaker.ui.bottom_sheet.macrocycle

import androidx.lifecycle.ViewModel
import java.util.*

class MacrocycleBottomSheetViewModel : ViewModel() {

    var name: String = ""
    var startDate: Date = Calendar.getInstance().time
    var endDate: Date? = null
}