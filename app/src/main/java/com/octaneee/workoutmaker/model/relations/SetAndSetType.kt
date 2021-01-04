package com.octaneee.workoutmaker.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.octaneee.workoutmaker.model.entity.Set
import com.octaneee.workoutmaker.model.entity.SetType

data class SetAndSetType(
    @Embedded
    val set: Set,
    @Relation(
        parentColumn = "setTypeIdFk",
        entityColumn = "setTypeId"
    )
    val setType: SetType,
)
