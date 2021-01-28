package com.octaneee.workoutmaker.repository

import com.octaneee.workoutmaker.data.dao.WeightMeasurementDao
import com.octaneee.workoutmaker.model.entity.WeightMeasurement
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class WeightMeasurementRepository @Inject constructor(private val dao: WeightMeasurementDao) :
    BaseRepository<WeightMeasurement>(dao) {
}