package com.books.info.books.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface BooksDao {

    @Query("Select * From books")
    suspend fun getAll(): List<LocalBook>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(books: List<LocalBook>)

    @Update(entity = LocalBook::class)
    suspend fun update(partialBook: PartialLocalBook_finished)

    @Update(entity = LocalBook::class)
    suspend fun updateAll(partialBooks: List<PartialLocalBook_finished>)

    @Query("SELECT * FROM books WHERE r_finished = 1")
    suspend fun getAllFinished(): List<LocalBook>

    @Query("SELECT * FROM books WHERE r_id = :id")
    suspend fun getBook(id: Int): LocalBook

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(book: LocalBook)


}