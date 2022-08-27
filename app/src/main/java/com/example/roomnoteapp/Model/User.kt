package com.example.roomnoteapp.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val noteTitle:String,
    val notesubtitle:String,
    val note:String,
    val preference:Int = 1
):Parcelable