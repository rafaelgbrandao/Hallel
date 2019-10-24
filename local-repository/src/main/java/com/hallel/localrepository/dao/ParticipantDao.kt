package com.hallel.localrepository.dao

import androidx.room.*
import com.hallel.localrepository.entity.Participant

@Dao
interface ParticipantDao {

    @Query("Select * from ${Participant.TABLE_PARTICIPANT}")
    fun getSingleParticipant(): Participant

    @Query("Select * from ${Participant.TABLE_PARTICIPANT} where ${Participant.COLUMN_PARTICIPANT_ID} = (:ids)")
    fun getParticipants(ids: List<String>): List<Participant>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateParticipant(participant: Participant): Long

    @Delete
    fun deleteParticipant(participant: Participant)
}