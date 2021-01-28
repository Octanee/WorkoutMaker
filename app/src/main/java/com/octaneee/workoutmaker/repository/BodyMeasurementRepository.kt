package com.octaneee.workoutmaker.repository

import com.octaneee.workoutmaker.data.dao.BodyMeasurementDao
import com.octaneee.workoutmaker.model.entity.BodyMeasurement
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class BodyMeasurementRepository @Inject constructor(private val dao: BodyMeasurementDao) :
    BaseRepository<BodyMeasurement>(dao) {
}