package com.octaneee.workoutmaker.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity

@Entity(tableName = "equipment_table")
data class Equipment(
    val equipmentName: String,
    val equipmentDrawable: String
) : BaseEntity {

    @PrimaryKey(autoGenerate = true)
    var equipmentId: Long = 0

    override fun toString(): String = equipmentName
}