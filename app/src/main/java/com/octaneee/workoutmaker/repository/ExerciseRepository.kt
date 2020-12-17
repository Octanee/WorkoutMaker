package com.octaneee.workoutmaker.repository

import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.dao.ExerciseDao
import com.octaneee.workoutmaker.data.model.entity.Exercise
import com.octaneee.workoutmaker.data.model.relation.ExerciseHolder
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class ExerciseRepository @Inject constructor(private val dao: ExerciseDao) :
    BaseRepository<Exercise>(dao) {


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