package com.octaneee.workoutmaker.data.model.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.octaneee.workoutmaker.data.model.entity.*
import com.octaneee.workoutmaker.data.model.entity.crossref.ExerciseMuscleCrossRef
import com.octaneee.workoutmaker.data.model.entity.crossref.ExerciseNoteCrossRef

data class WholeExercise(
    @Embedded
    val exercise: Exercise,
    @Relation(
        parentColumn = "exerciseTypeId",
        entity = ExerciseType::class,
        entityColumn = "exerciseTypeId",
    )
    val exerciseType: ExerciseType?,
    @Relation(
        parentColumn = "equipmentId",
        entity = Equipment::class,
        entityColumn = "equipmentId",
    )
    val equipment: Equipment?,
    @Relation(
        parentColumn = "exerciseId",
        entity = Note::class,
        entityColumn = "noteId",
        associateBy = Junction(ExerciseNoteCrossRef::class)
    )
    val notes: List<Note>?,
    @Relation(
        parentColumn = "exerciseId",
        entity = Muscle::class,
        entityColumn = "muscleId",
        associateBy = Junction(ExerciseMuscleCrossRef::class)
    )
    val muscles: List<Muscle>?
) {
}