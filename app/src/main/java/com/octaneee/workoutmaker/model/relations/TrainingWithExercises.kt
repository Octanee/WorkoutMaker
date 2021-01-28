package com.octaneee.workoutmaker.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.octaneee.workoutmaker.model.entity.Training
import com.octaneee.workoutmaker.model.entity.crossref.TrainingExerciseCrossRef

data class TrainingWithExercises(
    @Embedded
    val training: Training,
    @Relation(
        parentColumn = "trainingId",
        entityColumn = "trainingIdFk",
        entity = TrainingExerciseCrossRef::class
    )
    val exerciseWithSeries: List<ExerciseWithSeries>
)
