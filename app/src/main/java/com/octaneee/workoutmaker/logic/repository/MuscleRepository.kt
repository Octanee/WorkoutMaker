package com.octaneee.workoutmaker.logic.repository

import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.dao.MuscleDao
import com.octaneee.workoutmaker.data.model.entity.Muscle
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class MuscleRepository(private val dao: MuscleDao) : BaseRepository<Muscle>(dao) {

    fun getMuscleList(): LiveData<List<Muscle>> {
        return dao.getMuscleList()
    }
}