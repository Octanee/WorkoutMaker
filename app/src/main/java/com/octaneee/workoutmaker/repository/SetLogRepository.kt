package com.octaneee.workoutmaker.repository

import com.octaneee.workoutmaker.data.dao.SetLogDao
import com.octaneee.workoutmaker.data.model.entity.SetLog
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class SetLogRepository @Inject constructor(private val dao: SetLogDao) :
    BaseRepository<SetLog>(dao) {
}