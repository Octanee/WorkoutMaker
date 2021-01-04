package com.octaneee.workoutmaker.ui.fragment.plan.plan

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.octaneee.workoutmaker.data.preferences.PreferencesManager
import com.octaneee.workoutmaker.model.entity.Macrocycle
import com.octaneee.workoutmaker.repository.MacrocycleRepository

class PlanViewModel @ViewModelInject constructor(
    private val macrocycleRepository: MacrocycleRepository,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    var currentMacrocycle: LiveData<Macrocycle>? = null

    val currentMacrocycleId = preferencesManager.currentMacrocycleId.asLiveData()

    fun setupCurrentMacrocycle(macrocycleId: Long) {
        currentMacrocycle = macrocycleRepository.getMacrocycle(macrocycleId)
    }
}