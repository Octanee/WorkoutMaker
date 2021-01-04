package com.octaneee.workoutmaker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.model.entity.Training

@Dao
interface TrainingDao : BaseDao<Training> {

    @Query("SELECT * FROM training_table WHERE microcycleIdFk = :microcycleId")
    fun getTrainingByMicrocycleId(microcycleId: Long): LiveData<List<Training>>

    @Query("SELECT * FROM training_table WHERE trainingId = :trainingId")
    fun getTraining(trainingId: Long): LiveData<Training>

}