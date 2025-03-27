package com.app.hackernews.data.network.models

import com.google.gson.annotations.SerializedName

data class Hit(
    @SerializedName("objectID")
    private val objectID: String?,
    @SerializedName("created_at")
    private val createdAt: String?,
    @SerializedName("_tags")
    private val tags: List<String>?,
    @SerializedName("story_title")
    private val storyTitle: String?,
    @SerializedName("story_url")
    private val storyUrl: String?,
    @SerializedName("comment_text")
    val commentText: String?,
    @SerializedName("author")
    val author: String?,
)
