package com.octaneee.workoutmaker.logic.repository

import com.octaneee.workoutmaker.data.dao.MeasurementDao
import com.octaneee.workoutmaker.data.model.entity.Measurement
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class MeasurementRepository(private val dao: MeasurementDao) : BaseRepository<Measurement>(dao) {
}