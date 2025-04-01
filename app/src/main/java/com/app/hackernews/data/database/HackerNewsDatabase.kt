package com.app.hackernews.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.hackernews.data.database.dao.NewsDao
import com.app.hackernews.data.database.entities.NewsEntity

@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class HackerNewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}
