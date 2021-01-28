package com.octaneee.workoutmaker.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity

@Entity(tableName = "muscle_table")
data class Muscle(
    val muscleName: String,
    val muscleDrawable: String
) : BaseEntity {

    @PrimaryKey(autoGenerate = true)
    var muscleId: Long = 0

    override fun toString(): String = muscleName
}