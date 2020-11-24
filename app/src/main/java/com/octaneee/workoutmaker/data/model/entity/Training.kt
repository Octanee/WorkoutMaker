package com.octaneee.workoutmaker.data.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity

@Entity(
    tableName = "training_table",
    foreignKeys = [
        ForeignKey(
            entity = Microcycle::class,
            parentColumns = ["microcycleId"],
            childColumns = ["microcycleId"],
            onDelete = ForeignKey.SET_NULL,
        )
    ]
)
data class Training(
    val name: String,
    val microcycleId: Long,
    val dayOfMicrocycle: Int
) : BaseEntity {
    @PrimaryKey(autoGenerate = true)
    var trainingId: Long = 0
}