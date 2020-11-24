package com.octaneee.workoutmaker.logic.repository

import com.octaneee.workoutmaker.data.dao.TrainingDao
import com.octaneee.workoutmaker.data.model.entity.Training
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class TrainingRepository(private val dao: TrainingDao) : BaseRepository<Training>(dao) {
}