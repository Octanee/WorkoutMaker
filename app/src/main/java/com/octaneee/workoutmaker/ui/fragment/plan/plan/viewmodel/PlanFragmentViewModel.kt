package com.octaneee.workoutmaker.ui.fragment.plan.plan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.octaneee.workoutmaker.data.model.relation.MacrocycleWithMesocycles

class PlanFragmentViewModel(application: Application) : AndroidViewModel(application) {

    var macrocycleWithMesocycles: MacrocycleWithMesocycles? = null

//    private val macrocycleRepository =
//        MacrocycleRepository(WorkoutMakerDatabase.getDatabase(application).getMacrocycleDao())

//    fun deleteMacrocycle(macrocycle: Macrocycle) {
//        viewModelScope.launch(Dispatchers.IO) {
//            macrocycleRepository.delete(macrocycle)
//        }
//    }
}