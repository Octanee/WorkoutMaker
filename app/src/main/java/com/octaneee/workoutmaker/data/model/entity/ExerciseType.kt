package com.octaneee.workoutmaker.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity

@Entity(tableName = "exercise_type_table")
data class ExerciseType(val type: String) : BaseEntity {
    @PrimaryKey(autoGenerate = true)
    var exerciseTypeId: Long = 0
}