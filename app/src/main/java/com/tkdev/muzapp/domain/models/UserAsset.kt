package com.tkdev.muzapp.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class UserAsset(
    @SerializedName("id") val userId: String,
    @SerializedName("user_name") val userName: String,
)