package com.octaneee.workoutmaker.repository

import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.dao.MacrocycleDao
import com.octaneee.workoutmaker.model.entity.Macrocycle
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class MacrocycleRepository @Inject constructor(private val dao: MacrocycleDao) :
    BaseRepository<Macrocycle>(dao) {

    fun getMacrocycle(macrocycleId: Long): LiveData<Macrocycle> = dao.getMacrocycle(macrocycleId)

    fun getMacrocycles(): LiveData<List<Macrocycle>> = dao.getMacrocycles()

}