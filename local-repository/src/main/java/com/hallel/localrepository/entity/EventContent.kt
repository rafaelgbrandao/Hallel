package com.hallel.localrepository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = EventContent.TABLE_NAME)
data class EventContent(
    @PrimaryKey @ColumnInfo(name = COLUMN_ID) val id: Int,
    @ColumnInfo(name = COLUMN_EVENT_ID) val name: Int,
    @ColumnInfo(name = COLUMN_EVENT_TITLE) val eventTitle: String,
    @ColumnInfo(name = COLUMN_EVENT_SUBTITLE) val eventSubtitle: String,
    @ColumnInfo(name = COLUMN_MENU_ITEMS) val menuItems: String,
    @ColumnInfo(name = COLUMN_SPONSORS) val sponsors: String
) {

    companion object {
        const val TABLE_NAME = "event_content"
        const val COLUMN_ID = "id"
        const val COLUMN_EVENT_ID = "event_id"
        const val COLUMN_EVENT_TITLE = "event_title"
        const val COLUMN_EVENT_SUBTITLE = "event_subtitle"
        const val COLUMN_MENU_ITEMS = "menu_items"
        const val COLUMN_SPONSORS = "sponsors"
    }
}