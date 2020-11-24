package com.octaneee.workoutmaker.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity

@Entity(tableName = "macrocycle_table")
data class Macrocycle(val name: String) : BaseEntity {
    @PrimaryKey(autoGenerate = true)
    var macrocycleId: Long = 0
}