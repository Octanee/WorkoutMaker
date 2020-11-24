package com.octaneee.workoutmaker.logic.repository

import com.octaneee.workoutmaker.data.dao.EquipmentDao
import com.octaneee.workoutmaker.data.model.entity.Equipment
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class EquipmentRepository(private val dao: EquipmentDao) : BaseRepository<Equipment>(dao) {
}