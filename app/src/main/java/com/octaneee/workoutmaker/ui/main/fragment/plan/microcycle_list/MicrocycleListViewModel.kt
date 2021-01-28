package com.octaneee.workoutmaker.ui.main.fragment.plan.microcycle_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octaneee.workoutmaker.model.entity.Mesocycle
import com.octaneee.workoutmaker.model.entity.Microcycle
import com.octaneee.workoutmaker.repository.MesocycleRepository
import com.octaneee.workoutmaker.repository.MesocycleTypeRepository
import com.octaneee.workoutmaker.repository.MicrocycleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MicrocycleListViewModel @ViewModelInject constructor(
    private val mesocycleRepository: MesocycleRepository,
    private val mesocycleTypeRepository: MesocycleTypeRepository,
    private val microcycleRepository: MicrocycleRepository
) : ViewModel() {

    lateinit var mesocycle: LiveData<Mesocycle>
    lateinit var microcycleList: LiveData<List<Microcycle>>

    val mesocycleTypes = mesocycleTypeRepository.getMesocycleTypes()

    fun setMesocycleAndMicrocycleList(mesocycleId: Long) {
        mesocycle = mesocycleRepository.getMesocycle(mesocycleId)
        microcycleList = microcycleRepository.getMicrocycleListByMesocycleId(mesocycleId)
    }

    fun saveNewMicrocycle(item: Microcycle): Long = runBlocking {
        microcycleRepository.insert(item)
    }

    fun deleteMicrocycle(item: Microcycle) = viewModelScope.launch(Dispatchers.IO) {
        microcycleRepository.delete(item)
    }

    fun updateMesocycle(item: Mesocycle) = viewModelScope.launch(Dispatchers.IO) {
        mesocycleRepository.update(item)
    }
}