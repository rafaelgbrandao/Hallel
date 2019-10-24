package com.hallel.localrepository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Participant.TABLE_PARTICIPANT)
data class Participant(
    @PrimaryKey @ColumnInfo(name = COLUMN_PARTICIPANT_ID) val id: Int,
    @ColumnInfo(name = COLUMN_PARTICIPANT_NAME) val name: String,
    @ColumnInfo(name = COLUMN_PARTICIPANT_TYPE) val type: Int,
    @ColumnInfo(name = COLUMN_PARTICIPANT_IMAGE) val image: String
) {

    companion object {
        const val TABLE_PARTICIPANT= "PARTICIPANTS"
        const val COLUMN_PARTICIPANT_ID = "id"
        const val COLUMN_PARTICIPANT_NAME = "name"
        const val COLUMN_PARTICIPANT_TYPE = "type"
        const val COLUMN_PARTICIPANT_IMAGE = "image"
    }
}