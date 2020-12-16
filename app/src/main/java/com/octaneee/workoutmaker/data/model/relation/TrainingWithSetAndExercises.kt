package com.octaneee.workoutmaker.data.model.relation

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.octaneee.workoutmaker.data.model.entity.Set
import com.octaneee.workoutmaker.data.model.entity.Training
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TrainingWithSetAndExercises(
    @Embedded
    val training: Training,
    @Relation(
        parentColumn = "trainingId",
        entityColumn = "trainingId",
        entity = Set::class
    )
    val setAndExercises: MutableList<SetAndExercise> = mutableListOf()
) : Parcelable {

    override fun toString(): String {
        return "$training $setAndExercises"
    }
}
