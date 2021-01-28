package com.octaneee.workoutmaker.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity
import java.util.*

@Entity(
    tableName = "body_measurement_table",
)
data class BodyMeasurement(
    val dateOfMeasurement: Date,
    val chest: Float,
    val leftArm: Float,
    val rightArm: Float,
    val leftForearm: Float,
    val rightForearm: Float,
    val abdomen: Float,
    val waist: Float,
    val hips: Float,
    val leftThigh: Float,
    val rightThigh: Float,
    val leftCalf: Float,
    val rightCalf: Float,
) : BaseEntity {

    @PrimaryKey(autoGenerate = true)
    var bodyMeasurementId: Long = 0
}
