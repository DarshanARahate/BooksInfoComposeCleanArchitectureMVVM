package com.books.info

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.books.info.Constants.BASE_URL
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException
import java.net.UnknownHostException

class BooksViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {

    val state = mutableStateOf(emptyList<Book>())
    private var api: BooksApi
    private var bookDao = BooksDb.getDaoInstance(BookApplication.getAppContext())
    private val errorHandler = CoroutineExceptionHandler { _, e ->
        e.printStackTrace()
    }

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        api = retrofit.create(BooksApi::class.java)
        getBooks()
    }

    private fun getBooks() {
        viewModelScope.launch(errorHandler) {
            val books = getAllBooks()
            state.value = books.restoreFinishedField()
        }
    }

    private suspend fun getAllBooks(): List<Book> {
        return withContext(Dispatchers.IO) {
            api.getBooks()
            try {
                val books = api.getBooks()
                bookDao.addAll(books)
                return@withContext books
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
                        return@withContext bookDao.getAll()
                    }

                    else -> throw e
                }
            }
        }
    }

    private fun List<Book>.restoreFinishedField(): List<Book> {
        stateHandle.get<List<Int>?>("finished")?.let { selectedIds ->
            val booksMap = this.associateBy {
                it.id
            }.toMutableMap()

            selectedIds.forEach { id ->
                val book = booksMap[id] ?: return@forEach
                booksMap[id] = book.copy(finished = true)
            }
            return booksMap.values.toList()
        }
        return this
    }

    fun toggleFinished(id: Int) {
        val books = state.value.toMutableList()
        val bookIndex = books.indexOfFirst {
            it.id == id
        }
        val book = books[bookIndex]
        books[bookIndex] = book.copy(finished = !book.finished)
        state.value = books
        viewModelScope.launch {
            toogleFinishedDb(id, book.finished)
        }
    }

    private suspend fun toogleFinishedDb(id: Int, oldValue: Boolean) {
        withContext(Dispatchers.IO) {
            bookDao.update(
                PartialBook_finished(
                    id = id,
                    finished = !oldValue
                )
            )
        }
    }
}