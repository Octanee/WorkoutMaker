package com.octaneee.workoutmaker.repository

import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.dao.MacrocycleDao
import com.octaneee.workoutmaker.data.model.entity.Macrocycle
import com.octaneee.workoutmaker.data.model.relation.MacrocycleWithMesocycles
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class MacrocycleRepository @Inject constructor(private val dao: MacrocycleDao) :
    BaseRepository<Macrocycle>(dao) {


    fun getMacrocycles(): LiveData<List<Macrocycle>> {
        return dao.getMacrocycles()
    }

    fun getMacrocycleById(macrocycleId: Long): LiveData<MacrocycleWithMesocycles> {
        return dao.getMacrocycleById(macrocycleId)
    }
}