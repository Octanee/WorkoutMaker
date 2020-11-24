package com.octaneee.workoutmaker.logic.repository

import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.dao.ExerciseTypeDao
import com.octaneee.workoutmaker.data.model.entity.ExerciseType
import com.octaneee.workoutmaker.data.model.relation.ExerciseAndExerciseType
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class ExerciseTypeRepository(private val dao: ExerciseTypeDao) : BaseRepository<ExerciseType>(dao) {

     fun getExerciseAndExerciseType(exerciseId: Long): LiveData<ExerciseAndExerciseType> {
        return dao.getExerciseAndExerciseType(exerciseId)
    }
}