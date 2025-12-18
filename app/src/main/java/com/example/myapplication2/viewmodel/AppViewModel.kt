package com.example.myapplication2.viewmodel

import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.dto.UserWithSubscriptionLevel
import com.example.myapplication2.entity.SubscriptionLevel
import com.example.myapplication2.entity.User
import com.example.myapplication2.repository.SubscriptionLevelRepository
import com.example.myapplication2.repository.UserRepository

class AppViewModel (
    private val userRepository: UserRepository,
    private val subscriptionLevelRepository: SubscriptionLevelRepository
) : ViewModel() {

    val allUsers: LiveData<List<UserWithSubscriptionLevel>> = userRepository.allUsers
    val allSubscriptionLevels: LiveData<List<SubscriptionLevel>> = subscriptionLevelRepository.allSubscriptionLevels

    fun insert(user: User) = viewModelScope.launch { userRepository.insert(user) }
    fun update(user: User) = viewModelScope.launch { userRepository.update(user) }
    fun delete(user: User) = viewModelScope.launch { userRepository.delete(user) }

    class AppViewModelFactory(
        private val userRepository: UserRepository,
        private val subscriptionLevelRepository: SubscriptionLevelRepository
    ) : ViewModelProvider.Factory {
        override fun <T: ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(AppViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AppViewModel(userRepository, subscriptionLevelRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}