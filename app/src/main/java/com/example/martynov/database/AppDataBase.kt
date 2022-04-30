package com.example.martynov.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.martynov.ContactEntity

@Database(entities = [ContactEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract val contactDAO: ContactDAO

    companion object {
        private var instance: AppDataBase? = null

        fun getAppDatabase(context: Context): AppDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app-db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance as AppDataBase
        }
    }
}
