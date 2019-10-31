package com.example.rxjavabasic.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val id: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "username")
    val userName: String
)