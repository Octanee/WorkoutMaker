package com.octaneee.workoutmaker.data.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.octaneee.workoutmaker.data.model.entity.Macrocycle

data class MacrocycleAndMesocycleAndMesocycleType(
    @Embedded
    val macrocycle: Macrocycle,
    @Relation(
        parentColumn = "macrocycleId",
        entity = MesocycleAndMesocycleType::class,
        entityColumn = "macrocycleId"
    )
    val mesocycleAndMesocycleType: MesocycleAndMesocycleType
)
