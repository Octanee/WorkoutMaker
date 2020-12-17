package com.octaneee.workoutmaker.repository

import com.octaneee.workoutmaker.data.dao.MeasurementDao
import com.octaneee.workoutmaker.data.model.entity.Measurement
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class MeasurementRepository @Inject constructor(private val dao: MeasurementDao) :
    BaseRepository<Measurement>(dao) {
}