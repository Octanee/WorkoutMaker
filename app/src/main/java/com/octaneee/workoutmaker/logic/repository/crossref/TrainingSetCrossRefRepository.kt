package com.octaneee.workoutmaker.logic.repository.crossref

import com.octaneee.workoutmaker.data.dao.crossref.TrainingSetCrossRefDao
import com.octaneee.workoutmaker.data.model.entity.crossref.TrainingSetCrossRef
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class TrainingSetCrossRefRepository(private val dao: TrainingSetCrossRefDao) :
    BaseRepository<TrainingSetCrossRef>(dao) {
}