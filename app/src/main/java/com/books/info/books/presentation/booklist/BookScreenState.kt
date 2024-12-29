package com.books.info.books.presentation.booklist

import com.books.info.books.domain.model.Book

data class BookScreenState(
    val book: List<Book>,
    val isLoading: Boolean,
    val error: String? = null
)