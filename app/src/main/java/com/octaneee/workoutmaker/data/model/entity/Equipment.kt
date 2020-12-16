package com.octaneee.workoutmaker.data.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "equipment_table")
@Parcelize
data class Equipment(val equipmentName: String, val drawable: String) : BaseEntity, Parcelable {
    @PrimaryKey(autoGenerate = true)
    var equipmentId: Long = 0

    override fun toString(): String = "(${equipmentId}) $equipmentName"
}