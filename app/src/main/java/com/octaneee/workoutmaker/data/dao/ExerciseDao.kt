package com.octaneee.workoutmaker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.data.model.entity.Exercise
import com.octaneee.workoutmaker.data.model.relation.ExerciseHolder
import com.octaneee.workoutmaker.data.model.relation.WholeExercise

@Dao
interface ExerciseDao : BaseDao<Exercise> {

    @Query("SELECT * FROM exercise_table WHERE exerciseId = :exerciseId")
    suspend fun getExercise(exerciseId: Long): Exercise

    @Query("SELECT * FROM exercise_table WHERE exerciseId = :exerciseId")
    fun getWholeExercise(exerciseId: Long): LiveData<WholeExercise>

    @Query("SELECT * FROM exercise_table ORDER BY name")
    fun getExerciseHolderList(): LiveData<List<ExerciseHolder>>

    @Query("SELECT * FROM exercise_table WHERE equipmentId = :equipmentId ORDER BY name")
    fun getExerciseHolderListByEquipment(equipmentId: Long): LiveData<List<ExerciseHolder>>

    @Query("SELECT * FROM exercise_table WHERE exerciseTypeId = :exerciseTypeId ORDER BY name")
    fun getExerciseHolderListByExerciseType(exerciseTypeId: Long): LiveData<List<ExerciseHolder>>

    @Query("SELECT * FROM exercise_table WHERE muscleId = :muscleId ORDER BY name")
    fun getExerciseHolderListByMuscle(muscleId: Long): LiveData<List<ExerciseHolder>>
}