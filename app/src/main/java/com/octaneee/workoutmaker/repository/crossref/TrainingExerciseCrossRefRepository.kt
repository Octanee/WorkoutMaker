package com.octaneee.workoutmaker.repository.crossref

import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.dao.crossref.TrainingExerciseCrossRefDao
import com.octaneee.workoutmaker.model.entity.crossref.TrainingExerciseCrossRef
import com.octaneee.workoutmaker.model.relations.ExerciseWithSets
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class TrainingExerciseCrossRefRepository @Inject constructor(private val dao: TrainingExerciseCrossRefDao) :
    BaseRepository<TrainingExerciseCrossRef>(dao) {

    fun getExerciseWithSetsByTrainingId(trainingId: Long): LiveData<List<ExerciseWithSets>> =
        dao.getExerciseWithSetsByTrainingId(trainingId)
}