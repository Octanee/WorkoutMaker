package com.octaneee.workoutmaker.repository

import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.dao.MesocycleTypeDao
import com.octaneee.workoutmaker.model.entity.MesocycleType
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class MesocycleTypeRepository @Inject constructor(private val dao: MesocycleTypeDao) :
    BaseRepository<MesocycleType>(dao) {

    fun getMesocycleTypes(): LiveData<List<MesocycleType>> {
        return dao.getMesocycleTypes()
    }
}