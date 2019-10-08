package com.hallel.localrepository.dao

import androidx.room.*
import com.hallel.localrepository.entity.User

@Dao
interface UserDao {

    @Query("Select * from ${User.TABLE_USER}")
    fun getUser(): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)
}