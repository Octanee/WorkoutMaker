package com.octaneee.workoutmaker.ui.main.fragment.workout

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.octaneee.workoutmaker.model.relations.TrainingWithExercises
import com.octaneee.workoutmaker.repository.TrainingRepository
import com.octaneee.workoutmaker.util.WorkoutManager

class WorkoutViewModel @ViewModelInject constructor(
    private val trainingRepository: TrainingRepository,
    val workoutManager: WorkoutManager
) : ViewModel() {

    lateinit var trainingWithExercises: LiveData<TrainingWithExercises>

    fun setTrainingWithExercises(trainingId: Long) {
        trainingWithExercises =
            trainingRepository.getTrainingWithExercises(trainingId)
    }
}