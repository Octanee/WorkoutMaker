package com.octaneee.workoutmaker.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity

@Entity(tableName = "mesocycle_type_table")
data class MesocycleType(
    val mesocycleTypeName: String
) : BaseEntity {

    @PrimaryKey(autoGenerate = true)
    var mesocycleTypeId: Long = 0
    override fun toString(): String = mesocycleTypeName
}