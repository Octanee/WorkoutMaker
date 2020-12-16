package com.octaneee.workoutmaker.logic.repository.crossref

import com.octaneee.workoutmaker.data.dao.crossref.ExerciseMuscleCrossRefDao
import com.octaneee.workoutmaker.data.model.entity.crossref.ExerciseMuscleCrossRef
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class ExerciseMuscleCrossRefRepository(private val dao: ExerciseMuscleCrossRefDao) :
    BaseRepository<ExerciseMuscleCrossRef>(dao) {

}