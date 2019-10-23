package com.hallel.localrepository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Partner.TABLE_PARTNER)
data class Partner(
    @PrimaryKey @ColumnInfo(name = COLUMN_PARTNER_ID) val id: Int,
    @ColumnInfo(name = COLUMN_PARTNER_NAME) val name: String,
    @ColumnInfo(name = COLUMN_PARTNER_LINK) val link: String,
    @ColumnInfo(name = COLUMN_PARTNER_IMAGE) val image: String
) {

    companion object {
        const val TABLE_PARTNER= "Partner"
        const val COLUMN_PARTNER_ID = "id"
        const val COLUMN_PARTNER_NAME = "name"
        const val COLUMN_PARTNER_LINK = "link"
        const val COLUMN_PARTNER_IMAGE = "image"
    }
}