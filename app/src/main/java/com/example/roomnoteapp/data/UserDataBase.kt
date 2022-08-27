package com.example.roomnoteapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomnoteapp.Model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDataBase:RoomDatabase() {
    abstract fun UserDao(): UserDao
    companion object{
        @Volatile
        private var INSTANCE: UserDataBase?=null
        fun getDataBase(context: Context): UserDataBase {
            val tempinstance = INSTANCE
            if (tempinstance!=null){
                return tempinstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    UserDataBase::class.java,"user_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }

}