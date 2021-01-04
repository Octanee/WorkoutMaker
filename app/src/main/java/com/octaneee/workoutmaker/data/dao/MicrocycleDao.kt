package com.octaneee.workoutmaker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.model.entity.Microcycle

@Dao
interface MicrocycleDao : BaseDao<Microcycle> {

    @Query("SELECT * FROM microcycle_table WHERE mesocycleIdFk = :mesocycleId")
    fun getMicrocycleListByMesocycleId(mesocycleId: Long): LiveData<List<Microcycle>>

    @Query("SELECT * FROM microcycle_table WHERE microcycleId = :microcycleId")
    fun getMicrocycle(microcycleId: Long): LiveData<Microcycle>


}
