package com.octaneee.workoutmaker.ui.activity.main.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.database.WorkoutMakerDatabase
import com.octaneee.workoutmaker.data.model.entity.*
import com.octaneee.workoutmaker.data.model.entity.Set
import com.octaneee.workoutmaker.logic.repository.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository =
        UserRepository(WorkoutMakerDatabase.getDatabase(application).getUserDao())
    private val macrocycleRepository =
        MacrocycleRepository(WorkoutMakerDatabase.getDatabase(application).getMacrocycleDao())
    private val mesocycleTypeRepository =
        MesocycleTypeRepository(WorkoutMakerDatabase.getDatabase(application).getMesocycleTypeDao())
    private val mesocycleRepository =
        MesocycleRepository(WorkoutMakerDatabase.getDatabase(application).getMesocycleDao())
    private val microcycleRepository =
        MicrocycleRepository(WorkoutMakerDatabase.getDatabase(application).getMicrocycleDao())
    private val trainingRepository =
        TrainingRepository(WorkoutMakerDatabase.getDatabase(application).getTrainingDao())
    private val exerciseTypeRepository =
        ExerciseTypeRepository(WorkoutMakerDatabase.getDatabase(application).getExerciseTypeDao())
    private val equipmentRepository =
        EquipmentRepository(WorkoutMakerDatabase.getDatabase(application).getEquipmentDao())
    private val muscleRepository =
        MuscleRepository(WorkoutMakerDatabase.getDatabase(application).getMuscleDao())
    private val exerciseRepository =
        ExerciseRepository(WorkoutMakerDatabase.getDatabase(application).getExerciseDao())
    private val setTypeRepository =
        SetTypeRepository(WorkoutMakerDatabase.getDatabase(application).getSetTypeDao())
    private val setRepository =
        SetRepository(WorkoutMakerDatabase.getDatabase(application).getSetDao())

    val userAndMacrocycle = userRepository.getUser(1)

    fun prePopulate(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val macrocycleId = macrocycleRepository.insert(Macrocycle("Test Macrocycle"))
            val mesocycleTypeId =
                mesocycleTypeRepository.insert(MesocycleType("Test Mesocycle Type"))
            val mesocycleId = mesocycleRepository.insert(
                Mesocycle(
                    "Test Mesocycle",
                    mesocycleTypeId,
                    macrocycleId
                )
            )
            val microcycleId = microcycleRepository.insert(Microcycle(mesocycleId, 6))
            val trainingId = trainingRepository.insert(Training("Test Training", microcycleId, 1))
            val exerciseTypeId = exerciseTypeRepository.insert(
                ExerciseType(
                    "Test Exercise Type",
                    context.resources.getResourceEntryName(R.drawable.ic_body)
                )
            )
            val equipmentId = equipmentRepository.insert(
                Equipment(
                    "Test Equipment",
                    context.resources.getResourceEntryName(R.drawable.ic_gym)
                )
            )
            val muscleId =
                muscleRepository.insert(
                    Muscle(
                        "Test muscle",
                        context.resources.getResourceEntryName(R.drawable.man_figure_front_chest)
                    )
                )
            val exerciseId =
                exerciseRepository.insert(
                    Exercise(
                        "Test Exercise",
                        exerciseTypeId,
                        equipmentId,
                        muscleId
                    )
                )
            val setTypeId = setTypeRepository.insert(SetType("Test Set Type"))
            val setId = setRepository.insert(Set(trainingId, setTypeId, exerciseId, 6, 8))

            val user = User("Test User", 180)
            user.macrocycleId = macrocycleId
            userRepository.insert(user)
        }
    }
}