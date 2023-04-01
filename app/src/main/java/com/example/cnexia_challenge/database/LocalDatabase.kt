package com.example.cnexia_challenge.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cnexia_challenge.models.Car

@Database(
    entities = [Car::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DatabaseConverters::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun getCarDao():  CarDao
}
