package com.octaneee.workoutmaker.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity
import java.util.*

@Entity(tableName = "macrocycle_table")
data class Macrocycle(
    var macrocycleName: String,
    var macrocycleStartDate: Date,
    var macrocycleEndDate: Date? = null
) : BaseEntity {

    @PrimaryKey(autoGenerate = true)
    var macrocycleId: Long = 0
}