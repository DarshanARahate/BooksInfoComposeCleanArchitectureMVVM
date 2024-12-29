package com.books.info.books.presentation.bookdetails

import com.books.info.books.domain.model.Book

data class BookDetailsScreenState(
    val book: Book?,
    val isLoading: Boolean,
    val error: String? = null
)