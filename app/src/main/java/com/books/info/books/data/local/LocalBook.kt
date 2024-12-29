package com.books.info.books.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class LocalBook(
    @PrimaryKey
    @ColumnInfo("r_id")
    val id: Int,
    @ColumnInfo("r_title")
    val title: String,
    @ColumnInfo("r_author")
    val author: String,
    @ColumnInfo("r_genre")
    val genre: String,
    @ColumnInfo("r_series")
    val series: String?,
    @ColumnInfo("r_finished")
    var finished: Boolean = false
)
