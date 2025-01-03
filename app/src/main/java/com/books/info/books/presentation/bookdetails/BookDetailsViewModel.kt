package com.books.info.books.presentation.bookdetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.books.info.books.data.repo.BookDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val repo: BookDetailsRepository
) : ViewModel() {

    val state: State<BookDetailsScreenState>
        get() = _state
    private val _state = mutableStateOf(
        BookDetailsScreenState(
            book = null,
            isLoading = true
        )
    )

    private val errorHandler = CoroutineExceptionHandler { _, e ->
        e.printStackTrace()
    }

    init {
        val id = stateHandle.get<Int>("book_id") ?: 0
        getBook(id)
    }

    private fun getBook(id: Int) {
        viewModelScope.launch {
            val book = repo.getSingleBook(id)
            _state.value = _state.value.copy(
                book = book, isLoading = false
            )
        }
    }

}