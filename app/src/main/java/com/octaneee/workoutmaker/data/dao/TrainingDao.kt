package com.octaneee.workoutmaker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.model.entity.Training
import com.octaneee.workoutmaker.model.relations.TrainingWithExercises

@Dao
interface TrainingDao : BaseDao<Training> {

    @Query("SELECT * FROM training_table WHERE microcycleIdFk = :microcycleId")
    fun getTrainingByMicrocycleId(microcycleId: Long): LiveData<List<Training>>

    @Query("SELECT * FROM training_table WHERE trainingId = :trainingId")
    fun getTraining(trainingId: Long): LiveData<Training>

    @Transaction
    @Query("SELECT * FROM training_table WHERE trainingId = :trainingId")
    fun getTrainingWithExercises(trainingId: Long): LiveData<TrainingWithExercises>
}