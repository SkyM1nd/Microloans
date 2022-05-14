package com.example.martynov.data.storage.file

import com.example.martynov.data.storage.DataSourceType
import java.io.Serializable

class ContactEntityFromFile(
    val id: Int,
    val name: String,
    val phone: String
) : Serializable, DataSourceType