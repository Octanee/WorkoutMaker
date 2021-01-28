package com.octaneee.workoutmaker.repository

import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.dao.MicrocycleDao
import com.octaneee.workoutmaker.model.entity.Microcycle
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class MicrocycleRepository @Inject constructor(private val dao: MicrocycleDao) :
    BaseRepository<Microcycle>(dao) {

    fun getMicrocycleListByMesocycleId(mesocycleId: Long): LiveData<List<Microcycle>> =
        dao.getMicrocycleListByMesocycleId(mesocycleId)

    fun getMicrocycle(microcycleId: Long): LiveData<Microcycle> = dao.getMicrocycle(microcycleId)
    fun getNumberOfDaysByMicrocycleId(microcycleId: Long): LiveData<Int> =
        dao.getNumberOfDaysByMicrocycleId(microcycleId)
}