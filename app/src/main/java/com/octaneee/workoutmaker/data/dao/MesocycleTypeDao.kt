package com.octaneee.workoutmaker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.model.entity.MesocycleType

@Dao
interface MesocycleTypeDao : BaseDao<MesocycleType> {

    @Query("SELECT * FROM mesocycle_type_table")
    fun getMesocycleTypes(): LiveData<List<MesocycleType>>

}