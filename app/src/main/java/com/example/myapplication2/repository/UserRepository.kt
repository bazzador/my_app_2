package com.example.myapplication2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.myapplication2.dao.UserDao
import com.example.myapplication2.dto.UserWithSubscriptionLevel
import com.example.myapplication2.entity.User

class UserRepository(private val userDao: UserDao) {
    val allUsers : LiveData<List<UserWithSubscriptionLevel>> = userDao.getUsersWithSubscriptionLevel().asLiveData()

    suspend fun insert(user : User) {
        userDao.insert(user)
    }

    suspend fun update(user : User) {
        userDao.update(user)
    }

    suspend fun delete(user : User) {
        userDao.delete(user)
    }
}