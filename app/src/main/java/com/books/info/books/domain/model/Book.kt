package com.books.info.books.domain.model

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val genre: String,
    val series: String?,
    var finished: Boolean = false
)