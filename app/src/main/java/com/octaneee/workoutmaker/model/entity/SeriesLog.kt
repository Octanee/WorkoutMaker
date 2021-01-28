package com.octaneee.workoutmaker.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity
import java.util.*

@Entity(
    tableName = "series_log_table",
    foreignKeys = [
        ForeignKey(
            entity = Series::class,
            parentColumns = ["seriesId"],
            childColumns = ["seriesIdFk"],
            onDelete = ForeignKey.SET_NULL,
        )
    ]
)
data class SeriesLog(
    val seriesIdFk: Long,
    val quantity: Int,
    val repsInReserve: Int,
    val weight: Float,
    val logDate: Date
) : BaseEntity {

    @PrimaryKey(autoGenerate = true)
    var setLogId: Long = 0
}