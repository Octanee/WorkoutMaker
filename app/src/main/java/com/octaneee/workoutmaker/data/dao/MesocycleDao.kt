package com.octaneee.workoutmaker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.model.entity.Mesocycle
import com.octaneee.workoutmaker.model.relations.MesocycleAndMesocycleType

@Dao
interface MesocycleDao : BaseDao<Mesocycle> {

    @Query("SELECT * FROM mesocycle_table WHERE macrocycleIdFk = :macrocycleId")
    fun getMesocyclesByMacrocycleId(macrocycleId: Long): LiveData<List<Mesocycle>>

    @Query("SELECT * FROM mesocycle_table WHERE mesocycleId = :mesocycleId")
    fun getMesocycle(mesocycleId: Long): LiveData<Mesocycle>

    @Transaction
    @Query("SELECT * FROM mesocycle_table WHERE macrocycleIdFk = :macrocycleId")
    fun getMesocycleAndMesocycleTypeListByMacrocycleId(macrocycleId: Long): LiveData<List<MesocycleAndMesocycleType>>
}