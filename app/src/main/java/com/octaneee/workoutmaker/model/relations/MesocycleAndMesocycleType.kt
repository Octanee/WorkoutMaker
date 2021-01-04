package com.octaneee.workoutmaker.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.octaneee.workoutmaker.model.entity.Mesocycle
import com.octaneee.workoutmaker.model.entity.MesocycleType

data class MesocycleAndMesocycleType(
    @Embedded
    val mesocycle: Mesocycle,
    @Relation(
        parentColumn = "mesocycleTypeIdFk",
        entityColumn = "mesocycleTypeId"
    )
    val mesocycleType: MesocycleType
)
