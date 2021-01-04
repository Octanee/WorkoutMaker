package com.octaneee.workoutmaker.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "note_table")
@Parcelize
data class Note(
    var note: String
) : BaseEntity, Parcelable {

    @PrimaryKey(autoGenerate = true)
    var noteId: Long = 0

    override fun toString(): String = note
}
