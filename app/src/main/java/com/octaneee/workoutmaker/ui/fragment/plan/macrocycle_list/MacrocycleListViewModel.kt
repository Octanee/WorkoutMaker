package com.octaneee.workoutmaker.ui.fragment.plan.macrocycle_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octaneee.workoutmaker.data.preferences.PreferencesManager
import com.octaneee.workoutmaker.model.entity.Macrocycle
import com.octaneee.workoutmaker.other.ApplicationScope
import com.octaneee.workoutmaker.repository.MacrocycleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MacrocycleListViewModel @ViewModelInject constructor(
    private val macrocycleRepository: MacrocycleRepository,
    private val preferencesManager: PreferencesManager,
    @ApplicationScope private val applicationScope: CoroutineScope
) : ViewModel() {

    val macrocycles = macrocycleRepository.getMacrocycles()

    var newMacrocycleName: String? = null

    fun setCurrentMacrocycle(macrocycleId: Long) = applicationScope.launch(Dispatchers.IO) {
        preferencesManager.saveCurrentMacrocycleId(macrocycleId)
    }

    fun addMacrocycle(item: Macrocycle): Long = runBlocking {
        macrocycleRepository.insert(item)
    }

    fun deleteMacrocycle(macrocycle: Macrocycle) = viewModelScope.launch(Dispatchers.IO) {
        macrocycleRepository.delete(macrocycle)
    }
}