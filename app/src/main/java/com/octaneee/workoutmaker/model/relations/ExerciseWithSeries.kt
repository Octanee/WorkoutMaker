package com.octaneee.workoutmaker.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.octaneee.workoutmaker.model.SimpleExercise
import com.octaneee.workoutmaker.model.entity.Exercise
import com.octaneee.workoutmaker.model.entity.Series
import com.octaneee.workoutmaker.model.entity.crossref.TrainingExerciseCrossRef

data class ExerciseWithSeries(
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
        entity = Series::class,
    )
    val seriesList: List<Series> = listOf()
)
