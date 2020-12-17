package com.octaneee.workoutmaker.ui.fragment.plan.createmacrocycle.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class CreateMacrocycleFragmentViewModel(application: Application) : AndroidViewModel(application) {

//    private val macrocycleRepository =
//        MacrocycleRepository(WorkoutMakerDatabase.getDatabase(application).getMacrocycleDao())
//    private val mesocycleRepository =
//        MesocycleRepository(WorkoutMakerDatabase.getDatabase(application).getMesocycleDao())
//
//    lateinit var macrocycleWithMesocycles: MacrocycleWithMesocycles
//
//    fun saveMacrocycle() {
//        viewModelScope.launch(Dispatchers.IO) {
//            macrocycleRepository.insert(macrocycleWithMesocycles.macrocycle)
//            for (mesocycleAndMesocycleTypeWithMicrocycles in macrocycleWithMesocycles.mesocycles) {
//                mesocycleRepository.insert(mesocycleAndMesocycleTypeWithMicrocycles.mesocycle)
//            }
//        }
//    }
}