package com.octaneee.workoutmaker.data.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity

@Entity(
    tableName = "exercise_table",
    foreignKeys = [
        ForeignKey(
            entity = ExerciseType::class,
            parentColumns = ["exerciseTypeId"],
            childColumns = ["exerciseTypeId"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Equipment::class,
            parentColumns = ["equipmentId"],
            childColumns = ["equipmentId"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class Exercise(
    val name: String,
    val exerciseTypeId: Long,
    val equipmentId: Long
) :
    BaseEntity {
    @PrimaryKey(autoGenerate = true)
    var exerciseId: Long = 0
}