package com.octaneee.workoutmaker.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity
import java.util.*

@Entity(
    tableName = "weight_measurement_table"
)
data class WeightMeasurement(
    val dateOfMeasurement: Date,
    val weight: Float
) : BaseEntity {
    @PrimaryKey(autoGenerate = true)
    var weightMeasurementId: Long = 0
}