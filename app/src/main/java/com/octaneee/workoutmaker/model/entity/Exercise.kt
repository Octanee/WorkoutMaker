package com.octaneee.workoutmaker.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity
import kotlinx.android.parcel.Parcelize

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
@Parcelize
data class Exercise(
    val exerciseName: String,
    val exerciseTypeIdFk: Long,
    val equipmentIdFk: Long,
    val muscleIdFk: Long
) : BaseEntity, Parcelable {

    @PrimaryKey(autoGenerate = true)
    var exerciseId: Long = 0

    override fun toString(): String = exerciseName
}