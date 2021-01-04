package com.octaneee.workoutmaker.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "set_type_table")
@Parcelize
data class SetType(
    val setTypeName: String
) : BaseEntity, Parcelable {

    @PrimaryKey(autoGenerate = true)
    var setTypeId: Long = 0

    override fun toString(): String = setTypeName
}