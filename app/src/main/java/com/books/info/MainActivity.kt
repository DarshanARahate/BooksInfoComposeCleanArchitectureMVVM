package com.books.info

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.books.info.ui.theme.BooksInfoComposeCleanArchitectureMVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BooksInfoComposeCleanArchitectureMVVMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        BookTrackerApp()
                    }
                }
            }
        }
    }
}

@Composable
private fun BookTrackerApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "books") {
        composable(route = "books") {
            BookScreen() { id ->
                navController.navigate("books/$id")
            }
        }
        composable(
            route = "books/{book_id}",
            arguments = listOf(navArgument("book_id") {
                type = NavType.IntType
            })
        ) {
            BookDetailsScreen()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BooksInfoComposeCleanArchitectureMVVMTheme {
        Greeting("Android")
    }
}