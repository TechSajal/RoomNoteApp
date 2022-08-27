package com.example.roomnoteapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomnoteapp.Model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT*FROM user_table ORDER BY id ASC")
    fun readAllData():LiveData<List<User>>

    @Update
    suspend fun updateuser(user: User)
}