package com.books.info.books.presentation.bookdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.books.info.books.presentation.booklist.BookDetails
import com.books.info.books.presentation.booklist.FinishedIcon

@Composable
fun BookDetailsScreen(state: BookDetailsScreenState) {
    if (state.book != null) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val icon = if (state.book.finished) Icons.Default.Check else Icons.Default.Clear

            FinishedIcon(
                icon = icon,
                modifier = Modifier.padding(top = 32.dp, bottom = 32.dp),
                onClick = {})

            BookDetails(
                title = state.book.title, author = state.book.author, modifier = Modifier,
                horizantalAlignment = Alignment.CenterHorizontally
            )

            AdditionalDetails(
                genre = state.book.genre,
                series = state.book.series
            )
        }
    }
}

@Composable
fun AdditionalDetails(
    genre: String, series: String?,
    modifier: Modifier = Modifier.padding(32.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally
) {
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
            text = genre,
            fontSize = 20.sp
        )
        Text(text = series ?: "No Series", fontSize = 20.sp)
    }

}
