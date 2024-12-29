package com.books.info.books.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.books.info.books.presentation.bookdetails.BookDetailsScreen
import com.books.info.books.presentation.bookdetails.BookDetailsViewModel
import com.books.info.books.presentation.booklist.BookScreenState
import com.books.info.books.presentation.booklist.BooksScreen
import com.books.info.books.presentation.booklist.BooksViewModel
import com.books.info.ui.theme.BooksInfoComposeCleanArchitectureMVVMTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BooksInfoComposeCleanArchitectureMVVMTheme {
                BookTrackerApp()
            }
        }
    }
}

@Composable
private fun BookTrackerApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "books") {
        composable(route = "books") {
            val viewModel: BooksViewModel = hiltViewModel()
            BooksScreen(
                state = viewModel.state.value,
                onItemClick = { id ->
                    navController.navigate("books/$id")
                },
                onFinishedClick = { id ->
                    viewModel.toggleFinished(id)
                }
            )
        }
        composable(
            route = "books/{book_id}",
            arguments = listOf(navArgument("book_id") {
                type = NavType.IntType
            })
        ) {
            val viewModel: BookDetailsViewModel = hiltViewModel()
            BookDetailsScreen(viewModel.state.value)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BooksInfoComposeCleanArchitectureMVVMTheme {
        BooksScreen(state = BookScreenState(listOf(), false),
            {}, { _ -> }
        )
    }
}