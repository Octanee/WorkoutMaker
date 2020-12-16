package com.octaneee.workoutmaker.ui.fragment.plan.macrocyclelist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.octaneee.workoutmaker.data.database.WorkoutMakerDatabase
import com.octaneee.workoutmaker.data.model.entity.Macrocycle
import com.octaneee.workoutmaker.data.model.relation.MacrocycleWithMesocycles
import com.octaneee.workoutmaker.logic.repository.MacrocycleRepository
import kotlinx.coroutines.launch

class MacrocycleListFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val macrocycleRepository =
        MacrocycleRepository(WorkoutMakerDatabase.getDatabase(application).getMacrocycleDao())

    val macrocycles = macrocycleRepository.getMacrocycles()

    fun deleteMacrocycle(item: Macrocycle) {
        viewModelScope.launch {
            macrocycleRepository.delete(item)
        }
    }

    fun getMacrocycleWithMesocyclesById(macrocycleId: Long): LiveData<MacrocycleWithMesocycles> {
        return macrocycleRepository.getMacrocycleById(macrocycleId)
    }
}