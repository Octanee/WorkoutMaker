package com.octaneee.workoutmaker.data.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.octaneee.workoutmaker.data.model.entity.Macrocycle
import com.octaneee.workoutmaker.data.model.entity.User

data class UserAndMacrocycleAndMesocycleAndMesocycleType(
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "macrocycleId",
        entity = Macrocycle::class,
        entityColumn = "macrocycleId"
    )
    val macrocycle: Macrocycle?
)
