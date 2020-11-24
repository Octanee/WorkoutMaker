package com.octaneee.workoutmaker.data.dao.crossref

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.data.model.entity.crossref.ExerciseNoteCrossRef
import com.octaneee.workoutmaker.data.model.relation.ExerciseWithNotes

@Dao
interface ExerciseNoteCrossRefDao : BaseDao<ExerciseNoteCrossRef> {

    @Query("SELECT * FROM exercise_table ")
    fun getExerciseWithNotes(): LiveData<List<ExerciseWithNotes>>
}