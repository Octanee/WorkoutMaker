package com.octaneee.workoutmaker.repository

import com.octaneee.workoutmaker.data.dao.TrainingDao
import com.octaneee.workoutmaker.data.model.entity.Training
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class TrainingRepository @Inject constructor(private val dao: TrainingDao) :
    BaseRepository<Training>(dao) {

}