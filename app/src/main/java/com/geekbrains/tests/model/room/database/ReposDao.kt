package com.geekbrains.tests.model.room.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.geekbrains.tests.model.room.entity.Repository

@Dao
interface ReposDao {
    @Query("SELECT * FROM Repository")
    suspend fun getAllAsync(): List<Repository>

    @Query("SELECT * FROM Repository WHERE uid IN (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<Repository>

    @Insert
    suspend fun insertAllAsync(vararg repositories: Repository)

    @Delete
    suspend fun delete(user: Repository)

}