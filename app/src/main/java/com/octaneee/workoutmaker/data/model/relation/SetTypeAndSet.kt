package com.octaneee.workoutmaker.data.model.relation

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.octaneee.workoutmaker.data.model.entity.Set
import com.octaneee.workoutmaker.data.model.entity.SetType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SetTypeAndSet(
    @Embedded
    val set: Set,
    @Relation(
        parentColumn = "setTypeId",
        entity = SetType::class,
        entityColumn = "setTypeId"
    )
    val setType: SetType
) : Parcelable
