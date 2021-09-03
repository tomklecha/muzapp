package com.tkdev.muzapp.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tkdev.muzapp.domain.models.UserDomain
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    @Query("SELECT * FROM table_users WHERE id IS :currentUserId")
    fun fetchUser(currentUserId: String) : Flow<UserDomain>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUsers(userDomains: List<UserDomain>)

}