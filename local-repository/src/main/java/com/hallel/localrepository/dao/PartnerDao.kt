package com.hallel.localrepository.dao

import androidx.room.*
import com.hallel.localrepository.entity.Partner

@Dao
interface PartnerDao {

    @Query("Select * from ${Partner.TABLE_NAME}")
    fun getAllPartners(): List<Partner>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(partner: Partner): Long

    @Delete
    fun deleteUser(partner: Partner)
}