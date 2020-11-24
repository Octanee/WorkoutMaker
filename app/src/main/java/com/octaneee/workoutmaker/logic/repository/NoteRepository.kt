package com.octaneee.workoutmaker.logic.repository

import com.octaneee.workoutmaker.data.dao.NoteDao
import com.octaneee.workoutmaker.data.model.entity.Note
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class NoteRepository(private val dao: NoteDao) : BaseRepository<Note>(dao) {
}