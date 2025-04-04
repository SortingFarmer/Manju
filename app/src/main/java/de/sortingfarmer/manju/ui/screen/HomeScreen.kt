package de.sortingfarmer.manju.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import de.sortingfarmer.manju.ui.components.MangaImageCardPreview
import de.sortingfarmer.manju.ui.components.MangaTextCardPreview

@Composable
fun HomeScreen(navController: NavController) {
    Text("Home")

    LazyColumn {
        item {
            MangaImageCardPreview()
        }
        item {
            MangaTextCardPreview()
        }
    }
}