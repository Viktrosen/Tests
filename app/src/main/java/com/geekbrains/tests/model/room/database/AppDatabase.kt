package com.geekbrains.tests.model.room.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.geekbrains.tests.model.room.entity.Repository

@androidx.room.Database(
    entities = [
        Repository::class
    ],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {

    abstract val reposDao: ReposDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: AppDatabase? = null
        fun getInstance() = instance
            ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?) {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, AppDatabase::class.java, DB_NAME)
                    .build()
            }
        }
    }

}