package com.books.info.books.domain.usecase

import com.books.info.books.data.repo.BooksRepository
import com.books.info.books.domain.model.Book
import javax.inject.Inject

class GetSortedBooksFromCacheUseCase @Inject constructor(
    private val repo: BooksRepository
) {
    suspend operator fun invoke(): List<Book> {
        return repo.getCacheBooks().sortedBy { it.title }
    }
}