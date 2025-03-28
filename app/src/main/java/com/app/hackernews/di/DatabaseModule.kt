package com.app.hackernews.di

import android.content.Context
import androidx.room.Room
import com.app.hackernews.data.database.HackerNewsDatabase
import com.app.hackernews.data.database.NEWS_DATABASE
import com.app.hackernews.data.database.dao.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): HackerNewsDatabase =
        Room
            .databaseBuilder(
                context,
                HackerNewsDatabase::class.java,
                NEWS_DATABASE,
            ).build()

    @Provides
    fun providePokemonDao(database: HackerNewsDatabase): NewsDao = database.newsDao()
}
