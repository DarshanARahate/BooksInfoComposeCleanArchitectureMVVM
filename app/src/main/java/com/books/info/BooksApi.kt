package com.books.info

import retrofit2.http.GET

interface BooksApi {

    @GET("books.json")
    suspend fun getBooks(): List<Book>

}