package com.octaneee.workoutmaker.ui.fragment.plan.exercise_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octaneee.workoutmaker.data.dao.crossref.TrainingExerciseCrossRefDao
import com.octaneee.workoutmaker.model.entity.*
import com.octaneee.workoutmaker.model.entity.Set
import com.octaneee.workoutmaker.model.entity.crossref.TrainingExerciseCrossRef
import com.octaneee.workoutmaker.other.ExerciseOrderType
import com.octaneee.workoutmaker.repository.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ExerciseListViewModel @ViewModelInject constructor(
    private val exerciseRepository: ExerciseRepository,
    private val muscleRepository: MuscleRepository,
    private val exerciseTypeRepository: ExerciseTypeRepository,
    private val equipmentRepository: EquipmentRepository,
    private val trainingExerciseCrossRefDao: TrainingExerciseCrossRefDao,
    private val setRepository: SetRepository
) : ViewModel() {

    var orderType: ExerciseOrderType = ExerciseOrderType.ORDER_BY_MUSCLE
    var sortMuscle: Muscle? = null
    var sortEquipment: Equipment? = null
    var sortExerciseType: ExerciseType? = null

    var exerciseList = exerciseRepository.getExerciseList(
        orderType,
        sortMuscle?.muscleId,
        sortExerciseType?.exerciseTypeId,
        sortEquipment?.equipmentId
    )

    val muscleList = muscleRepository.getMuscleList()
    val equipmentList = equipmentRepository.getEquipmentList()
    val exerciseTypeList = exerciseTypeRepository.getExerciseTypeList()

    fun getFilteredExerciseList(): LiveData<List<Exercise>> {
        Timber.d("GetFilteredExerciseList")
        return exerciseRepository.getExerciseList(
            orderType,
            sortMuscle?.muscleId,
            sortExerciseType?.exerciseTypeId,
            sortEquipment?.equipmentId
        )
    }

    fun saveSets(dataSet: List<Set>, trainingId: Long, exerciseId: Long) =
        viewModelScope.launch(Dispatchers.IO) {
            val trainingExerciseCrossRefId = trainingExerciseCrossRefDao.insert(
                TrainingExerciseCrossRef(
                    trainingId,
                    exerciseId
                )
            )
            dataSet.forEach {
                it.trainingExerciseCrossRefIdFk = trainingExerciseCrossRefId
            }
            setRepository.insert(dataSet)
        }
}