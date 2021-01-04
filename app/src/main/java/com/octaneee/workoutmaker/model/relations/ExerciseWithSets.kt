package com.octaneee.workoutmaker.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.octaneee.workoutmaker.model.SimpleExercise
import com.octaneee.workoutmaker.model.entity.Exercise
import com.octaneee.workoutmaker.model.entity.Set
import com.octaneee.workoutmaker.model.entity.crossref.TrainingExerciseCrossRef

data class ExerciseWithSets(
    @Embedded
    val trainingExerciseCrossRef: TrainingExerciseCrossRef,
    @Relation(
        parentColumn = "exerciseIdFk",
        entityColumn = "exerciseId",
        entity = Exercise::class,
        projection = ["exerciseId", "exerciseName"]
    )
    val exercise: SimpleExercise,
    @Relation(
        parentColumn = "trainingExerciseCrossRefId",
        entityColumn = "trainingExerciseCrossRefIdFk",
        entity = Set::class,
    )
    val setList: List<Set> = listOf()
)
