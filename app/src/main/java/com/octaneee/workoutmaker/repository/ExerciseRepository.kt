package com.octaneee.workoutmaker.repository

import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.dao.ExerciseDao
import com.octaneee.workoutmaker.model.entity.Exercise
import com.octaneee.workoutmaker.other.ExerciseOrderType
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class ExerciseRepository @Inject constructor(private val dao: ExerciseDao) :
    BaseRepository<Exercise>(dao) {

    fun getExerciseList(
        orderType: ExerciseOrderType,
        muscleId: Long? = null,
        exerciseTypeId: Long? = null,
        equipmentId: Long? = null
    ): LiveData<List<Exercise>> =
        dao.getExerciseList(orderType, muscleId, exerciseTypeId, equipmentId)
}