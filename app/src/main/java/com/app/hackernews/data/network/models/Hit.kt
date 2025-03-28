package com.app.hackernews.data.network.models

import com.google.gson.annotations.SerializedName

data class Hit(
    @SerializedName("objectID")
    val objectID: String,
    @SerializedName("story_title")
    val storyTitle: String?,
    @SerializedName("story_url")
    val storyUrl: String?,
    @SerializedName("comment_text")
    val commentText: String?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("created_at_i")
    val date: Long?,
)
