package com.app.hackernews.data.network

import com.app.hackernews.data.database.dao.NewsDao
import com.app.hackernews.data.database.entities.NewsEntity
import com.app.hackernews.data.network.models.Hit
import com.app.hackernews.data.network.models.RequestState
import javax.inject.Inject

class HackerNewsRepository
    @Inject
    constructor(
        private val hackerNewsApi: HackerNewsApi,
        private val newsDao: NewsDao,
    ) {
        suspend fun getLatestAndroidNews(): RequestState<List<Hit>> =
            try {
                val response = hackerNewsApi.getLatestAndroidNews()

                if (response.isSuccessful && response.body() != null) {
                    saveNewsOnDatabase(response.body()?.hits.orEmpty())
                    RequestState.Success(returnDataFromDatabase())
                } else {
                    RequestState.Error("ERROR", returnDataFromDatabase().orEmpty())
                }
            } catch (ex: Exception) {
                RequestState.Error("ERROR", returnDataFromDatabase().orEmpty())
            }

        private suspend fun saveNewsOnDatabase(list: List<Hit>) {
            val newsList = mutableListOf<NewsEntity>()
            list.forEachIndexed { index, hit ->
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

        suspend fun returnDataFromDatabase(): List<Hit> {
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

            return listHit
        }

        suspend fun deleteNewsById(objectId: String) {
            newsDao.deleteNewsById(objectId)
        }
    }
