package com.octaneee.workoutmaker.data.dao

import androidx.room.Dao
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.model.entity.Measurement

@Dao
interface MeasurementDao : BaseDao<Measurement> {
}