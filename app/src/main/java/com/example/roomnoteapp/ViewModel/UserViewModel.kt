package com.example.roomnoteapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomnoteapp.Model.User
import com.example.roomnoteapp.data.UserDataBase
import com.example.roomnoteapp.Repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application) {
      val readAllData:LiveData<List<User>>
      private val repository: UserRepository
      init {
          val userDao = UserDataBase.getDataBase(application).UserDao()
          repository = UserRepository(userDao)
          readAllData = repository.readAllData
      }
    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }
    fun updateuser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateuser(user)
        }
    }
    fun deleteuser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteuser(user)
        }
    }
    fun deleteAllUser(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteallasers()
        }
    }
}