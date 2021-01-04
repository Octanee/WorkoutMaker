package com.octaneee.workoutmaker.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "macrocycle_table")
data class Macrocycle(
    var macrocycleName: String
) : BaseEntity, Parcelable {

    @PrimaryKey(autoGenerate = true)
    var macrocycleId: Long = 0

    override fun toString(): String = macrocycleName
}