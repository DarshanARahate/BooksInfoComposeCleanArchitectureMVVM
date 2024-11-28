package com.books.info

import retrofit2.Call
import retrofit2.http.GET

interface BooksApi {

    @GET("books.json")
    fun getBooks(): Call<List<Book>>

}