package com.octaneee.workoutmaker.data.model.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.octaneee.workoutmaker.data.model.entity.Exercise
import com.octaneee.workoutmaker.data.model.entity.Note
import com.octaneee.workoutmaker.data.model.entity.crossref.ExerciseNoteCrossRef

data class ExerciseWithNotes(
    @Embedded
    val exercise: Exercise,
    @Relation(
        parentColumn = "exerciseId",
        entity = Note::class,
        entityColumn = "noteId",
        associateBy = Junction(ExerciseNoteCrossRef::class)
    )
    val notes: List<Note>
) {
}