package com.octaneee.workoutmaker.ui.fragment.plan.createtraining.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.octaneee.workoutmaker.data.model.relation.TrainingWithSetAndExercises

class CreateTrainingFragmentViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var trainingWithSetAndExercises: TrainingWithSetAndExercises
}