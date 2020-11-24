package com.octaneee.workoutmaker.data.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity

@Entity(
    tableName = "mesocycle_table",
    foreignKeys = [
        ForeignKey(
            entity = MesocycleType::class,
            parentColumns = ["mesocycleTypeId"],
            childColumns = ["mesocycleTypeId"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Macrocycle::class,
            parentColumns = ["macrocycleId"],
            childColumns = ["macrocycleId"],
            onDelete = ForeignKey.SET_NULL

        )]
)
data class Mesocycle(
    val mesocycleTypeId: Long,
    val macrocycleId: Long
) : BaseEntity {
    @PrimaryKey(autoGenerate = true)
    var mesocycleId: Long = 0
}