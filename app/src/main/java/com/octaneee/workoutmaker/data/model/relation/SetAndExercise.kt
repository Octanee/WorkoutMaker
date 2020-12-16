package com.octaneee.workoutmaker.data.model.relation

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.octaneee.workoutmaker.data.model.entity.Exercise
import com.octaneee.workoutmaker.data.model.entity.Set
import com.octaneee.workoutmaker.data.model.entity.SetType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SetAndExercise(
    @Embedded
    val set: Set,
    @Relation(
        parentColumn = "setTypeId",
        entityColumn = "setTypeId"
    )
    val setType: SetType,
    @Relation(
        parentColumn = "exerciseId",
        entityColumn = "exerciseId"
    )
    val exercise: Exercise
) : Parcelable {
    override fun toString(): String {
        return "$set, $exercise"
    }
}
