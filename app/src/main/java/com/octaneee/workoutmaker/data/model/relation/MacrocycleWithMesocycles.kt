package com.octaneee.workoutmaker.data.model.relation

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.octaneee.workoutmaker.data.model.entity.Macrocycle
import com.octaneee.workoutmaker.data.model.entity.Mesocycle
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MacrocycleWithMesocycles(
    @Embedded
    val macrocycle: Macrocycle,
    @Relation(
        parentColumn = "macrocycleId",
        entity = Mesocycle::class,
        entityColumn = "macrocycleId"
    )
    val mesocycles: List<MesocycleAndMesocycleTypeWithMicrocycles>? = null
) : Parcelable

