package com.example.studentmanagment.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insert(users: Users)

    @Query("SELECT * FROM Users WHERE userEmail = :userEmail")
    fun getUserDetails(userEmail: String):Users
}