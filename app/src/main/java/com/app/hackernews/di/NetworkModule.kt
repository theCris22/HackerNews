package com.app.hackernews.di

import com.app.hackernews.BuildConfig
import com.app.hackernews.data.network.DEFAULT_TIME
import com.app.hackernews.data.network.HackerNewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun providesRetrofit() =
        Retrofit
            .Builder()
            .baseUrl(BuildConfig.HACKER_NEWS_BASE_URL)
            .client(providesOkhttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): HackerNewsApi = retrofit.create(HackerNewsApi::class.java)

    @Singleton
    @Provides
    fun providesAuthInterceptor() =
        Interceptor { chain ->
            val original: Request = chain.request()
            val requestBuilder =
                original
                    .newBuilder()
                    .method(original.method, original.body)

            val request = requestBuilder.build()
            chain.proceed(request)
        }

    @Singleton
    @Provides
    fun providesLoggingInterceptor() = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Singleton
    @Provides
    fun providesOkhttpClient() =
        OkHttpClient
            .Builder()
            .connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
            .addInterceptor(providesLoggingInterceptor())
            .addInterceptor(providesAuthInterceptor())
            .build()
}
