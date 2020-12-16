package com.octaneee.workoutmaker.data.model.relation

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.octaneee.workoutmaker.data.model.entity.Microcycle
import com.octaneee.workoutmaker.data.model.entity.Training
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MicrocycleWithTrainings(
    @Embedded
    val microcycle: Microcycle,
    @Relation(
        parentColumn = "microcycleId",
        entityColumn = "microcycleId",
        entity = Training::class
    )
    val trainingWithSetAndExercises: MutableList<TrainingWithSetAndExercises> = mutableListOf()
) : Parcelable {

    override fun toString(): String {
        return "$microcycle $trainingWithSetAndExercises"
    }
}
