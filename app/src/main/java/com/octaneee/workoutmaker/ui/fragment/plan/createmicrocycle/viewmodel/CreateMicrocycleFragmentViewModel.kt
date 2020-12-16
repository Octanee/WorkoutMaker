package com.octaneee.workoutmaker.ui.fragment.plan.createmicrocycle.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.octaneee.workoutmaker.data.model.relation.MicrocycleWithTrainings

class CreateMicrocycleFragmentViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var microcycleWithTrainings: MicrocycleWithTrainings
}