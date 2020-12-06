package com.octaneee.workoutmaker.data.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.octaneee.workoutmaker.data.model.entity.Equipment
import com.octaneee.workoutmaker.data.model.entity.Exercise
import com.octaneee.workoutmaker.data.model.entity.ExerciseType
import com.octaneee.workoutmaker.data.model.entity.Muscle

data class ExerciseHolder(

    @Embedded
    val exercise: Exercise,

    @Relation(
        parentColumn = "muscleId",
        entity = Muscle::class,
        entityColumn = "muscleId"
    )
    val primaryMuscle: Muscle,

    @Relation(
        parentColumn = "exerciseTypeId",
        entity = ExerciseType::class,
        entityColumn = "exerciseTypeId",
    )
    val exerciseType: ExerciseType,

    @Relation(
        parentColumn = "equipmentId",
        entity = Equipment::class,
        entityColumn = "equipmentId",
    )
    val equipment: Equipment,
)
