package com.octaneee.workoutmaker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.model.entity.ExerciseType

@Dao
interface ExerciseTypeDao : BaseDao<ExerciseType> {

    @Query("SELECT * FROM exercise_type_table ORDER BY exerciseTypeId")
    fun getExerciseTypeList(): LiveData<List<ExerciseType>>
}