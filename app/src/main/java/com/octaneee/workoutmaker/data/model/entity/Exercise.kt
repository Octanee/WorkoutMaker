package com.octaneee.workoutmaker.data.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity
import kotlinx.android.parcel.Parcelize

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
        ),
        ForeignKey(
            entity = Muscle::class,
            parentColumns = ["muscleId"],
            childColumns = ["muscleId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
@Parcelize
data class Exercise(
    val exerciseName: String,
    val exerciseTypeId: Long,
    val equipmentId: Long,
    val muscleId: Long
) : BaseEntity, Parcelable {
    @PrimaryKey(autoGenerate = true)
    var exerciseId: Long = 0

    override fun toString(): String = "($exerciseId) $exerciseName"
}