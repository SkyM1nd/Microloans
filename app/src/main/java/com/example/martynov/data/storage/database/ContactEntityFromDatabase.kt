package com.example.martynov.data.storage.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.martynov.data.storage.DataSourceType

@Entity
class ContactEntityFromDatabase(
    @PrimaryKey
    val id: Int,
    val name: String,
    val phone: String
) : DataSourceType