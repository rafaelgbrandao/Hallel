package com.hallel.localrepository.dao

import androidx.room.*
import com.hallel.localrepository.entity.User

@Dao
interface UserDao {

    @Query("Select * from ${User.TABLE_NAME}")
    fun getUser(): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: User): Long

    @Delete
    fun deleteUser(user: User)
}