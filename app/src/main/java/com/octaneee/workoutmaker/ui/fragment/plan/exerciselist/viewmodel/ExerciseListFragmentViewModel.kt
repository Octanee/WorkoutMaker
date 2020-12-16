package com.octaneee.workoutmaker.ui.fragment.plan.exerciselist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.database.WorkoutMakerDatabase
import com.octaneee.workoutmaker.data.model.entity.Equipment
import com.octaneee.workoutmaker.data.model.entity.ExerciseType
import com.octaneee.workoutmaker.data.model.entity.Muscle
import com.octaneee.workoutmaker.data.model.relation.ExerciseHolder
import com.octaneee.workoutmaker.logic.repository.ExerciseRepository

class ExerciseListFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val exerciseRepository =
        ExerciseRepository(WorkoutMakerDatabase.getDatabase(application).getExerciseDao())

    lateinit var exerciseList: LiveData<List<ExerciseHolder>>

    fun setExerciseList(equipment: Equipment) {
        exerciseList = exerciseRepository.getExerciseHolderListByEquipment(equipment.equipmentId)
    }

    fun setExerciseList(muscle: Muscle) {
        exerciseList = exerciseRepository.getExerciseHolderListByMuscle(muscle.muscleId)
    }

    fun setExerciseList(exerciseType: ExerciseType) {
        exerciseList =
            exerciseRepository.getExerciseHolderListByExerciseType(exerciseType.exerciseTypeId)
    }
}