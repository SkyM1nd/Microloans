package com.example.martynov.database
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.martynov.ContactEntity

@Dao
interface ContactDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contactEntity: ContactEntity)

    @Query("SELECT COUNT(*) FROM ContactEntity")
    fun getCountContacts(): Int

    @Query("SELECT * FROM ContactEntity")
    fun getContacts(): List<ContactEntity>

    @Query("DELETE FROM ContactEntity")
    fun clearContacts()
}
