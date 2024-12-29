package com.books.info.books.data.repo

import android.util.Log
import com.books.info.books.domain.model.Book
import com.books.info.books.data.local.BooksDao
import com.books.info.books.data.local.PartialLocalBook_finished
import com.books.info.books.data.mapper.toBook
import com.books.info.books.data.mapper.toLocalBook
import com.books.info.books.data.remote.BooksApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class BookDetailsRepository @Inject constructor(
    private val api: BooksApi,
    private val bookDoa: BooksDao
) {

    private suspend fun refreshCache(id: Int) {
        val mapWrapper = api.getBook(id)
        val remoteBook = mapWrapper.values.first()

        val bookFinished = bookDoa.getBook(id).finished
        bookDoa.add(remoteBook.toLocalBook())

        if (bookFinished) {
            val partialBook = PartialLocalBook_finished(id, true)
            bookDoa.update(partialBook)
        }
    }

    suspend fun getSingleBook(id: Int): Book {
        return withContext(Dispatchers.IO) {
            try {
                refreshCache(id)
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
                        Log.e("BookViewModel", "Error: No data to display")
                        return@withContext bookDoa.getBook(id).toBook()
                    }

                    else -> throw e
                }
            }
            return@withContext bookDoa.getBook(id).toBook()
        }
    }
}