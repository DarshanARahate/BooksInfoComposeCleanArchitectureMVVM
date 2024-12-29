package com.books.info.books.data.di

import android.content.Context
import androidx.room.Room
import com.books.info.books.data.remote.BooksApi
import com.books.info.books.data.local.BooksDao
import com.books.info.books.data.local.BooksDb
import com.books.info.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BooksModule {

    @Provides
    fun provideRoomDao(database: BooksDb): BooksDao {
        return database.dao
    }

    @Singleton
    @Provides
    fun provideRoomDb(
        @ApplicationContext appContext: Context
    ): BooksDb {
        return Room.databaseBuilder(
            appContext.applicationContext,
            BooksDb::class.java,
            "books_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    fun providesRetrofitApi(retrofit: Retrofit): BooksApi {
        return retrofit.create(BooksApi::class.java)
    }

}