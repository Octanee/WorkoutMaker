package com.octaneee.workoutmaker.repository

import com.octaneee.workoutmaker.data.dao.NoteDao
import com.octaneee.workoutmaker.model.entity.Note
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class NoteRepository @Inject constructor(private val dao: NoteDao) : BaseRepository<Note>(dao) {
}