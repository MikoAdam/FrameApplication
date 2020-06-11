package com.frame_application.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.frame_application.model.Item

@Database(
    version = 1,
    exportSchema = false,
    entities = [Item::class]
)
abstract class Database : RoomDatabase() {

    abstract fun dao(): RoomDao

}