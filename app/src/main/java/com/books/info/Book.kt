package com.books.info

data class Book(
    val id: Int,
    val title: String,
    val author: String
)

val mockBookList = listOf(
    Book(0, "The Fellowship of the Ring", "J. R. R. Tolkien"),
    Book(1, "The Two Towers", "J. R. R. Tolkien"),
    Book(2, "The Return of the King", "J. R. R. Tolkien"),
    Book(3, "The Hobbit", "J. R. R. Tolkien"),
    Book(4, "The Eye of the World", "Robert Jordan"),
    Book(5, "The Dragon Reborn", "Robert Jordan"),
    Book(6, "A Christmas Carol", "Charles Dickens"),
    Book(7, "A Tale of Two Cities", "Charles Dickens"),
    Book(8, "War and Peace", "Leo Tolstoy"),
    Book(9, "Moby Dick", "Herman Melville"),
    Book(10, "Tom Sawyer", "Mark Twain"),
    Book(11, "Alice in Wonderland", "Lewis Carroll"),
    Book(12, "Jane Eyre", "Charlotte Bronte"),
    Book(13, "David Copperfield", "Charles Dickens"),
    Book(14, "The Lion, the Witch, and the Wardrobe", "C. S. Lewis"),
    Book(15, "Getting There", "Samir Deokuliar")
)