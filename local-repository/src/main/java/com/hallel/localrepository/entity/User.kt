package com.hallel.localrepository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = User.TABLE_USER)
data class User(
    @PrimaryKey @ColumnInfo(name = COLUMN_USER_ID) val userId: Int,
    @ColumnInfo(name = COLUMN_USER_NAME) val userName: String,
    @ColumnInfo(name = COLUMN_USER_EMAIL) val userEmail: String,
    @ColumnInfo(name = COLUMN_USER_PHONE) val userPhone: String,
    @ColumnInfo(name = COLUMN_USER_BIRTHDAY) val userBirthday: String,
    @ColumnInfo(name = COLUMN_USER_IS_SENT) val isSent: Int
) {

    companion object {
        const val TABLE_USER= "User"
        const val COLUMN_USER_ID = "id"
        const val COLUMN_USER_NAME = "name"
        const val COLUMN_USER_EMAIL = "email"
        const val COLUMN_USER_PHONE = "phone"
        const val COLUMN_USER_BIRTHDAY = "birthday"
        const val COLUMN_USER_IS_SENT = "isSent"
    }
}