package com.octaneee.workoutmaker.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity

@Entity(tableName = "muscle_table")
data class Muscle(val name: String) : BaseEntity {
    @PrimaryKey(autoGenerate = true)
    var muscleId: Long = 0
}