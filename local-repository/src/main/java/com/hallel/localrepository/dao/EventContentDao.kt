package com.hallel.localrepository.dao

import androidx.room.*
import com.hallel.localrepository.entity.Event
import com.hallel.localrepository.entity.EventContent

@Dao
interface EventContentDao {

    @Query("Select * from ${EventContent.TABLE_NAME} where ${EventContent.COLUMN_EVENT_ID} = :eventId")
    fun getEventContent(eventId: Int): Event

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateEvent(event: Event): Long

    @Delete
    fun deleteEvent(event: Event)
}