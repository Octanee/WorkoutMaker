package com.octaneee.workoutmaker.data.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "exercise_type_table")
@Parcelize
data class ExerciseType(val exerciseTypeName: String, val drawable: String) : BaseEntity,
    Parcelable {
    @PrimaryKey(autoGenerate = true)
    var exerciseTypeId: Long = 0

    override fun toString(): String = "($exerciseTypeId) $exerciseTypeName"
}