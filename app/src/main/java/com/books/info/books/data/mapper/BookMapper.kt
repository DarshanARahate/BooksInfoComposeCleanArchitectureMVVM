package com.books.info.books.data.mapper

import com.books.info.books.domain.model.Book
import com.books.info.books.data.local.LocalBook
import com.books.info.books.data.remote.RemoteBook

fun Book.toRemoteBook(): RemoteBook {
    return RemoteBook(
        id = id,
        title = title,
        author = author,
        genre = genre,
        series = series
    )
}

fun Book.toLocalBook(): LocalBook {
    return LocalBook(
        id = id,
        title = title,
        author = author,
        genre = genre,
        series = series,
        finished = finished
    )
}

fun LocalBook.toBook(): Book {
    return Book(
        id = id,
        title = title,
        author = author,
        genre = genre,
        series = series,
        finished = finished
    )
}

fun LocalBook.toRemoteBook(): RemoteBook {
    return RemoteBook(
        id = id,
        title = title,
        author = author,
        genre = genre,
        series = series
    )
}

fun RemoteBook.toLocalBook(): LocalBook {
    return LocalBook(
        id = id,
        title = title,
        author = author,
        genre = genre,
        series = series
    )
}

fun RemoteBook.Book(): Book {
    return Book(
        id = id,
        title = title,
        author = author,
        genre = genre,
        series = series
    )
}

fun List<LocalBook>.toBookList(): List<Book>{
    return this.map{
        Book(
            id = it.id,
            title = it.title,
            author = it.author,
            genre = it.genre,
            series = it.series,
            finished = it.finished
        )
    }
}

fun List<RemoteBook>.toLocalBookList(): List<LocalBook>{
    return this.map{
        LocalBook(
            id = it.id,
            title = it.title,
            author = it.author,
            genre = it.genre,
            series = it.series,
        )
    }
}