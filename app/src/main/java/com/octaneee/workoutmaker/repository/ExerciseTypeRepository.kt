package com.octaneee.workoutmaker.repository

import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.dao.ExerciseTypeDao
import com.octaneee.workoutmaker.data.model.entity.ExerciseType
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class ExerciseTypeRepository @Inject constructor(private val dao: ExerciseTypeDao) :
    BaseRepository<ExerciseType>(dao) {

    fun getExerciseTypeList(): LiveData<List<ExerciseType>> {
        return dao.getExerciseTypeList()
    }
}