package com.octaneee.workoutmaker.ui.fragment.plan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.octaneee.workoutmaker.data.database.WorkoutMakerDatabase
import com.octaneee.workoutmaker.data.model.entity.Macrocycle
import com.octaneee.workoutmaker.logic.repository.MacrocycleRepository
import com.octaneee.workoutmaker.logic.repository.UserRepository

class PlanFragmentViewModel(application: Application) : AndroidViewModel(application) {


    private val macrocycleRepository =
        MacrocycleRepository(WorkoutMakerDatabase.getDatabase(application).getMacrocycleDao())



    fun getCurrentMacrocycle(): Macrocycle? {
        return if (user.macrocycleId > 0) {
            macrocycleRepository.getMacrocycleById(user.macrocycleId)
        } else {
            null
        }
    }
}