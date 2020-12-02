package com.octaneee.workoutmaker.logic.repository

import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.dao.MacrocycleDao
import com.octaneee.workoutmaker.data.model.entity.Macrocycle
import com.octaneee.workoutmaker.data.model.relation.MacrocycleWithMesocycles
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class MacrocycleRepository(private val dao: MacrocycleDao) : BaseRepository<Macrocycle>(dao) {

    fun getFullMacrocycle(macrocycleId: Long): LiveData<MacrocycleWithMesocycles> {
        return dao.getFullMacrocycle(macrocycleId)
    }

    fun getMacrocycleById(macrocycleId: Long): Macrocycle {
        return dao.getMacrocycleById(macrocycleId)
    }
}