package com.frame_application.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "items")
class Item(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var itemName: String
) : Serializable