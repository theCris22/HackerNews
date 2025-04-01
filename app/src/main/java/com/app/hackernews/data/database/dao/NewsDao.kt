package com.app.hackernews.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.hackernews.data.database.entities.NewsEntity

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(newsList: List<NewsEntity>)

    @Query("SELECT * FROM NEWS_TABLE")
    suspend fun getAllNews(): List<NewsEntity>

    @Query("DELETE FROM NEWS_TABLE WHERE objectID = :objectID")
    suspend fun deleteNewsById(objectID: String)
}
