package com.octaneee.workoutmaker.logic.repository

import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.dao.MicrocycleDao
import com.octaneee.workoutmaker.data.model.entity.Microcycle
import com.octaneee.workoutmaker.data.model.relation.MicrocycleWithTrainings
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class MicrocycleRepository(private val dao: MicrocycleDao) : BaseRepository<Microcycle>(dao) {

    fun getMicrocycleWithTrainings(microcycleId: Long): LiveData<MicrocycleWithTrainings> {
        return dao.getMicrocycleWithTrainings(microcycleId)
    }
}