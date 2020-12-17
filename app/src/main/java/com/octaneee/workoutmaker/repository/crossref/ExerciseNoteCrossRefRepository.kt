package com.octaneee.workoutmaker.repository.crossref

import com.octaneee.workoutmaker.data.dao.crossref.ExerciseNoteCrossRefDao
import com.octaneee.workoutmaker.data.model.entity.crossref.ExerciseNoteCrossRef
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class ExerciseNoteCrossRefRepository @Inject constructor(private val dao: ExerciseNoteCrossRefDao) :
    BaseRepository<ExerciseNoteCrossRef>(dao) {
}