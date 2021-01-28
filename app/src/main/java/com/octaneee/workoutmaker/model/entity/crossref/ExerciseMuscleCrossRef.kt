package com.octaneee.workoutmaker.model.entity.crossref

import androidx.room.Entity
import androidx.room.ForeignKey
import com.octaneee.workoutmaker.model.entity.Exercise
import com.octaneee.workoutmaker.model.entity.Muscle
import com.octaneee.workoutmaker.model.entity.base.BaseEntity

@Entity(
    tableName = "exercise_muscle_cross_ref",
    primaryKeys = ["exerciseId", "muscleId"],
    foreignKeys = [
        ForeignKey(
            entity = Exercise::class,
            parentColumns = ["exerciseId"],
            childColumns = ["exerciseId"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = Muscle::class,
            parentColumns = ["muscleId"],
            childColumns = ["muscleId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ExerciseMuscleCrossRef(
    val exerciseId: Long,
    val muscleId: Long
) : BaseEntity