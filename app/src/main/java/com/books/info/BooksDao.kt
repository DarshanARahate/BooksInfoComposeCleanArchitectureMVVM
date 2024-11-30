package com.books.info

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface BooksDao {

    @Query("Select * From books")
    suspend fun getAll(): List<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(books: List<Book>)

    @Update(entity = Book::class)
    suspend fun update(partialBook: PartialBook_finished)
}