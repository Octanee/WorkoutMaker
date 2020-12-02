package com.octaneee.workoutmaker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.data.model.entity.ExerciseType

@Dao
interface ExerciseTypeDao : BaseDao<ExerciseType> {

    @Query("SELECT * FROM exercise_type_table")
    fun getExerciseTypeList(): LiveData<List<ExerciseType>>
}