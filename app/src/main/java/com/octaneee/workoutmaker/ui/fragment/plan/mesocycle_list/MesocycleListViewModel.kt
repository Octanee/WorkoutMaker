package com.octaneee.workoutmaker.ui.fragment.plan.mesocycle_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octaneee.workoutmaker.model.entity.Macrocycle
import com.octaneee.workoutmaker.model.entity.Mesocycle
import com.octaneee.workoutmaker.model.entity.MesocycleType
import com.octaneee.workoutmaker.model.relations.MesocycleAndMesocycleType
import com.octaneee.workoutmaker.repository.MacrocycleRepository
import com.octaneee.workoutmaker.repository.MesocycleRepository
import com.octaneee.workoutmaker.repository.MesocycleTypeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MesocycleListViewModel @ViewModelInject constructor(
    private val macrocycleRepository: MacrocycleRepository,
    private val mesocycleRepository: MesocycleRepository,
    private val mesocycleTypeRepository: MesocycleTypeRepository
) : ViewModel() {

    lateinit var macrocycle: LiveData<Macrocycle>
    lateinit var mesocycleAndMesocycleTypeList: LiveData<List<MesocycleAndMesocycleType>>

    var newMacrocycleName: String? = null
    var newMesocycleName: String? = null
    var newMesocycleType: MesocycleType? = null

    val mesocycleTypes = mesocycleTypeRepository.getMesocycleTypes()

    fun setMacrocycleAndMesocycleList(macrocycleId: Long) {
        macrocycle = macrocycleRepository.getMacrocycle(macrocycleId)
        mesocycleAndMesocycleTypeList =
            mesocycleRepository.getMesocycleAndMesocycleTypeListByMacrocycleId(macrocycleId)
    }

    fun saveNewMesocycle(item: Mesocycle): Long = runBlocking {
        mesocycleRepository.insert(item)
    }

    fun deleteMesocycle(item: Mesocycle) = viewModelScope.launch(Dispatchers.IO) {
        mesocycleRepository.delete(item)
    }

    fun updateMacrocycle(item: Macrocycle) = viewModelScope.launch(Dispatchers.IO) {
        macrocycleRepository.update(item)
    }

    fun updateMesocyclesPosition(fromItem: Mesocycle, toItem: Mesocycle) =
        viewModelScope.launch(Dispatchers.IO) {
            mesocycleRepository.update(fromItem)
            mesocycleRepository.update(toItem)
        }
}