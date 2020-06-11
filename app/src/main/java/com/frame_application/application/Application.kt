package com.frame_application.application

import android.app.Application
import androidx.room.Room
import com.frame_application.data.Database

class Application : Application() {

    companion object {
        lateinit var database: Database
            private set
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            "frame_database"
        ).build()
    }

}