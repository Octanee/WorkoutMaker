package com.octaneee.workoutmaker.data.dao.crossref

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.model.entity.crossref.TrainingExerciseCrossRef
import com.octaneee.workoutmaker.model.relations.ExerciseWithSeries

@Dao
interface TrainingExerciseCrossRefDao : BaseDao<TrainingExerciseCrossRef> {

    @Transaction
    @Query("SELECT * FROM training_exercise_cross_ref WHERE trainingIdFk = :trainingId")
    fun getExerciseWithSetsByTrainingId(trainingId: Long): LiveData<List<ExerciseWithSeries>>

}