package com.octaneee.workoutmaker.data.model.relation

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.octaneee.workoutmaker.data.model.entity.Microcycle
import com.octaneee.workoutmaker.data.model.entity.Training
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MicrocycleWithTrainings(
    @Embedded
    val microcycle: Microcycle,
    @Relation(
        parentColumn = "microcycleId",
        entity = Training::class,
        entityColumn = "microcycleId"
    )
    val trainings: List<TrainingWithSets>?
) : Parcelable
