package com.hallel.localrepository.dao

import androidx.room.*
import com.hallel.localrepository.entity.Participant

@Dao
interface ParticipantDao {

    @Query("Select * from ${Participant.TABLE_NAME}")
    fun getSingleParticipant(): Participant

    @Query("Select * from ${Participant.TABLE_NAME} where ${Participant.COLUMN_ID} = (:ids)")
    fun getParticipants(ids: List<String>): List<Participant>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateParticipant(participant: Participant): Long

    @Delete
    fun deleteParticipant(participant: Participant)
}