package com.octaneee.workoutmaker.logic.repository

import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.dao.MesocycleTypeDao
import com.octaneee.workoutmaker.data.model.entity.MesocycleType
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class MesocycleTypeRepository(private val dao: MesocycleTypeDao) :
    BaseRepository<MesocycleType>(dao) {

    fun getMesocycleTypeList(): LiveData<List<MesocycleType>> {
        return dao.getMesocycleTypeList()
    }
}