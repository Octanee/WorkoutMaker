package com.octaneee.workoutmaker.ui.activity.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.octaneee.workoutmaker.data.database.WorkoutMakerDatabase
import com.octaneee.workoutmaker.data.model.entity.User
import com.octaneee.workoutmaker.logic.repository.*
import com.octaneee.workoutmaker.logic.repository.crossref.TrainingSetCrossRefRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository =
        UserRepository(WorkoutMakerDatabase.getDatabase(application).getUserDao())
    private val mesocycleTypeRepository =
        MesocycleTypeRepository(WorkoutMakerDatabase.getDatabase(application).getMesocycleTypeDao())
    private val setTypeRepository =
        SetTypeRepository(WorkoutMakerDatabase.getDatabase(application).getSetTypeDao())
    private val trainingSetCrossRefRepository = TrainingSetCrossRefRepository(
        WorkoutMakerDatabase.getDatabase(application).getTrainingSetCrossRefDao()
    )
    private val trainingRepository =
        TrainingRepository(WorkoutMakerDatabase.getDatabase(application).getTrainingDao())
    private val microcycleRepository =
        MicrocycleRepository(WorkoutMakerDatabase.getDatabase(application).getMicrocycleDao())


    val userAndMicrocycle = userRepository.getUserAndMicrocycle()

    fun insertUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insert(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.update(user)
        }
    }

    fun prePopulate() {
        viewModelScope.launch(Dispatchers.IO) {
            //Types
//            mesocycleTypeRepository.insert(MesocycleType("Test Mesocycle Type"))
//            setTypeRepository.insert(SetType("Test Set Type"))

//            trainingSetCrossRefRepository.insert(
//                TrainingSetCrossRef(
//                    trainingRepository.insert(
//                        Training(
//                            "Test Training",
//
//                        )
//                    )
//                )
//            )

            //userRepository.insert(User("Test User", 180))
        }
    }
}