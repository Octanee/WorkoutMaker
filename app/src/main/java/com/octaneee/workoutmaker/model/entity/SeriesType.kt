package com.octaneee.workoutmaker.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity

@Entity(tableName = "series_type_table")
data class SeriesType(
    val seriesTypeName: String
) : BaseEntity {

    @PrimaryKey(autoGenerate = true)
    var seriesTypeId: Long = 0

    override fun toString(): String = seriesTypeName
}