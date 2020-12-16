package com.octaneee.workoutmaker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.data.model.entity.Training
import com.octaneee.workoutmaker.data.model.relation.TrainingWithSetAndExercises

@Dao
interface TrainingDao : BaseDao<Training> {

    @Query("SELECT * FROM training_table")
    fun getTrainingWithSetAndExercises(): LiveData<List<TrainingWithSetAndExercises>>
}