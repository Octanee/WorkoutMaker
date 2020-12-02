package com.octaneee.workoutmaker.data.model.relation

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.octaneee.workoutmaker.data.model.entity.Exercise
import com.octaneee.workoutmaker.data.model.entity.Muscle
import com.octaneee.workoutmaker.data.model.entity.crossref.ExerciseMuscleCrossRef
import kotlinx.android.parcel.Parcelize

@Parcelize
class MuscleWithExercises(
    @Embedded
    val muscle: Muscle,
    @Relation(
        parentColumn = "muscleId",
        entity = Exercise::class,
        entityColumn = "exerciseId",
        associateBy = Junction(ExerciseMuscleCrossRef::class)
    )
    val exercises: List<Exercise>
) : Parcelable