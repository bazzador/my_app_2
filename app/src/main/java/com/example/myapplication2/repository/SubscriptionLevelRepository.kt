package com.example.myapplication2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.myapplication2.dao.SubscriptionLevelDao
import com.example.myapplication2.entity.SubscriptionLevel

class SubscriptionLevelRepository(private var subscriptionLevelDao: SubscriptionLevelDao) {

    val allSubscriptionLevels : LiveData<List<SubscriptionLevel>> = subscriptionLevelDao.getAllSubscriptionLevels().asLiveData()

    suspend fun insert(subscriptionLevel: SubscriptionLevel) {
        subscriptionLevelDao.insert(subscriptionLevel)
    }
}