package com.octaneee.workoutmaker.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity
import java.util.*

@Entity(
    tableName = "set_log_table",
    foreignKeys = [
        ForeignKey(
            entity = Set::class,
            parentColumns = ["setId"],
            childColumns = ["setIdFk"],
            onDelete = ForeignKey.SET_NULL,
        )
    ]
)
data class SetLog(
    val setIdFk: Long,
    val quantity: Int,
    val repsInReserve: Int,
    val date: Date
) : BaseEntity {

    @PrimaryKey(autoGenerate = true)
    var setLogId: Long = 0
}