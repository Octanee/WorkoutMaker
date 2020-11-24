package com.octaneee.workoutmaker.logic.repository

import com.octaneee.workoutmaker.data.dao.MesocycleTypeDao
import com.octaneee.workoutmaker.data.model.entity.MesocycleType
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class MesocycleTypeRepository(private val dao: MesocycleTypeDao) :
    BaseRepository<MesocycleType>(dao) {
}