package com.octaneee.workoutmaker.model


data class WorkoutExercise(
    val trainingExerciseCrossRefId: Long,
    val exercise: SimpleExercise,
    val series: List<WorkoutSeries>,
    var isCompleted: Boolean = false
)
