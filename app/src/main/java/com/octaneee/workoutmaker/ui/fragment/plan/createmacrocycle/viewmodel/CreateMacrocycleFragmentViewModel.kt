package com.octaneee.workoutmaker.ui.fragment.plan.createmacrocycle.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.octaneee.workoutmaker.data.model.entity.Macrocycle
import com.octaneee.workoutmaker.data.model.relation.MacrocycleWithMesocycles

class CreateMacrocycleFragmentViewModel(application: Application) : AndroidViewModel(application) {

    var macrocycleWithMesocycles: MacrocycleWithMesocycles = MacrocycleWithMesocycles(
        Macrocycle("")
    )
}