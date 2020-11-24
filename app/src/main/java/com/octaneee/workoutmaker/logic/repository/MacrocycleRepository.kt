package com.octaneee.workoutmaker.logic.repository

import com.octaneee.workoutmaker.data.dao.MacrocycleDao
import com.octaneee.workoutmaker.data.model.entity.Macrocycle
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class MacrocycleRepository(private val dao: MacrocycleDao) : BaseRepository<Macrocycle>(dao) {

    fun getMacrocycleById(macrocycleId: Long): Macrocycle {
        return dao.getMacrocycleById(macrocycleId)
    }
}