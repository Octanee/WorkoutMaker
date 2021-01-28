package com.octaneee.workoutmaker.data.dao

import androidx.room.Dao
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.model.entity.SeriesType

@Dao
interface SetTypeDao : BaseDao<SeriesType> {
}