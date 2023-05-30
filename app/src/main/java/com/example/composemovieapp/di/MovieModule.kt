package com.example.composemovieapp.di

import com.example.composemovieapp.data.MovieApi
import com.example.composemovieapp.domain.MovieRepository
import com.example.composemovieapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    @Provides
    @Singleton
    fun provideRetofitInterface(): MovieApi{
        val okHttp = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp)
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepositoryInterface(movieApi: MovieApi):MovieRepository{
        return MovieRepository(movieApi)
    }
}