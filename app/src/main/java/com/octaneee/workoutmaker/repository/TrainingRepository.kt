package com.octaneee.workoutmaker.repository

import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.dao.TrainingDao
import com.octaneee.workoutmaker.model.entity.Training
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class TrainingRepository @Inject constructor(private val dao: TrainingDao) :
    BaseRepository<Training>(dao) {

    fun getTrainingByMicrocycleId(microcycleId: Long): LiveData<List<Training>> =
        dao.getTrainingByMicrocycleId(microcycleId)

    fun getTraining(trainingId: Long): LiveData<Training> = dao.getTraining(trainingId)
}