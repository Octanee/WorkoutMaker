package com.octaneee.workoutmaker.model.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.octaneee.workoutmaker.model.entity.*
import com.octaneee.workoutmaker.model.entity.crossref.ExerciseMuscleCrossRef
import com.octaneee.workoutmaker.model.entity.crossref.ExerciseNoteCrossRef

data class FullExercise(
    @Embedded
    val exercise: Exercise,
    @Relation(
        parentColumn = "exerciseTypeIdFk",
        entityColumn = "exerciseTypeId"
    )
    val exerciseType: ExerciseType?,
    @Relation(
        parentColumn = "equipmentIdFk",
        entityColumn = "equipmentId"
    )
    val equipment: Equipment?,
    @Relation(
        parentColumn = "muscleIdFk",
        entityColumn = "muscleId"
    )
    val primaryMuscle: Muscle?,
    @Relation(
        parentColumn = "exerciseId",
        entityColumn = "muscleId",
        associateBy = Junction(ExerciseMuscleCrossRef::class)
    )
    val additionalMuscles: List<Muscle>,
    @Relation(
        parentColumn = "exerciseId",
        entityColumn = "noteId",
        associateBy = Junction(ExerciseNoteCrossRef::class)
    )
    val notes: List<Note>
)