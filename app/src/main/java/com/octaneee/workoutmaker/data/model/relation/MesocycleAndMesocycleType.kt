package com.octaneee.workoutmaker.data.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.octaneee.workoutmaker.data.model.entity.Mesocycle
import com.octaneee.workoutmaker.data.model.entity.MesocycleType

data class MesocycleAndMesocycleType(
    @Embedded
    val mesocycle: Mesocycle,
    @Relation(
        parentColumn = "mesocycleTypeId",
        entity = MesocycleType::class,
        entityColumn = "mesocycleTypeId"
    )
    val mesocycleType: MesocycleType
)
