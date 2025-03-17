package com.example.manju.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun MangaScreen(navController: NavHostController, id: String?) {
    Text("Manga with ID: $id")
}