package com.example.roomnoteapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val noteTitle:String,
    val notesubtitle:String,
    val note:String,
    val preference:Int = 1
)