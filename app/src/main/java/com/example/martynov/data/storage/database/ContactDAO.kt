package com.example.martynov.data.storage.database

import androidx.room.*

@Dao
interface ContactDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contactEntity: ContactEntityFromDatabase)

    @Query("SELECT COUNT(*) FROM ContactEntityFromDatabase")
    fun getCountContacts(): Int

    @Query("SELECT * FROM ContactEntityFromDatabase")
    fun getContacts(): List<ContactEntityFromDatabase>

    @Query("DELETE FROM ContactEntityFromDatabase")
    fun clearContacts()

    @Update
    fun update(contactEntity: ContactEntityFromDatabase)

    @Delete
    fun delete(contactEntity: ContactEntityFromDatabase)
}
