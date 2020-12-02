package com.octaneee.workoutmaker.data.model.relation

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.octaneee.workoutmaker.data.model.entity.Macrocycle
import com.octaneee.workoutmaker.data.model.entity.User
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserAndMacrocycle(
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "macrocycleId",
        entity = Macrocycle::class,
        entityColumn = "macrocycleId"
    )
    val macrocycleWithMesocycles: MacrocycleWithMesocycles?
) : Parcelable
