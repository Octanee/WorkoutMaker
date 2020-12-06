package com.octaneee.workoutmaker.ui.fragment.plan.createmicrocycle.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.octaneee.workoutmaker.data.model.entity.Microcycle
import com.octaneee.workoutmaker.data.model.relation.MacrocycleWithMesocycles
import com.octaneee.workoutmaker.data.model.relation.MesocycleAndMesocycleTypeWithMicrocycles
import com.octaneee.workoutmaker.data.model.relation.MicrocycleWithTrainings

class CreateMicrocycleFragmentViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var macrocycleWithMesocycles: MacrocycleWithMesocycles
    lateinit var mesocycleAndMesocycleTypeWithMicrocycles: MesocycleAndMesocycleTypeWithMicrocycles

    var microcycleWithTrainings = MicrocycleWithTrainings(Microcycle(0, 0))
}