package com.octaneee.workoutmaker.logic.repository.crossref

import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.dao.crossref.ExerciseMuscleCrossRefDao
import com.octaneee.workoutmaker.data.model.entity.crossref.ExerciseMuscleCrossRef
import com.octaneee.workoutmaker.data.model.relation.ExerciseWithMuscles
import com.octaneee.workoutmaker.data.model.relation.MuscleWithExercises
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class ExerciseMuscleCrossRefRepository(private val dao: ExerciseMuscleCrossRefDao) :
    BaseRepository<ExerciseMuscleCrossRef>(dao) {

    fun getMuscleWithExercises(): LiveData<List<MuscleWithExercises>> {
        return dao.getMuscleWithExercises()
    }

    fun getMuscleWithExercises(muscleId: Long): LiveData<MuscleWithExercises> {
        return dao.getMuscleWithExercises(muscleId)
    }

    fun getExerciseWithMuscles(): LiveData<List<ExerciseWithMuscles>> {
        return dao.getExerciseWithMuscles()
    }

    fun getExerciseWithMuscles(exerciseId: Long): LiveData<ExerciseWithMuscles> {
        return dao.getExerciseWithMuscles(exerciseId)
    }
}