package com.octaneee.workoutmaker.ui.fragment.plan.training_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octaneee.workoutmaker.model.entity.Microcycle
import com.octaneee.workoutmaker.model.entity.Training
import com.octaneee.workoutmaker.repository.MicrocycleRepository
import com.octaneee.workoutmaker.repository.TrainingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TrainingListViewModel @ViewModelInject constructor(
    private val microcycleRepository: MicrocycleRepository,
    private val trainingRepository: TrainingRepository
) : ViewModel() {

    lateinit var microcycle: LiveData<Microcycle>
    lateinit var trainingList: LiveData<List<Training>>

    var newMicrocycleName: String? = null
    var newMicrocycleNumberOfDays: String? = null
    var newTrainingName: String? = null
    var newTrainingNumberOfDay: Int? = null

    fun setMicrocycleAndTrainingList(microcycleId: Long) {
        microcycle = microcycleRepository.getMicrocycle(microcycleId)
        trainingList = trainingRepository.getTrainingByMicrocycleId(microcycleId)
    }

    fun saveNewTraining(item: Training): Long = runBlocking {
        trainingRepository.insert(item)

    }

    fun deleteTraining(item: Training) = viewModelScope.launch(Dispatchers.IO) {
        trainingRepository.delete(item)
    }

    fun updateMicrocycle(item: Microcycle) = viewModelScope.launch(Dispatchers.IO) {
        microcycleRepository.update(item)
    }
}