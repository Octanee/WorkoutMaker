package com.octaneee.workoutmaker.repository.base

import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity

abstract class BaseRepository<T : BaseEntity>(private val dao: BaseDao<T>) {

    suspend fun insert(entity: T): Long {
        return dao.insert(entity)
    }

    suspend fun insert(entities: List<T>): List<Long> {
        return dao.insert(entities)
    }

    suspend fun update(entity: T) {
        dao.update(entity)
    }

    suspend fun update(entities: List<T>) {
        dao.update(entities)
    }

    suspend fun delete(entity: T) {
        dao.delete(entity)
    }

    suspend fun delete(entities: List<T>) {
        dao.delete(entities)
    }
}