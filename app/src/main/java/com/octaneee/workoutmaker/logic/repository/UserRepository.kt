package com.octaneee.workoutmaker.logic.repository

import com.octaneee.workoutmaker.data.dao.UserDao
import com.octaneee.workoutmaker.data.model.entity.User
import com.octaneee.workoutmaker.logic.repository.base.BaseRepository

class UserRepository(private val dao: UserDao) : BaseRepository<User>(dao) {

    fun getUser(): User {
        return dao.getUser()
    }

}