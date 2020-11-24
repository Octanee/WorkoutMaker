package com.octaneee.workoutmaker.data.dao.crossref

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.data.model.entity.crossref.ExerciseMuscleCrossRef
import com.octaneee.workoutmaker.data.model.relation.ExerciseWithMuscles
import com.octaneee.workoutmaker.data.model.relation.MuscleWithExercises

@Dao
interface ExerciseMuscleCrossRefDao : BaseDao<ExerciseMuscleCrossRef> {

    @Query("SELECT * FROM muscle_table ")
    fun getMuscleWithExercises(): LiveData<List<MuscleWithExercises>>

    @Query("SELECT * FROM muscle_table WHERE muscleId = :muscleId")
    fun getMuscleWithExercises(muscleId: Long): LiveData<MuscleWithExercises>

    @Query("SELECT * FROM exercise_table ")
    fun getExerciseWithMuscles(): LiveData<List<ExerciseWithMuscles>>

    @Query("SELECT * FROM exercise_table WHERE exerciseId = :exerciseId")
    fun getExerciseWithMuscles(exerciseId: Long): LiveData<ExerciseWithMuscles>


}