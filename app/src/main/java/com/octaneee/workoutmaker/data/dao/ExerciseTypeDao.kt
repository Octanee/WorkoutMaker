package com.octaneee.workoutmaker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.data.model.entity.ExerciseType
import com.octaneee.workoutmaker.data.model.relation.ExerciseAndExerciseType

@Dao
interface ExerciseTypeDao : BaseDao<ExerciseType> {

    @Query("SELECT * FROM exercise_table WHERE exerciseId = :exerciseId")
    fun getExerciseAndExerciseType(exerciseId: Long): LiveData<ExerciseAndExerciseType>

}