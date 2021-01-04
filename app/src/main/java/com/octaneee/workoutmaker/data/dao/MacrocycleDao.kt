package com.octaneee.workoutmaker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.model.entity.Macrocycle

@Dao
interface MacrocycleDao : BaseDao<Macrocycle> {

    @Query("SELECT * FROM macrocycle_table")
    fun getMacrocycles(): LiveData<List<Macrocycle>>

    @Query("SELECT * FROM macrocycle_table WHERE macrocycleId = :macrocycleId")
    fun getMacrocycle(macrocycleId: Long): LiveData<Macrocycle>

}