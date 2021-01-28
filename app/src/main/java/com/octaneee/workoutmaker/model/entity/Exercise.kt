package com.octaneee.workoutmaker.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity

@Entity(
    tableName = "exercise_table",
    foreignKeys = [
        ForeignKey(
            entity = ExerciseType::class,
            parentColumns = ["exerciseTypeId"],
            childColumns = ["exerciseTypeIdFk"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Equipment::class,
            parentColumns = ["equipmentId"],
            childColumns = ["equipmentIdFk"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Muscle::class,
            parentColumns = ["muscleId"],
            childColumns = ["muscleIdFk"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Exercise(
    val exerciseName: String,
    var exerciseTypeIdFk: Long? = null,
    var equipmentIdFk: Long? = null,
    var muscleIdFk: Long? = null,
    var isFavorite: Boolean = false
) : BaseEntity {

    @PrimaryKey(autoGenerate = true)
    var exerciseId: Long = 0


    override fun toString(): String = exerciseName
}