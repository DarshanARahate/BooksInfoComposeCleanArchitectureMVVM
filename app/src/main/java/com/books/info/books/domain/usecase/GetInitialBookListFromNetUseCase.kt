package com.books.info.books.domain.usecase

import com.books.info.books.data.repo.BooksRepository
import com.books.info.books.domain.model.Book
import javax.inject.Inject

class GetInitialBookListFromNetUseCase @Inject constructor(
    private val repo: BooksRepository,
    private val getSortedBooksFromCacheUseCase: GetSortedBooksFromCacheUseCase
) {

    suspend operator fun invoke(): List<Book> {
        repo.loadBooks()
        return getSortedBooksFromCacheUseCase()
    }


}