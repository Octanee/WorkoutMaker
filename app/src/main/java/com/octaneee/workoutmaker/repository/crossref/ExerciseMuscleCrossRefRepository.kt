package com.octaneee.workoutmaker.repository.crossref

import com.octaneee.workoutmaker.data.dao.crossref.ExerciseMuscleCrossRefDao
import com.octaneee.workoutmaker.data.model.entity.crossref.ExerciseMuscleCrossRef
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class ExerciseMuscleCrossRefRepository @Inject constructor(private val dao: ExerciseMuscleCrossRefDao) :
    BaseRepository<ExerciseMuscleCrossRef>(dao) {

}