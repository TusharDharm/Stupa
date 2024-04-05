package com.example.stupa.repository

import androidx.lifecycle.LiveData
import com.example.stupa.room.User
import com.example.stupa.room.UserDao
import com.example.stupa.room.UserDatabase
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDatabase: UserDatabase) {
    fun getUsers():LiveData<List<User>>{
        return userDatabase.userDao().getUsers()
    }

    suspend fun insertUser(user:User){
        userDatabase.userDao().insertUser(user)
    }

    suspend fun getUser(email:String,password:String):List<User>{
        return userDatabase.userDao().getUser(email,password)
    }
}