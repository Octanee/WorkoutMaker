package com.octaneee.workoutmaker.repository

import androidx.lifecycle.LiveData
import com.octaneee.workoutmaker.data.dao.UserDao
import com.octaneee.workoutmaker.data.model.entity.User
import com.octaneee.workoutmaker.repository.base.BaseRepository
import javax.inject.Inject

class UserRepository @Inject constructor(private val dao: UserDao) : BaseRepository<User>(dao) {

    fun getUser(userId: Long): LiveData<User> = dao.getUser(userId)
}