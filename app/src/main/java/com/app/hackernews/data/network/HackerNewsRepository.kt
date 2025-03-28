package com.app.hackernews.data.network

import com.app.hackernews.data.database.dao.NewsDao
import com.app.hackernews.data.database.entities.NewsEntity
import com.app.hackernews.data.network.models.Hit
import com.app.hackernews.data.network.models.RequestState
import com.app.hackernews.data.network.response.AndroidNewsResponse
import javax.inject.Inject

class HackerNewsRepository
    @Inject
    constructor(
        private val hackerNewsApi: HackerNewsApi,
        private val newsDao: NewsDao,
    ) {
        suspend fun getLatestAndroidNews(): RequestState<AndroidNewsResponse> =
            try {
                val response = hackerNewsApi.getLatestAndroidNews()

                if (response.isSuccessful && response.body() != null) {
                    saveNewsOnDatabase(response.body()!!)
                    RequestState.Success(response.body()!!)
                } else {
                    RequestState.Error(response.message())
                }
            } catch (ex: Exception) {
                returnDataFromDatabase()
            }

        private suspend fun saveNewsOnDatabase(body: AndroidNewsResponse) {
            val newsList = mutableListOf<NewsEntity>()
            body.hits.forEachIndexed { index, hit ->
                newsList.add(
                    index,
                    NewsEntity(
                        objectID = hit.objectID,
                        storyTitle = hit.storyTitle.orEmpty(),
                        commentText = hit.commentText.orEmpty(),
                        author = hit.author.orEmpty(),
                        date = hit.date ?: 0L,
                        storyUrl = hit.storyUrl.orEmpty(),
                    ),
                )
            }
            newsDao.insertAll(newsList.toList())
        }

        private suspend fun returnDataFromDatabase(): RequestState.Error<AndroidNewsResponse> {
            val dataFromDatabase = newsDao.getAllNews()

            val listHit =
                dataFromDatabase.map { newsEntity ->
                    Hit(
                        objectID = newsEntity.objectID,
                        storyTitle = newsEntity.storyTitle,
                        commentText = newsEntity.commentText,
                        author = newsEntity.author,
                        date = newsEntity.date,
                        storyUrl = newsEntity.storyUrl,
                    )
                }

            return RequestState.Error(message = "Error", data = AndroidNewsResponse(listHit))
        }
    }
