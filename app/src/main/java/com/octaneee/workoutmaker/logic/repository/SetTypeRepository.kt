package com.octaneee.workoutmaker.logic.repository

import com.octaneee.workoutmaker.data.dao.SetTypeDao
import com.octaneee.workoutmaker.data.model.entity.SetType
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class SetTypeRepository(private val dao: SetTypeDao) : BaseRepository<SetType>(dao) {
}