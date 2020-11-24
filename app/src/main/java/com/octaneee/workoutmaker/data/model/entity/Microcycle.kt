package com.octaneee.workoutmaker.data.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity

@Entity(
    tableName = "microcycle_table",
    foreignKeys = [
        ForeignKey(
            entity = Mesocycle::class,
            parentColumns = ["mesocycleId"],
            childColumns = ["mesocycleId"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class Microcycle(
    val mesocycleId: Long,
    val numberOfDays: Int
) : BaseEntity {
    @PrimaryKey(autoGenerate = true)
    var microcycleId: Long = 0
}