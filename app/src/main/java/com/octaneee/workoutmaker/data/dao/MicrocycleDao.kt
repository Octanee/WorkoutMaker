package com.octaneee.workoutmaker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.data.model.entity.Microcycle
import com.octaneee.workoutmaker.data.model.relation.MicrocycleWithTrainings

@Dao
interface MicrocycleDao : BaseDao<Microcycle> {

    @Query("SELECT * FROM microcycle_table WHERE microcycleId = :microcycleId")
    fun getMicrocycleWithTrainings(microcycleId: Long): LiveData<MicrocycleWithTrainings>
}
