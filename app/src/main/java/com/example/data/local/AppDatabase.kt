package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NegotiationEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun negotiationDao(): NegotiationDao
}
