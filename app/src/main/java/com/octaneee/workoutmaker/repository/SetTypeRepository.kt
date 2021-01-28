package com.octaneee.workoutmaker.repository

import com.octaneee.workoutmaker.data.dao.SetTypeDao
import com.octaneee.workoutmaker.model.entity.SeriesType
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class SetTypeRepository @Inject constructor(private val dao: SetTypeDao) :
    BaseRepository<SeriesType>(dao) {
}