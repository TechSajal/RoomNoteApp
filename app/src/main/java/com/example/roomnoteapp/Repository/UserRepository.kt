package com.example.roomnoteapp.Repository

import androidx.lifecycle.LiveData
import com.example.roomnoteapp.Model.User
import com.example.roomnoteapp.data.UserDao

class UserRepository(private val userDao: UserDao){
    val readAllData:LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateuser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteuser(user: User){
        userDao.deleteuser(user)
    }
    suspend fun deleteallasers(){
        userDao.deleteAllUsers()
    }
}