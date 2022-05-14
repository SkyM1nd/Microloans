package com.example.martynov.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ContactEntityFromDatabase::class], version = 1
)
abstract class AppDataBase : RoomDatabase() {

    abstract val contactDAO: ContactDAO

}
