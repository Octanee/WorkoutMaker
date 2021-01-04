package com.octaneee.workoutmaker.ui.activity.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.octaneee.workoutmaker.repository.MuscleRepository

class MainViewModel @ViewModelInject constructor(
    private val muscleRepository: MuscleRepository
) : ViewModel() {

    val muscles = muscleRepository.getMuscleList()
}