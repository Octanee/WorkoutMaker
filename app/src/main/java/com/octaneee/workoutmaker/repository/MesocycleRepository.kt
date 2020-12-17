package com.octaneee.workoutmaker.repository

import com.octaneee.workoutmaker.data.dao.MesocycleDao
import com.octaneee.workoutmaker.data.model.entity.Mesocycle
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class MesocycleRepository @Inject constructor(private val dao: MesocycleDao) :
    BaseRepository<Mesocycle>(dao) {
}