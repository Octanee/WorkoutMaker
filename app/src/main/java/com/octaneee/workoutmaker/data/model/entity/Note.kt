package com.octaneee.workoutmaker.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity

@Entity(tableName = "note_table")
data class Note(val note: String) : BaseEntity {
    @PrimaryKey(autoGenerate = true)
    var noteId: Long = 0
}
