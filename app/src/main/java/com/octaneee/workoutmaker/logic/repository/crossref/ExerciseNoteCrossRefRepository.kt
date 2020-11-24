package com.octaneee.workoutmaker.logic.repository.crossref

import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.dao.crossref.ExerciseNoteCrossRefDao
import com.octaneee.workoutmaker.data.model.entity.crossref.ExerciseNoteCrossRef
import com.octaneee.workoutmaker.data.model.relation.ExerciseWithNotes
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class ExerciseNoteCrossRefRepository(private val dao: ExerciseNoteCrossRefDao) :
    BaseRepository<ExerciseNoteCrossRef>(dao) {

    fun getExerciseWithNotes(): LiveData<List<ExerciseWithNotes>> {
        return dao.getExerciseWithNotes()
    }
}