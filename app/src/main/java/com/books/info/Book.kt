package com.books.info

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val finished: Boolean
)

val mockBookList = listOf(
    Book(0, "The Fellowship of the Ring", "J. R. R. Tolkien", false),
    Book(1, "The Two Towers", "J. R. R. Tolkien", false),
    Book(2, "The Return of the King", "J. R. R. Tolkien", false),
    Book(3, "The Hobbit", "J. R. R. Tolkien", false),
    Book(4, "The Eye of the World", "Robert Jordan", false),
    Book(5, "The Dragon Reborn", "Robert Jordan", false),
    Book(6, "A Christmas Carol", "Charles Dickens", false),
    Book(7, "A Tale of Two Cities", "Charles Dickens", false),
    Book(8, "War and Peace", "Leo Tolstoy", false),
    Book(9, "Moby Dick", "Herman Melville", false),
    Book(10, "Tom Sawyer", "Mark Twain", false),
    Book(11, "Alice in Wonderland", "Lewis Carroll", false),
    Book(12, "Jane Eyre", "Charlotte Bronte", false),
    Book(13, "David Copperfield", "Charles Dickens", false),
    Book(14, "The Lion, the Witch, and the Wardrobe", "C. S. Lewis", false),
    Book(15, "Getting There", "Samir Deokuliar", false)
)