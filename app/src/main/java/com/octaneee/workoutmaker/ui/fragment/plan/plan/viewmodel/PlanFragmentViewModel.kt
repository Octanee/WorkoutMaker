package com.octaneee.workoutmaker.ui.fragment.plan.plan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.octaneee.workoutmaker.data.database.WorkoutMakerDatabase
import com.octaneee.workoutmaker.data.model.entity.Macrocycle
import com.octaneee.workoutmaker.data.model.relation.MacrocycleWithMesocycles
import com.octaneee.workoutmaker.logic.repository.MacrocycleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlanFragmentViewModel(application: Application) : AndroidViewModel(application) {

    var macrocycleWithMesocycles: MacrocycleWithMesocycles? = null

    private val macrocycleRepository =
        MacrocycleRepository(WorkoutMakerDatabase.getDatabase(application).getMacrocycleDao())

    fun deleteMacrocycle(macrocycle: Macrocycle) {
        viewModelScope.launch(Dispatchers.IO) {
            macrocycleRepository.delete(macrocycle)
        }
    }
}