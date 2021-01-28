package com.octaneee.workoutmaker.repository

import com.octaneee.workoutmaker.data.dao.SetDao
import com.octaneee.workoutmaker.model.entity.Series
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class SetRepository @Inject constructor(private val dao: SetDao) : BaseRepository<Series>(dao) {

}