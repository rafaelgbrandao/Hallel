package com.hallel.localrepository.dao

import androidx.room.*
import com.hallel.localrepository.entity.Menu

@Dao
interface MenuDao {

    @Query("Select * from ${Menu.TABLE_NAME} where ${Menu.COLUMN_ID} = (:eventMenuIds)")
    fun getMenuItens(eventMenuIds: List<String>): List<Menu>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMenu(menu: Menu): Long

    @Delete
    fun deleteMenu(menu: Menu)
}