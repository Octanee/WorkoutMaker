package com.octaneee.workoutmaker.ui.fragment.plan.createmacrocycle.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.octaneee.workoutmaker.data.model.relation.MacrocycleWithMesocycles
import com.octaneee.workoutmaker.data.model.relation.MesocycleAndMesocycleTypeWithMicrocycles

class CreateMacrocycleFragmentViewModel(application: Application) : AndroidViewModel(application) {

    var macrocycleWithMesocycles: MacrocycleWithMesocycles? = null

    lateinit var macrocycleName: String

    var mesocycleAndMesocycleTypeWithMicrocyclesList =
        mutableListOf<MesocycleAndMesocycleTypeWithMicrocycles>()

}