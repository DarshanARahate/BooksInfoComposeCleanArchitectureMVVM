package com.books.info

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PartialBook_finished(
    @PrimaryKey
    @ColumnInfo(name = "r_id")
    val id: Int,
    @ColumnInfo(name = "r_finished")
    val finished: Boolean = false
)