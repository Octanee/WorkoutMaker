package com.octaneee.workoutmaker.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SimpleExercise(val exerciseId: Long, val exerciseName: String) : Parcelable
