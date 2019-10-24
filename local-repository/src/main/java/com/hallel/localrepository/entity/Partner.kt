package com.hallel.localrepository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Partner.TABLE_NAME)
data class Partner(
    @PrimaryKey @ColumnInfo(name = COLUMN_ID) val id: Int,
    @ColumnInfo(name = COLUMN_NAME) val name: String,
    @ColumnInfo(name = COLUMN_LINK) val link: String,
    @ColumnInfo(name = COLUMN_IMAGE) val image: String
) {

    companion object {
        const val TABLE_NAME= "partner"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_LINK = "link"
        const val COLUMN_IMAGE = "image"
    }
}