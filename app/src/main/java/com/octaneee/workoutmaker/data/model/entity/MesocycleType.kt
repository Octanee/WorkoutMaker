package com.octaneee.workoutmaker.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity

@Entity(tableName = "mesocycle_type_table")
data class MesocycleType(val name: String) : BaseEntity {
    @PrimaryKey(autoGenerate = true)
    var mesocycleTypeId: Long = 0
}