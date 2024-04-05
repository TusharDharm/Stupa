package com.example.stupa.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name: String,
    val mobile: String,
    val country: String,
    val email:String,
    val password:String
)
