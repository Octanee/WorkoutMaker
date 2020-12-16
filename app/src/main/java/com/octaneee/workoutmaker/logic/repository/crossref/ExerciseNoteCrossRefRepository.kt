package com.octaneee.workoutmaker.logic.repository.crossref

import com.octaneee.workoutmaker.data.dao.crossref.ExerciseNoteCrossRefDao
import com.octaneee.workoutmaker.data.model.entity.crossref.ExerciseNoteCrossRef
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class ExerciseNoteCrossRefRepository(private val dao: ExerciseNoteCrossRefDao) :
    BaseRepository<ExerciseNoteCrossRef>(dao) {
}