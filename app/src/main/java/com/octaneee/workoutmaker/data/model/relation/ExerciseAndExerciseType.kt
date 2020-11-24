package com.octaneee.workoutmaker.data.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.octaneee.workoutmaker.data.model.entity.Equipment
import com.octaneee.workoutmaker.data.model.entity.Exercise
import com.octaneee.workoutmaker.data.model.entity.ExerciseType

data class ExerciseAndExerciseType(
    @Embedded
    val exercise: Exercise,
    @Relation(
        parentColumn = "exerciseTypeId",
        entity = ExerciseType::class,
        entityColumn = "exerciseTypeId",
    )
    val exerciseType: ExerciseType,


) {
}