package com.books.info.books.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LocalBook::class],
    version = 1,
    exportSchema = false
)
abstract class BooksDb : RoomDatabase() {
    abstract val dao: BooksDao
}