package com.octaneee.workoutmaker.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.data.model.entity.Macrocycle

@Dao
interface MacrocycleDao : BaseDao<Macrocycle> {

    @Query("SELECT * FROM macrocycle_table WHERE macrocycleId = :macrocycleId")
    fun getMacrocycleById(macrocycleId: Long): Macrocycle
}