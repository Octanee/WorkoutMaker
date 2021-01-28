package com.octaneee.workoutmaker.ui.setup.fragment.setup

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.octaneee.workoutmaker.data.preferences.PreferencesManager
import com.octaneee.workoutmaker.other.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SetupViewModel @ViewModelInject constructor(
    private val preferencesManager: PreferencesManager,
    @ApplicationScope private val applicationScope: CoroutineScope
) : ViewModel() {

    var userName = ""
    var userHeight = 0

    fun saveUserData() {
        applicationScope.launch {
            preferencesManager.saveUserName(userName)
            preferencesManager.saveUserHeight(userHeight)
            preferencesManager.saveFirstTime(false)
        }
    }
}