package com.octaneee.workoutmaker.data.dao

import androidx.room.Dao
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.data.model.entity.Set

@Dao
interface SetDao : BaseDao<Set> {
}