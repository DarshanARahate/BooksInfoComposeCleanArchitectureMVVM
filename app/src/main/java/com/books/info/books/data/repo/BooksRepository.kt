package com.books.info.books.data.repo

import android.util.Log
import com.books.info.books.domain.model.Book
import com.books.info.books.data.di.IoDispatcher
import com.books.info.books.data.local.BooksDao
import com.books.info.books.data.local.PartialLocalBook_finished
import com.books.info.books.data.mapper.toBookList
import com.books.info.books.data.mapper.toLocalBookList
import com.books.info.books.data.remote.BooksApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BooksRepository @Inject constructor(
    private val api: BooksApi,
    private val booksDao: BooksDao,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) {
    suspend fun loadBooks() {
        return withContext(dispatcher) {
            try {
                refreshCache()
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
                        Log.e("BooksViewModel", "Error : No data fro Firebase")
                        if (booksDao.getAll().isEmpty()) {
                            Log.e("BooksViewModel", "Error : No data fro Firebase")
                            throw Exception("Error: Device offline and \nno data from local cache")
                        }
                    }

                    else -> throw e
                }
            }
        }
    }

    suspend fun getCacheBooks(): List<Book> {
        return withContext(dispatcher) {
            return@withContext booksDao.getAll().toBookList()
        }
    }

    private suspend fun refreshCache() {
        val remoteBooks = api.getBooks()
        val finishedBooks = booksDao.getAllFinished()

        booksDao.addAll(remoteBooks.toLocalBookList())
        booksDao.updateAll(finishedBooks.map {
            PartialLocalBook_finished(it.id, true)
        })
    }

    suspend fun toggleFinishedDb(id: Int, value: Boolean) =
        withContext(dispatcher) {
            booksDao.update(
                PartialLocalBook_finished(
                    id = id, finished = value
                )
            )
        }

}