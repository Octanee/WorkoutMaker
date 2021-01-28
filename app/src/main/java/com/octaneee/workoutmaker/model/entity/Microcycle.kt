package com.octaneee.workoutmaker.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity

@Entity(
    tableName = "microcycle_table",
    foreignKeys = [
        ForeignKey(
            entity = Mesocycle::class,
            parentColumns = ["mesocycleId"],
            childColumns = ["mesocycleIdFk"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class Microcycle(
    var microcycleName: String,
    var mesocycleIdFk: Long,
    var numberOfDays: Int
) : BaseEntity {
    @PrimaryKey(autoGenerate = true)
    var microcycleId: Long = 0

    override fun toString(): String = microcycleName
}