package com.octaneee.workoutmaker.ui.fragment.plan.set_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octaneee.workoutmaker.model.entity.Set
import com.octaneee.workoutmaker.model.entity.Training
import com.octaneee.workoutmaker.model.entity.crossref.TrainingExerciseCrossRef
import com.octaneee.workoutmaker.model.relations.ExerciseWithSets
import com.octaneee.workoutmaker.repository.SetRepository
import com.octaneee.workoutmaker.repository.TrainingRepository
import com.octaneee.workoutmaker.repository.crossref.TrainingExerciseCrossRefRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SetListViewModel @ViewModelInject constructor(
    private val trainingRepository: TrainingRepository,
    private val setRepository: SetRepository,
    private val trainingExerciseCrossRefRepository: TrainingExerciseCrossRefRepository
) : ViewModel() {

    var dataSet: LiveData<List<ExerciseWithSets>> = MutableLiveData()

    lateinit var training: LiveData<Training>

    var newTrainingName: String? = null
    var newTrainingNumberOfDay: Int? = null

    fun setTraining(trainingId: Long) {
        training = trainingRepository.getTraining(trainingId)
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

    fun updateSetList(dataSet: List<Set>, removeList: List<Set>) =
        viewModelScope.launch(Dispatchers.IO) {
            setRepository.insertOrUpdate(dataSet)
            setRepository.delete(removeList)
        }
}