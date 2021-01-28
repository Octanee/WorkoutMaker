package com.octaneee.workoutmaker.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity

@Entity(tableName = "note_table")
data class Note(
    var note: String
) : BaseEntity {

    @PrimaryKey(autoGenerate = true)
    var noteId: Long = 0

    override fun toString(): String = note
}
