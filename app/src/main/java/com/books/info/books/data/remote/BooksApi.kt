package com.books.info.books.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApi {

    @GET("books.json")
    suspend fun getBooks(): List<RemoteBook>

    @GET("books.json?orderBy=\"r_id\"")
    suspend fun getBook(@Query("equalTo") id: Int): Map<String, RemoteBook>

}