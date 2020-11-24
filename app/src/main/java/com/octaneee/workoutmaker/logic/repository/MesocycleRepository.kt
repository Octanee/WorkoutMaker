package com.octaneee.workoutmaker.logic.repository

import com.octaneee.workoutmaker.data.dao.MesocycleDao
import com.octaneee.workoutmaker.data.model.entity.Mesocycle
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class MesocycleRepository(private val dao: MesocycleDao) : BaseRepository<Mesocycle>(dao) {
}