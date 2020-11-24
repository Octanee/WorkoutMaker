package com.octaneee.workoutmaker.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity

@Entity(tableName = "equipment_table")
data class Equipment(val equipment: String) : BaseEntity {
    @PrimaryKey(autoGenerate = true)
    var equipmentId: Long = 0
}