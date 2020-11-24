package com.octaneee.workoutmaker.data.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity

@Entity(
    tableName = "set_table",
    foreignKeys = [
        ForeignKey(
            entity = SetType::class,
            parentColumns = ["setTypeId"],
            childColumns = ["setTypeId"],
            onDelete = ForeignKey.SET_NULL,
        ),
        ForeignKey(
            entity = Exercise::class,
            parentColumns = ["exerciseId"],
            childColumns = ["exerciseId"],
            onDelete = ForeignKey.CASCADE,
        )
    ]
)
data class Set(
    val setTypeId: Long,
    val exerciseId: Long,
    val minimum: Int,
    val maximum: Int,
    val restTime: Int?,
    val repsInReserve: Int?
) : BaseEntity {
    @PrimaryKey(autoGenerate = true)
    var setId: Long = 0
}