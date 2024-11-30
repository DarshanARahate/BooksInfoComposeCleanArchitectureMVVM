package com.books.info

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "books")
data class Book(
    @PrimaryKey
    @ColumnInfo("r_id")
    @SerializedName("r_id")
    val id: Int,
    @ColumnInfo("r_title")
    @SerializedName("r_title")
    val title: String,
    @ColumnInfo("r_author")
    @SerializedName("r_author")
    val author: String,
    @ColumnInfo("r_genre")
    @SerializedName("r_genre")
    val genre: String,
    @ColumnInfo("r_series")
    @SerializedName("r_series")
    val series: String?,
    @ColumnInfo("r_finished")
    @SerializedName("r_finished")
    var finished: Boolean = false
)