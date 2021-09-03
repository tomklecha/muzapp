package com.tkdev.muzapp.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_users")
data class UserDomain(
    @PrimaryKey @ColumnInfo(name = "id") val userId: String,
    @ColumnInfo(name = "user_name") val userName: String,
)