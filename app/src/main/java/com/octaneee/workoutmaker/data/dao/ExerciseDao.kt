package com.octaneee.workoutmaker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.data.model.entity.Exercise
import com.octaneee.workoutmaker.data.model.entity.ExerciseType
import com.octaneee.workoutmaker.data.model.relation.WholeExercise

@Dao
interface ExerciseDao : BaseDao<Exercise> {

    @Query("SELECT * FROM exercise_table WHERE exerciseId = :exerciseId")
    suspend fun getExercise(exerciseId: Long): Exercise

    @Query("SELECT * FROM exercise_table WHERE exerciseId = :exerciseId")
    fun getWholeExercise(exerciseId: Long): LiveData<WholeExercise>
}