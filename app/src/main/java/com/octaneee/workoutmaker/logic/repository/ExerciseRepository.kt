package com.octaneee.workoutmaker.logic.repository

import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.dao.ExerciseDao
import com.octaneee.workoutmaker.data.model.entity.Exercise
import com.octaneee.workoutmaker.data.model.relation.WholeExercise
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class ExerciseRepository(private val dao: ExerciseDao) : BaseRepository<Exercise>(dao) {

    suspend fun getExercise(exerciseId: Long): Exercise {
        return dao.getExercise(exerciseId)
    }

    fun getWholeExercise(exerciseId: Long): LiveData<WholeExercise> {
        return dao.getWholeExercise(exerciseId)
    }
}