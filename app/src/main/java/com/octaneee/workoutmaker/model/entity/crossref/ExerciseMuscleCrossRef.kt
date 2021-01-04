package com.octaneee.workoutmaker.model.entity.crossref

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.Exercise
import com.octaneee.workoutmaker.model.entity.Muscle
import com.octaneee.workoutmaker.model.entity.base.BaseEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "exercise_muscle_cross_ref",
    foreignKeys = [
        ForeignKey(
            entity = Exercise::class,
            parentColumns = ["exerciseId"],
            childColumns = ["exerciseIdFk"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = Muscle::class,
            parentColumns = ["muscleId"],
            childColumns = ["muscleIdFk"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ExerciseMuscleCrossRef(
    val exerciseIdFk: Long,
    val muscleIdFk: Long
) : BaseEntity, Parcelable {
    @PrimaryKey(autoGenerate = true)
    var exerciseMuscleCrossRefId: Long = 0
}