package com.octaneee.workoutmaker.ui.fragment.plan.createtraining.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.octaneee.workoutmaker.data.model.entity.Training
import com.octaneee.workoutmaker.data.model.relation.MacrocycleWithMesocycles
import com.octaneee.workoutmaker.data.model.relation.MesocycleAndMesocycleTypeWithMicrocycles
import com.octaneee.workoutmaker.data.model.relation.MicrocycleWithTrainings
import com.octaneee.workoutmaker.data.model.relation.TrainingWithSets

class CreateTrainingFragmentViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var macrocycleWithMesocycles: MacrocycleWithMesocycles
    lateinit var mesocycleAndMesocycleTypeWithMicrocycles: MesocycleAndMesocycleTypeWithMicrocycles
    lateinit var microcycleWithTrainings: MicrocycleWithTrainings

    var trainingWithString = TrainingWithSets(
        Training("", 0, 0)
    )
}