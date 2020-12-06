package com.octaneee.workoutmaker.ui.fragment.plan.createmesocycle.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.octaneee.workoutmaker.data.database.WorkoutMakerDatabase
import com.octaneee.workoutmaker.data.model.entity.Mesocycle
import com.octaneee.workoutmaker.data.model.entity.MesocycleType
import com.octaneee.workoutmaker.data.model.relation.MacrocycleWithMesocycles
import com.octaneee.workoutmaker.data.model.relation.MesocycleAndMesocycleTypeWithMicrocycles
import com.octaneee.workoutmaker.logic.repository.MesocycleTypeRepository

class CreateMesocycleFragmentViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var macrocycleWithMesocycles: MacrocycleWithMesocycles

    var mesocycleAndMesocycleTypeWithMicrocycles: MesocycleAndMesocycleTypeWithMicrocycles =
        MesocycleAndMesocycleTypeWithMicrocycles(
            Mesocycle("", 0, 0),
            MesocycleType(""),
        )

    private val mesocycleTypeRepository =
        MesocycleTypeRepository(WorkoutMakerDatabase.getDatabase(application).getMesocycleTypeDao())

    var mesocycleTypeList = mesocycleTypeRepository.getMesocycleTypeList()
}