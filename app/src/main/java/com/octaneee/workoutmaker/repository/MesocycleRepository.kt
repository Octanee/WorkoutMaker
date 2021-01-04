package com.octaneee.workoutmaker.repository

import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.dao.MesocycleDao
import com.octaneee.workoutmaker.model.entity.Mesocycle
import com.octaneee.workoutmaker.model.relations.MesocycleAndMesocycleType
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class MesocycleRepository @Inject constructor(private val dao: MesocycleDao) :
    BaseRepository<Mesocycle>(dao) {

    fun getMesocyclesByMacrocycleId(macrocycleId: Long): LiveData<List<Mesocycle>> =
        dao.getMesocyclesByMacrocycleId(macrocycleId)

    fun getMesocycleAndMesocycleTypeListByMacrocycleId(macrocycleId: Long): LiveData<List<MesocycleAndMesocycleType>> =
        dao.getMesocycleAndMesocycleTypeListByMacrocycleId(macrocycleId)

    fun getMesocycle(mesocycleId: Long): LiveData<Mesocycle> = dao.getMesocycle(mesocycleId)
}