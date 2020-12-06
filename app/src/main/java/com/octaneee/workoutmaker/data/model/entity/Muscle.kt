package com.octaneee.workoutmaker.data.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "muscle_table")
@Parcelize
data class Muscle(val name: String, val drawable: String) : BaseEntity, Parcelable {
    @PrimaryKey(autoGenerate = true)
    var muscleId: Long = 0
}