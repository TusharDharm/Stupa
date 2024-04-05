package com.example.stupa.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * from user")
    fun getUsers():LiveData<List<User>>

    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE email LIKE :email AND password LIKE :password")
    suspend fun getUser(email:String,password:String):List<User>
}