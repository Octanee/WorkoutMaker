package com.octaneee.workoutmaker.data.dao

import androidx.room.Dao
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.model.entity.WeightMeasurement

@Dao
interface WeightMeasurementDao : BaseDao<WeightMeasurement> {}