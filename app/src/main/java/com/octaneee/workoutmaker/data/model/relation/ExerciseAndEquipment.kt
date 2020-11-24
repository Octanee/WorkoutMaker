package com.octaneee.workoutmaker.data.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.octaneee.workoutmaker.data.model.entity.Equipment
import com.octaneee.workoutmaker.data.model.entity.Exercise

data class ExerciseAndEquipment(
    @Embedded
    val exercise: Exercise,
    @Relation(
        parentColumn = "equipmentId",
        entity = Equipment::class,
        entityColumn = "equipmentId",
    )
    val equipment: Equipment
) {
}