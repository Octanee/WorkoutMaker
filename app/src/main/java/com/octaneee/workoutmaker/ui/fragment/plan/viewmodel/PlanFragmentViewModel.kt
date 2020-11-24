package com.octaneee.workoutmaker.ui.fragment.plan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.octaneee.workoutmaker.data.database.WorkoutMakerDatabase
import com.octaneee.workoutmaker.data.model.entity.Macrocycle
import com.octaneee.workoutmaker.data.model.entity.User
import com.octaneee.workoutmaker.logic.repository.MacrocycleRepository

class PlanFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val macrocycleRepository =
        MacrocycleRepository(WorkoutMakerDatabase.getDatabase(application).getMacrocycleDao())

    fun getCurrentMacrocycle(user: User): Macrocycle? {
        return if (user.macrocycleId > 0) {
            macrocycleRepository.getMacrocycleById(user.macrocycleId)
        } else {
            null
        }
    }
}