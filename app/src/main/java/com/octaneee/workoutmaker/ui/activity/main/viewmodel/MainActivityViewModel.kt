package com.octaneee.workoutmaker.ui.activity.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.octaneee.workoutmaker.data.database.WorkoutMakerDatabase
import com.octaneee.workoutmaker.data.model.entity.User
import com.octaneee.workoutmaker.logic.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository =
        UserRepository(WorkoutMakerDatabase.getDatabase(application).getUserDao())

    val userAndMicrocycle = userRepository.getUserAndMicrocycle()

    fun insertUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insert(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.update(user)
        }
    }
}