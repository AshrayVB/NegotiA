package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NegotiationDao {
    @Query("SELECT * FROM negotiations ORDER BY timestamp DESC")
    fun getAllNegotiations(): Flow<List<NegotiationEntity>>

    @Query("SELECT * FROM negotiations WHERE id = :id")
    fun getNegotiationById(id: Int): Flow<NegotiationEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNegotiation(negotiation: NegotiationEntity): Long

    @Query("DELETE FROM negotiations WHERE id = :id")
    suspend fun deleteNegotiationById(id: Int)
}
