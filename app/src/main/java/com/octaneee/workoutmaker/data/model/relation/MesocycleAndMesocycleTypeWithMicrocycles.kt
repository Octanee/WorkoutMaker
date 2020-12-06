package com.octaneee.workoutmaker.data.model.relation

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.octaneee.workoutmaker.data.model.entity.Mesocycle
import com.octaneee.workoutmaker.data.model.entity.MesocycleType
import com.octaneee.workoutmaker.data.model.entity.Microcycle
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MesocycleAndMesocycleTypeWithMicrocycles(
    @Embedded
    val mesocycle: Mesocycle,
    @Relation(
        parentColumn = "mesocycleTypeId",
        entity = MesocycleType::class,
        entityColumn = "mesocycleTypeId"
    )
    var mesocycleType: MesocycleType,
    @Relation(
        parentColumn = "mesocycleId",
        entity = Microcycle::class,
        entityColumn = "mesocycleId"
    )
    var microcycles: List<MicrocycleWithTrainings> = listOf()
) : Parcelable