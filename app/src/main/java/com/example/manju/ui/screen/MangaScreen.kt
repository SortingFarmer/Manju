package com.example.manju.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.manju.data.ApiClient

@Composable
fun MangaScreen(navController: NavHostController, id: String?) {
    var manga = ApiClient.api.getMangaDetails(id = id!!).enqueue()
    Text("Manga with ID: $id")
}