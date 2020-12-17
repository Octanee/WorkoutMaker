package com.octaneee.workoutmaker.ui.fragment.plan.createmesocycle.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.octaneee.workoutmaker.data.model.relation.MesocycleAndMesocycleTypeWithMicrocycles

class CreateMesocycleFragmentViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var mesocycleAndMesocycleTypeWithMicrocycles: MesocycleAndMesocycleTypeWithMicrocycles


//    private val mesocycleTypeRepository =
//        MesocycleTypeRepository(WorkoutMakerDatabase.getDatabase(application).getMesocycleTypeDao())
//
//    var mesocycleTypeList = mesocycleTypeRepository.getMesocycleTypeList()
}