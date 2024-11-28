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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BookTrackerViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {

    val state = mutableStateOf(emptyList<Book>())
    private var api: BooksApi
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
            val books = getRemoteBooks()
            state.value = books.restoreFinishedField()
        }
    }

    private suspend fun getRemoteBooks(): List<Book> {
        return withContext(Dispatchers.IO) {
            api.getBooks()
        }
    }

    private fun List<Book>.restoreFinishedField() : List<Book> {
        stateHandle.get<List<Int>?>("finished") ?.let { selectedIds ->
            val booksMap = this.associateBy {
                it.id
            }
            selectedIds.forEach { id ->
                booksMap[id]?.finished = true
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
    }

}