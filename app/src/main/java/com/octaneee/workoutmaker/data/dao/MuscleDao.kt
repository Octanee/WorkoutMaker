package com.octaneee.workoutmaker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.data.model.entity.Muscle

@Dao
interface MuscleDao : BaseDao<Muscle> {

    @Query("SELECT * FROM muscle_table ORDER BY muscleId")
    fun getMuscleList(): LiveData<List<Muscle>>

}