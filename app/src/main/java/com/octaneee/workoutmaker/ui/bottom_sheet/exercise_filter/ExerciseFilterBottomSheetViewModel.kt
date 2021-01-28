package com.octaneee.workoutmaker.ui.bottom_sheet.exercise_filter

import androidx.lifecycle.ViewModel
import com.octaneee.workoutmaker.model.entity.Equipment
import com.octaneee.workoutmaker.model.entity.ExerciseType
import com.octaneee.workoutmaker.model.entity.Muscle
import com.octaneee.workoutmaker.other.ExerciseOrderType

class ExerciseFilterBottomSheetViewModel : ViewModel() {

    var muscleDataSet: List<Muscle> = listOf()
    var exerciseTypeDataSet: List<ExerciseType> = listOf()
    var equipmentDataSet: List<Equipment> = listOf()

    var orderType: ExerciseOrderType = ExerciseOrderType.ORDER_BY_MUSCLE
    var muscle: Muscle? = null
    var equipment: Equipment? = null
    var exerciseType: ExerciseType? = null
}