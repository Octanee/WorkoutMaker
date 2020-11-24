package com.octaneee.workoutmaker.logic.repository

import com.octaneee.workoutmaker.data.dao.SetDao
import com.octaneee.workoutmaker.data.model.entity.Set
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class SetRepository(private val dao: SetDao) : BaseRepository<Set>(dao) {
}