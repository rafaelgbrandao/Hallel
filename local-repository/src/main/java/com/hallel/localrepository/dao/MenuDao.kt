package com.hallel.localrepository.dao

import androidx.room.*
import com.hallel.localrepository.entity.Event
import com.hallel.localrepository.entity.EventContent
import com.hallel.localrepository.entity.Menu

@Dao
interface MenuDao {

    @Query("Select * from ${Menu.TABLE_NAME} where ${Menu.COLUMN_ID} = (:menuIds)")
    fun getEventContent(menuIds: List<String>): EventContent

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateEvent(menu: Menu): Long

    @Delete
    fun deleteEvent(menu: Menu)
}