package com.octaneee.workoutmaker.data.model.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.octaneee.workoutmaker.data.model.entity.Exercise
import com.octaneee.workoutmaker.data.model.entity.crossref.ExerciseMuscleCrossRef
import com.octaneee.workoutmaker.data.model.entity.Muscle

data class ExerciseWithMuscles(
    @Embedded
    val exercise: Exercise,
    @Relation(
        parentColumn = "exerciseId",
        entity = Muscle::class,
        entityColumn = "muscleId",
        associateBy = Junction(ExerciseMuscleCrossRef::class)
    )
    val muscles: List<Muscle>
) {
}