package com.octaneee.workoutmaker.ui.fragment.setup.logo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.octaneee.workoutmaker.data.preferences.PreferencesManager


class LogoViewModel @ViewModelInject constructor(private val preferencesManager: PreferencesManager) :
    ViewModel() {

    val firstTime = preferencesManager.firstTime.asLiveData()
}