package com.octaneee.workoutmaker.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity

@Entity(tableName = "set_type_table")
data class SetType(val name: String) : BaseEntity {
    @PrimaryKey(autoGenerate = true)
    var setTypeId: Long = 0
}