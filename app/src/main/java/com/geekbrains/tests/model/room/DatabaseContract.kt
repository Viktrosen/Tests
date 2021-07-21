package com.geekbrains.tests.model.room

import com.geekbrains.tests.model.room.entity.Repository

interface DatabaseContract {

    suspend fun insertData(vararg repository: Repository)
    suspend fun getData(): List<Repository>
}