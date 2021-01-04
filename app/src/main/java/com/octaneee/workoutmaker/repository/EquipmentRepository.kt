package com.octaneee.workoutmaker.repository

import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.dao.EquipmentDao
import com.octaneee.workoutmaker.model.entity.Equipment
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class EquipmentRepository @Inject constructor(private val dao: EquipmentDao) :
    BaseRepository<Equipment>(dao) {

    fun getEquipmentList(): LiveData<List<Equipment>> = dao.getEquipmentList()

}