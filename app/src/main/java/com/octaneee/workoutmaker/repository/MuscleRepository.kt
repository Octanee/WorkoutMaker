package com.octaneee.workoutmaker.repository

import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.dao.MuscleDao
import com.octaneee.workoutmaker.model.entity.Muscle
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class MuscleRepository @Inject constructor(private val dao: MuscleDao) :
    BaseRepository<Muscle>(dao) {

    fun getMuscleList(): LiveData<List<Muscle>> = dao.getMuscleList()

}