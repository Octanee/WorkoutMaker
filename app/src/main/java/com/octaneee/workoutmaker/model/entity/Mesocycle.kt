package com.octaneee.workoutmaker.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity

@Entity(
    tableName = "mesocycle_table",
    foreignKeys = [
        ForeignKey(
            entity = MesocycleType::class,
            parentColumns = ["mesocycleTypeId"],
            childColumns = ["mesocycleTypeIdFk"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Macrocycle::class,
            parentColumns = ["macrocycleId"],
            childColumns = ["macrocycleIdFk"],
            onDelete = ForeignKey.SET_NULL
        )]
)
data class Mesocycle(
    var mesocycleName: String,
    var mesocycleTypeIdFk: Long,
    var macrocycleIdFk: Long
) : BaseEntity {

    @PrimaryKey(autoGenerate = true)
    var mesocycleId: Long = 0

    override fun toString(): String = mesocycleName
}