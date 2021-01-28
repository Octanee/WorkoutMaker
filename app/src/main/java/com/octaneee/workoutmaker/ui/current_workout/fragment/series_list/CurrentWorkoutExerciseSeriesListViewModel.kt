package com.octaneee.workoutmaker.ui.current_workout.fragment.series_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.octaneee.workoutmaker.util.WorkoutManager

class CurrentWorkoutExerciseSeriesListViewModel @ViewModelInject constructor(val workoutManager: WorkoutManager) :
    ViewModel() {

    var restTime: Boolean = false
}