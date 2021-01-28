package com.octaneee.workoutmaker.ui.main.fragment.plan.set_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octaneee.workoutmaker.model.entity.Series
import com.octaneee.workoutmaker.model.entity.Training
import com.octaneee.workoutmaker.model.entity.crossref.TrainingExerciseCrossRef
import com.octaneee.workoutmaker.model.relations.ExerciseWithSeries
import com.octaneee.workoutmaker.repository.MicrocycleRepository
import com.octaneee.workoutmaker.repository.SetRepository
import com.octaneee.workoutmaker.repository.TrainingRepository
import com.octaneee.workoutmaker.repository.crossref.TrainingExerciseCrossRefRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SetListViewModel @ViewModelInject constructor(
    private val trainingRepository: TrainingRepository,
    private val microcycleRepository: MicrocycleRepository,
    private val setRepository: SetRepository,
    private val trainingExerciseCrossRefRepository: TrainingExerciseCrossRefRepository
) : ViewModel() {

    var dataSet: LiveData<List<ExerciseWithSeries>> = MutableLiveData()

    lateinit var training: LiveData<Training>
    lateinit var numberOfDays: LiveData<Int>

    fun setTraining(trainingId: Long) {
        training = trainingRepository.getTraining(trainingId)
    }

    fun setNumberOfDays(microcycleId: Long) {
        numberOfDays = microcycleRepository.getNumberOfDaysByMicrocycleId(microcycleId)
    }

    fun setDataSet(trainingId: Long) {
        dataSet = trainingExerciseCrossRefRepository.getExerciseWithSetsByTrainingId(trainingId)
    }

    fun updateTraining(item: Training) = viewModelScope.launch(Dispatchers.IO) {
        trainingRepository.update(item)
    }

    fun deleteExercise(item: TrainingExerciseCrossRef) =
        viewModelScope.launch(Dispatchers.IO) {
            trainingExerciseCrossRefRepository.delete(item)
        }

    fun updateSetList(dataSet: List<Series>, removeList: List<Series>) =
        viewModelScope.launch(Dispatchers.IO) {
            setRepository.insertOrUpdate(dataSet)
            setRepository.delete(removeList)
        }
}