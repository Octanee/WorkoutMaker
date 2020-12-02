package com.octaneee.workoutmaker.data.model.relation

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.octaneee.workoutmaker.data.model.entity.Set
import com.octaneee.workoutmaker.data.model.entity.Training
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TrainingWithSets(
    @Embedded
    val training: Training,
    @Relation(
        parentColumn = "trainingId",
        entity = Set::class,
        entityColumn = "trainingId"
    )
    val sets: List<SetTypeAndSet>?
) : Parcelable
