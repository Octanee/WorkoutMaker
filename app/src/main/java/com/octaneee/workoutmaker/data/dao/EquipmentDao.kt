package com.octaneee.workoutmaker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.model.entity.Equipment

@Dao
interface EquipmentDao : BaseDao<Equipment> {

    @Query("SELECT * FROM equipment_table ORDER BY equipmentId")
    fun getEquipmentList(): LiveData<List<Equipment>>

}