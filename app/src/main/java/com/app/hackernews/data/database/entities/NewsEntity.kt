package com.app.hackernews.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.hackernews.data.database.NEWS_TABLE

@Entity(tableName = NEWS_TABLE)
data class NewsEntity(
    @PrimaryKey
    val objectID: String,
    val storyTitle: String?,
    val storyUrl: String?,
    val commentText: String?,
    val author: String?,
    val date: Long?,
)
