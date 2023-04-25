package com.example.sabaideatest.data.di

import com.example.sabaideatest.data.endpoint.MovieEndPoint
import com.example.sabaideatest.data.repository.MovieRepositoryImpl
import com.example.sabaideatest.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
@OptIn(ExperimentalCoroutinesApi::class)
object AppModule {

    private const val BASE_URL = "https://www.filimo.com/api/en/v1/"

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun providesOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .connectTimeout(180, TimeUnit.SECONDS)
            .readTimeout(180, TimeUnit.SECONDS)
            .writeTimeout(180, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideCategoryEndPoint(retrofit: Retrofit): MovieEndPoint =
        retrofit.create(MovieEndPoint::class.java)

    @Provides
    @Singleton
    fun provideCategoryRepository(
        endPoint: MovieEndPoint,
    ): MovieRepository = MovieRepositoryImpl(endPoint)

}