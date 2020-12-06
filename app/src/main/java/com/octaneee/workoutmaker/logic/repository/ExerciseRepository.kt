package com.octaneee.workoutmaker.logic.repository

import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.dao.ExerciseDao
import com.octaneee.workoutmaker.data.model.entity.Exercise
import com.octaneee.workoutmaker.data.model.relation.ExerciseHolder
import com.octaneee.workoutmaker.data.model.relation.WholeExercise
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class ExerciseRepository(private val dao: ExerciseDao) : BaseRepository<Exercise>(dao) {

    suspend fun getExercise(exerciseId: Long): Exercise {
        return dao.getExercise(exerciseId)
    }

    fun getWholeExercise(exerciseId: Long): LiveData<WholeExercise> {
        return dao.getWholeExercise(exerciseId)
    }

    fun getExerciseHolderList(): LiveData<List<ExerciseHolder>> {
        return dao.getExerciseHolderList()
    }

    fun getExerciseHolderListByEquipment(equipmentId: Long): LiveData<List<ExerciseHolder>> {
        return dao.getExerciseHolderListByEquipment(equipmentId)
    }

    fun getExerciseHolderListByMuscle(muscleId: Long): LiveData<List<ExerciseHolder>> {
        return dao.getExerciseHolderListByMuscle(muscleId)
    }

    fun getExerciseHolderListByExerciseType(exerciseTypeId: Long): LiveData<List<ExerciseHolder>> {
        return dao.getExerciseHolderListByEquipment(exerciseTypeId)
    }
}