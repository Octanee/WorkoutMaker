package com.octaneee.workoutmaker.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity

@Entity(tableName = "exercise_type_table")
data class ExerciseType(
    val exerciseTypeName: String,
    val exerciseTypeDrawable: String
) : BaseEntity {

    @PrimaryKey(autoGenerate = true)
    var exerciseTypeId: Long = 0

    override fun toString(): String = exerciseTypeName
}