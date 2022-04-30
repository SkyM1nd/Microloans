package com.example.martynov

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class ContactEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val phone: String
) : Serializable