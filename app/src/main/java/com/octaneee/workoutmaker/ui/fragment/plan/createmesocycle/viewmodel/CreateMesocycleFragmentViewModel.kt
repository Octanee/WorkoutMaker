package com.octaneee.workoutmaker.ui.fragment.plan.createmesocycle.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.octaneee.workoutmaker.data.database.WorkoutMakerDatabase
import com.octaneee.workoutmaker.data.model.entity.MesocycleType
import com.octaneee.workoutmaker.logic.repository.MesocycleTypeRepository

class CreateMesocycleFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val mesocycleTypeRepository =
        MesocycleTypeRepository(WorkoutMakerDatabase.getDatabase(application).getMesocycleTypeDao())

    var mesocycleTypeList = mesocycleTypeRepository.getMesocycleTypeList()

    lateinit var mesocycleType: MesocycleType


}