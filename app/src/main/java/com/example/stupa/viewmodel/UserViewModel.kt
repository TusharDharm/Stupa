package com.example.stupa.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stupa.repository.UserRepository
import com.example.stupa.room.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {
    fun getUsers():LiveData<List<User>>{
        return userRepository.getUsers()
    }

    fun insertUser(user:User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insertUser(user)
        }
    }

    suspend fun getUser(email:String,password:String):List<User>{
//        viewModelScope.launch(Dispatchers.IO) {
           return userRepository.getUser(email,password)
//        }
    }
}