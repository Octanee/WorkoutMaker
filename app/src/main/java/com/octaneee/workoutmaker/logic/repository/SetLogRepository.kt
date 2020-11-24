package com.octaneee.workoutmaker.logic.repository

import com.octaneee.workoutmaker.data.dao.SetLogDao
import com.octaneee.workoutmaker.data.model.entity.SetLog
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class SetLogRepository(private val dao: SetLogDao) : BaseRepository<SetLog>(dao) {
}