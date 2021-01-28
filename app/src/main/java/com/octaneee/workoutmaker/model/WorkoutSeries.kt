package com.octaneee.workoutmaker.model

import com.octaneee.workoutmaker.model.entity.Series

data class WorkoutSeries(val series: Series, var isCompleted: Boolean = false)
