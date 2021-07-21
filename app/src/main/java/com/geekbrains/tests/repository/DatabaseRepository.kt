package com.geekbrains.tests.repository

import com.geekbrains.tests.model.room.DatabaseContract
import com.geekbrains.tests.model.room.database.AppDatabase
import com.geekbrains.tests.model.room.entity.Repository

class DatabaseRepository(private val dbInstance: AppDatabase = AppDatabase.getInstance()) :
    DatabaseContract {

    override suspend fun insertData(vararg repository: Repository) =
        dbInstance.reposDao.insertAllAsync(*repository)

    override suspend fun getData(): List<Repository> = dbInstance.reposDao.getAllAsync()
}