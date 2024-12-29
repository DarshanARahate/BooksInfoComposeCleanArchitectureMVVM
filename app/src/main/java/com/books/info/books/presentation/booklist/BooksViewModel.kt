package com.books.info.books.presentation.booklist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.books.info.books.data.di.MainDispatcher
import com.books.info.books.domain.usecase.GetInitialBookListFromNetUseCase
import com.books.info.books.domain.usecase.ToggleFinishedUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class BooksViewModel(
    private val toggleFinishedUseCase: ToggleFinishedUseCase,
    private val getBookUseCase: GetInitialBookListFromNetUseCase,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    val state: State<BookScreenState>
        get() = _state

    private val _state = mutableStateOf(
        BookScreenState(
            book = listOf(),
            true
        )
    )

    private val errorHandler = CoroutineExceptionHandler { _, e ->
        e.printStackTrace()
        _state.value = _state.value.copy(
            error = e.message,
            isLoading = false
        )
    }

    init {
        getBooks()
    }

    private fun getBooks() {
        viewModelScope.launch(errorHandler + dispatcher) {
            val books = getBookUseCase()
            _state.value = _state.value.copy(
                book = books,
                isLoading = false
            )
        }
    }

    fun toggleFinished(id: Int) {
        val books = _state.value.book.toMutableList()
        val bookIndex = books.indexOfFirst { it.id == id }

        val book = books[bookIndex]
        viewModelScope.launch(errorHandler + dispatcher) {
            val updateBooks = toggleFinishedUseCase(id, book.finished)
            _state.value = _state.value.copy(book = updateBooks)
        }
    }
}